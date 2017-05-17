package com.ttm.basic.filter;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPipeline;
import redis.clients.jedis.ShardedJedisPool;
import redis.clients.util.MurmurHash;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liguoqing on 2017/5/17.
 */
public class RedisBloomFilter implements Serializable {

    private static final long serialVersionUID = -8726635992913321940L;

    private int bitSetSize;
    private int hashFunctionCount;
    private ShardedJedisPool jedisPool;

    private static final String defaultKey = "redis:defaultKey";

    public RedisBloomFilter(String host, int maxKey, double errorRate, int timeout) throws Exception {
        List<JedisShardInfo> jedisShardInfoList = Lists.newArrayList();
        if (StringUtils.isNoneEmpty(host)) {
            String[] hosts = host.split(";");
            for (String hst : hosts) {
                String[] ip_port = hst.split(":");
                if (ip_port.length != 2) {
                    throw new Exception("host info error");
                }
                int port = 6379;
                if (!NumberUtils.isNumber(ip_port[1])) {
                    throw new Exception("host port error");
                }
                port = Integer.valueOf(ip_port[1]);
                jedisShardInfoList.add(new JedisShardInfo(ip_port[0], port, timeout));
            }
        } else {
            throw new Exception("host info null");
        }
        jedisPool = initJedisPool(jedisShardInfoList);
        bitSetSize = bitSetSize(maxKey, errorRate);
        hashFunctionCount = bitSetSize(bitSetSize, maxKey);
    }

    private ShardedJedisPool initJedisPool(List<JedisShardInfo> jedisShardInfoList) {
        GenericObjectPoolConfig config = new GenericObjectPoolConfig();
        config.setTestOnBorrow(false); //借出时不校验有效性
        config.setTestOnReturn(false); //归还时不校验有效性
        config.setMinIdle(10); //最小空闲数
        config.setMaxIdle(100); //最大空闲数
        config.setMaxWaitMillis(3 * 1000); //最大等待时间
        config.setMaxTotal(100); //最大连接数
        config.setMinEvictableIdleTimeMillis(1000L * 60 * 60 * 5L);
        return new ShardedJedisPool(config, jedisShardInfoList);
    }

    private int bitSetSize(int maxKey, double errorRate) {
        return (int) Math.ceil(maxKey * (Math.log(errorRate) / Math.log(0.6185)));
    }

    private int bitSetSize(int bitSize, int maxKey) {
        return (int) Math.ceil(Math.log(2) * (bitSize / maxKey));
    }

    /**
     * get the setbit offset by MurmurHash
     *
     * @param bizId
     * @return
     */
    private int[] murmurHashOffset(String bizId, int hashFunctionCount, int maxBitCount) {
        int[] offsets = new int[hashFunctionCount];
        byte[] b = bizId.getBytes();
        int hash1 = MurmurHash.hash(b, 0);
        int hash2 = MurmurHash.hash(b, hash1);
        for (int i = 0; i < hashFunctionCount; ++i) {
            offsets[i] = (int) (Math.abs((hash1 + i * hash2) % maxBitCount));
        }
        return offsets;
    }

    public void add(String bizId) {
        add(defaultKey, bizId);
    }

    public void add(String key, String bizId) {
        int[] bits = murmurHashOffset(bizId, hashFunctionCount, bitSetSize);
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (int bit : bits) {
                jedis.setbit(key, bit, true);
            }
        } finally {
            if (null != jedis) {
                jedisPool.close();
            }
        }
    }

    public void addWithPipe(String key, String bizId) {
        int[] bits = murmurHashOffset(bizId, hashFunctionCount, bitSetSize);
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            ShardedJedisPipeline pipeline = jedis.pipelined();
            for (int bit : bits) {
                pipeline.setbit(key, bit, true);
            }
            pipeline.sync();
        } finally {
            if (null != jedis) {
                jedisPool.close();
            }
        }
    }

    public boolean include(String key, String bizId) {
        int[] bits = murmurHashOffset(bizId, hashFunctionCount, bitSetSize);
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            for (int bit : bits) {
                if (!jedis.getbit(key, bit)) {
                    return false;
                }
            }
        } finally {
            if (null != jedis) {
                jedisPool.close();
            }
        }
        return true;
    }

    public boolean includeWithPipe(String key, String bizId) {
        int[] bits = murmurHashOffset(bizId, hashFunctionCount, bitSetSize);
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            ShardedJedisPipeline pipeline = jedis.pipelined();
            for (int bit : bits) {
                pipeline.getbit(key, bit);
            }
            List<Object> responses = pipeline.syncAndReturnAll();
            for (Object object : responses) {
                if (object instanceof Boolean) {
                    Boolean contains = (Boolean) object;
                    if (!contains) {
                        return false;
                    }
                }
            }
        } finally {
            if (null != jedis) {
                jedisPool.close();
            }
        }
        return true;
    }

    public long count(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.bitcount(key);
        } finally {
            if (null != jedis) {
                jedisPool.close();
            }
        }
    }

    public String get(String key) {
        ShardedJedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            return jedis.get(key);
        } finally {
            if (null != jedis) {
                jedisPool.close();
            }
        }
    }

    public static void main(String args[]) throws Exception {
        String host = "127.0.0.1:6379";
        int maxKey = (int) Math.pow(2, 31);
        double errorRate = 0.00000001f;
        int timeout = 1000;
        RedisBloomFilter redisBloomFilter = new RedisBloomFilter(host, maxKey, errorRate, timeout);
        System.out.println(redisBloomFilter.bitSetSize / 8 / 1024 / 1024);
    }


}

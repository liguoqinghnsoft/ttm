package com.ttm.basic.filter;

import com.ttm.basic.utils.HashFunction;

import java.io.Serializable;
import java.util.BitSet;

/**
 * Created by liguoqing on 2017/5/16.
 */
public class BloomFilter implements Serializable {

    private static final long serialVersionUID = 5132374148049001092L;

    private BitSet bitSet;

    private int bitSetSize;

    private int addElementSize;

    private int hashFunctionNum;

    /**
     * 构造器 容量为 recordSize * addElementSize
     *
     * @param recordSize      过滤器预计开辟最大记录
     * @param addElementSize  过滤器预计要存储的记录
     * @param hashFunctionNum 哈希函数的个数（每条记录要占用bit数）
     */
    public BloomFilter(int recordSize, int addElementSize, int hashFunctionNum) {
        this.bitSetSize = (int) Math.ceil(recordSize * hashFunctionNum);
        this.bitSet = new BitSet(this.bitSetSize);
        this.addElementSize = addElementSize;
        this.hashFunctionNum = hashFunctionNum;
    }

    /**
     * 过滤器错误率
     *
     * @return
     */
    public double getFalsePositiveProbability() {
        // (1 - e^(-k * n / m)) ^ k
        return Math.pow((1 - Math.exp(-hashFunctionNum * (double) addElementSize / bitSetSize)),
                hashFunctionNum);
    }

    public BloomFilter(int recordSize, int hashFunctionNum) {
        this.bitSetSize = (int) Math.ceil(recordSize * hashFunctionNum);
        this.hashFunctionNum = hashFunctionNum;
        this.bitSet = new BitSet();
    }

    public void put(String url) {
        int[] position = genHash(url.getBytes(), hashFunctionNum);
        for (int i = 0; i < position.length; i++) {
            int pos = Math.abs(position[i] % bitSetSize);
            bitSet.set(pos);
        }
    }

    public boolean contains(String url) {
        int[] position = genHash(url.getBytes(), hashFunctionNum);
        for (int i = 0; i < position.length; i++) {
            int pos = Math.abs(position[i] % bitSetSize);
            if (!bitSet.get(pos)) {
                return false;
            }
        }
        return true;
    }

    private int[] genHash(byte[] bytes, int hashFunctionNum) {
        int[] result = new int[hashFunctionNum];
        int k = 0;
        while (k < hashFunctionNum) {
            result[k] = HashFunction.hash(bytes, k);
            k++;
        }
        return result;
    }

    public static void main(String args[]) {
        BloomFilter bloomFilter = new BloomFilter(10, 10, 8);
        String[] urls = new String[]{"baidu.com", "google.com", "360.com", "jd.com", "tmall.com", "111.com.cn", "google.com", "360.com", "qq.com"};
        for (String url : urls) {
            if (!bloomFilter.contains(url)) {
                bloomFilter.put(url);
            } else {
                System.out.println(url);
            }
        }
        System.out.println(bloomFilter.getFalsePositiveProbability());
    }

}

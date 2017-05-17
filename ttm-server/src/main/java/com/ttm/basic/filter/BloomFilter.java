package com.ttm.basic.filter;

import com.ttm.basic.utils.HashFunction;

import java.io.Serializable;
import java.util.BitSet;

/**
 * 1，如果他告诉你不存在，则一定不存在；
 * 2，如果他告诉你存在，则可能不存在。
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
     * @param hashFunctionNum 哈希函数的个数（每条记录要占用bit数）
     */
    public BloomFilter(int recordSize, int hashFunctionNum) {
        this.bitSetSize = (int) Math.ceil(recordSize * hashFunctionNum);
        this.bitSet = new BitSet(this.bitSetSize);
        this.addElementSize = 0;
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

    public void put(String url) {
        int[] position = genHash(url.getBytes(), hashFunctionNum);
        for (int i = 0; i < position.length; i++) {
            int pos = Math.abs(position[i] % bitSetSize);
            bitSet.set(pos, true);
        }
        this.addElementSize++;
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

    public void clear() {
        this.bitSet.clear();
        this.addElementSize = 0;
    }

    public int count() {
        return this.addElementSize;
    }

    public static void main(String args[]) {
        BloomFilter bloomFilter = new BloomFilter(20, 8);
        String[] urls = new String[]{"baidu.com", "google.com", "360.com", "jd.com", "tmall.com", "111.com.cn", "google.com", "360.com", "qq.com"};
        for (String url : urls) {
            if (!bloomFilter.contains(url)) {
                bloomFilter.put(url);
            } else {
                System.out.println(url);
            }
        }
        System.out.println("element size :" + bloomFilter.count());
        System.out.println(bloomFilter.getFalsePositiveProbability());
        bloomFilter.clear();
    }

}

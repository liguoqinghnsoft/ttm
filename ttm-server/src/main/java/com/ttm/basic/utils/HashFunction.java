package com.ttm.basic.utils;

import java.io.Serializable;

/**
 * Created by liguoqing on 2017/5/16.
 */
public class HashFunction implements Serializable {

    private static final long serialVersionUID = -2358670822411621514L;

    public static int hash(byte[] bytes, int num) {
        switch (num) {
            case 0:
                return RSHash(bytes);
            case 1:
                return JSHash(bytes);
            case 2:
                return PJWHash(bytes);
            case 3:
                return ELFHash(bytes);
            case 4:
                return BKDRHash(bytes);
            case 5:
                return SDBMHash(bytes);
            case 6:
                return DJBHash(bytes);
            case 7:
                return BPHash(bytes);
            case 8:
                return DEKHash(bytes);
        }
        return 0;
    }

    private static int RSHash(byte[] bytes) {
        int a = 378551;
        int b = 63689;
        int hash = 0;
        for (int i = 0; i < bytes.length; i++) {
            hash = hash * a + bytes[i];
            a = a * b;
        }
        return hash;
    }

    private static int JSHash(byte[] bytes) {
        int hash = 1315423911;
        for (int i = 0; i < bytes.length; i++) {
            hash ^= ((hash << 5) + bytes[i] + hash >> 2);
        }
        return hash;
    }

    private static int PJWHash(byte[] bytes) {
        long BitsInUnsignedInt = (4 * 8);
        long ThreeQuarters = ((BitsInUnsignedInt * 3) / 4);
        long OneEighth = (BitsInUnsignedInt / 8);
        long HighBits = (long) (0xFFFFFFFF) << (BitsInUnsignedInt - OneEighth);
        int hash = 0;
        long test = 0;
        for (int i = 0; i < bytes.length; i++) {
            hash = (hash << OneEighth) + bytes[i];
            if ((test = hash & HighBits) != 0) {
                hash = (int) ((hash ^ (test >> ThreeQuarters)) & (~HighBits));
            }
        }
        return hash;
    }

    private static int ELFHash(byte[] bytes) {
        int hash = 0;
        int x = 0;
        for (int i = 0; i < bytes.length; i++) {
            hash = (hash << 4) + bytes[i];
            if ((x = hash & 0xF0000000) != 0) {
                hash ^= (x >> 24);
                hash &= ~x;
            }
        }
        return hash;
    }

    private static int BKDRHash(byte[] bytes) {
        int seed = 131;
        int hash = 0;
        int len = bytes.length;
        for (int i = 0; i < len; i++) {
            hash = (hash * seed) + bytes[i];
        }
        return hash;
    }

    private static int SDBMHash(byte[] bytes) {
        int hash = 0;
        int len = bytes.length;
        for (int i = 0; i < len; i++) {
            hash = bytes[i] + (hash << 6) + (hash << 16) - hash;
        }
        return hash;
    }

    private static int DJBHash(byte[] bytes) {
        int hash = 5381;
        int len = bytes.length;
        for (int i = 0; i < len; i++) {
            hash = ((hash << 5) + hash) + bytes[i];
        }
        return hash;
    }

    private static int DEKHash(byte[] bytes) {
        int hash = bytes.length;
        for (int i = 0; i < bytes.length; i++) {
            hash = ((hash << 5) ^ (hash >> 27)) ^ bytes[i];
        }
        return hash;
    }

    private static int BPHash(byte[] bytes) {
        int hash = 0;
        for (int i = 0; i < bytes.length; i++) {
            hash = hash << 7 ^ bytes[i];
        }
        return hash;
    }

    public static void main(String args[]) {
        byte[] bytes = "http://www.cnblogs.com/rainy-shurun/p/5426570.html".getBytes();
        System.out.println(HashFunction.RSHash(bytes));
        System.out.println(HashFunction.JSHash(bytes));
    }

}

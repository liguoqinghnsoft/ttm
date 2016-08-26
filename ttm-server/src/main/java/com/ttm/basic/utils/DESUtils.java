package com.ttm.basic.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import java.security.SecureRandom;

/**
 * Created by liguoqing on 2016/6/2.
 */
public class DESUtils {
    private static Logger logger = LoggerFactory.getLogger(DESUtils.class);
    private static final String KEY_ALGORITHM = "DES";
    private static Cipher cipher;
    static{
        try {
            cipher = Cipher.getInstance(KEY_ALGORITHM);
        }catch (Exception e){
            logger.error("DESUtils static error!");
        }
    }

    /**
     * 加密
     * @param secretKey
     * @param data
     * @return
     */
    public static String encrypt(byte[] secretKey,String data){
        String result = null;
        SecureRandom sr = new SecureRandom();
        try {
            DESKeySpec desKeySpec = new DESKeySpec(secretKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            SecretKey secretKey1 = secretKeyFactory.generateSecret(desKeySpec);
            cipher.init(Cipher.ENCRYPT_MODE,secretKey1,sr);
            result = Base64.encodeBase64String(cipher.doFinal(data.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 解密
     * @param secretKey
     * @param data
     * @return
     */
    public static String decrypt(byte[] secretKey,String data){
        String result = null;
        SecureRandom sr = new SecureRandom();
        try {
            DESKeySpec desKeySpec = new DESKeySpec(secretKey);
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance(KEY_ALGORITHM);
            SecretKey secretKey1 = secretKeyFactory.generateSecret(desKeySpec);
            cipher.init(Cipher.DECRYPT_MODE,secretKey1,sr);
            result = new String(cipher.doFinal(Base64.decodeBase64(data.getBytes())),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 初始化密钥
     * @return
     * @throws Exception
     */
    public static String initKey()throws Exception{
        KeyGenerator keyGenerator = KeyGenerator.getInstance(KEY_ALGORITHM);
        keyGenerator.init(128);
        return Base64.encodeBase64String(keyGenerator.generateKey().getEncoded());
    }

}

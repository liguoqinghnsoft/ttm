package com.ttm.basic.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;

/**
 * Created by liguoqing on 2016/6/2.
 */
public class AESUtils {
    private static Logger logger = LoggerFactory.getLogger(AESUtils.class);
    private static final String KEY_ALGORITHM = "AES";
    private static Cipher cipher;
    static{
        try {
            cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        }catch (Exception e){
            logger.error("AESUtils static error!");
        }
    }

    /**
     * 加密
     * @param secretKey
     * @param data
     * @return
     */
    public static String encrypt(String secretKey,String data){
        String result = null;
        Key key = restoreKey(Base64.decodeBase64(secretKey));
        try {
             cipher.init(Cipher.ENCRYPT_MODE,key);
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
    public static String decrypt(String secretKey,String data){
        String result = null;
        Key key = restoreKey(Base64.decodeBase64(secretKey));
        try {
            cipher.init(Cipher.DECRYPT_MODE,key);
            result = new String(cipher.doFinal(Base64.decodeBase64(data)),"UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 还原密钥
     * @param secretKey
     * @return
     */
    private static Key restoreKey(byte[] secretKey){
        return new SecretKeySpec(secretKey,KEY_ALGORITHM);
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

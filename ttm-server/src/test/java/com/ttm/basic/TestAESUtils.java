package com.ttm.basic;

import com.ttm.basic.utils.AESUtils;
import org.junit.Test;

/**
 * Created by liguoqing on 2016/9/11.
 */
public class TestAESUtils {

    @Test
     public void testEncrptAndDecrpt(){
        String secretKey = "ddf70a01cebaceb7";
        String xmlData = "IcanTest  AESUtils";
        String result = AESUtils.encrypt(secretKey,xmlData);
        System.out.println(result);
        result = AESUtils.decrypt(secretKey,result);
        System.out.println(result);
     }


}

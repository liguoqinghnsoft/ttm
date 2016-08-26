package com.ttm.basic.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

/**
 * Created by liguoqing on 2016/5/11.
 */
@Component
public class ValidatorUtils {

    private static Logger logger = LoggerFactory.getLogger(ValidatorUtils.class);

    @Value("${reg.email}")
    private String REGEX_EMAIL;

    @Value("${reg.phone}")
    private String REGEX_MOBILE;

    @Value("${reg.idCard}")
    private String REGEX_ID;

    /**
     * 手机号校验
     * @param phoneNum
     * @return
     */
    public boolean checkPhoneNum(String phoneNum){
        return Pattern.matches(REGEX_MOBILE, phoneNum);
    }

    /**
     * 邮箱校验
     * @param email
     * @return
     */
    public boolean checkEmail(String email){
        logger.info("logback Test");
        return Pattern.matches(REGEX_EMAIL, email);
    }

    /**
     * 身份证校验
     * @param idCard
     * @return
     */
    public boolean checkIdCard(String idCard){
        return Pattern.matches(REGEX_ID, idCard);
    }


}

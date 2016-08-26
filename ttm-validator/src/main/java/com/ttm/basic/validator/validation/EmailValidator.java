package com.ttm.basic.validator.validation;

import com.ttm.basic.validator.inter.Email;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by liguoqing on 2016/7/21.
 */
public class EmailValidator implements ConstraintValidator<Email,String>{

    private Email email;
    private static final String REGEX_EMAIL = "^([a-z0-9A-Z]+[-|\\.|_]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
    @Override
    public void initialize(Email email) {
        this.email = email;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        String msg = "";
        boolean flag = true;
        if(StringUtils.isEmpty(s) || "null".equals(s.toLowerCase())){
            msg = "参数不能为空";
            flag = false;
        }
        if(flag) {
            if(!Pattern.matches(REGEX_EMAIL, s)){
                flag = false;
                msg = email.message();
            }
        }
        if(!flag){
            constraintValidatorContext.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
        }
        return flag;
    }
}

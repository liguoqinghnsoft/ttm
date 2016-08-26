package com.ttm.basic.validator.validation;

import com.ttm.basic.validator.inter.MobilePhone;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by liguoqing on 2016/7/21.
 */
public class MobilePhoneValidator implements ConstraintValidator<MobilePhone,String>{

    private MobilePhone phone;
    private static final String REGEX_PHONE = "^((13[0-9])|(15[^4,\\D])|(147)|(17[0,8])|(18[^4,\\D]))\\d{8}$";
    @Override
    public void initialize(MobilePhone phone) {
        this.phone = phone;
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
            if(!Pattern.matches(REGEX_PHONE, s)){
                flag = false;
                msg = phone.message();
            }
        }
        if(!flag){
            constraintValidatorContext.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
        }
        return flag;
    }
}

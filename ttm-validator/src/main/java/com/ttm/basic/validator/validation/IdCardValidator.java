package com.ttm.basic.validator.validation;

import com.ttm.basic.validator.inter.IdCard;
import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

/**
 * Created by liguoqing on 2016/7/21.
 */
public class IdCardValidator implements ConstraintValidator<IdCard,String>{

    private IdCard idCard;
    private static final String REGEX_ID_CARD = "\\\\d{15}|^\\\\d{17}([0-9]|X|x)$";
    @Override
    public void initialize(IdCard idCard) {
        this.idCard = idCard;
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
            if(!Pattern.matches(REGEX_ID_CARD, s)){
                flag = false;
                msg = idCard.message();
            }
        }
        if(!flag){
            constraintValidatorContext.buildConstraintViolationWithTemplate(msg).addConstraintViolation();
        }
        return flag;
    }
}

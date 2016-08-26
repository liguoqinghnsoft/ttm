package com.ttm.basic.api.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * Created by liguoqing on 2016/5/26.
 */
public class DataObjectsValidator implements ConstraintValidator<Max,Integer>{

    private Max max;

    @Override
    public void initialize(Max max) {
        this.max = max;
        System.out.println("max->"+max.value());
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        System.out.println("value->" + value);
        constraintValidatorContext.buildConstraintViolationWithTemplate(max.message()).addConstraintViolation();
        boolean result = value > max.value() ? false : true;
        System.out.println(result);
        return result;
    }
}

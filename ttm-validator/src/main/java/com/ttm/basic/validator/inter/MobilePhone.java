package com.ttm.basic.validator.inter;

import com.ttm.basic.validator.validation.MobilePhoneValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by liguoqing on 2016/7/26.
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.FIELD,ElementType.METHOD,ElementType.PARAMETER})
@Constraint(validatedBy = MobilePhoneValidator.class)
public @interface MobilePhone {
    String value() default "";
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

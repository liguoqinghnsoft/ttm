package com.ttm.basic.validator.inter;

import com.ttm.basic.validator.validation.IdCardValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by liguoqing on 2016/7/26.
 */
@Documented
@Retention(value = RetentionPolicy.RUNTIME)
@Target(value = {ElementType.PARAMETER,ElementType.FIELD,ElementType.METHOD})
@Constraint(validatedBy = IdCardValidator.class)
public @interface IdCard {
    String value() default "";
    String message() default "";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

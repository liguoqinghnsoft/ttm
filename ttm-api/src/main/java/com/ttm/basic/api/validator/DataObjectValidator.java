package com.ttm.basic.api.validator;

import com.ttm.basic.api.dto.DataObject;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * Created by liguoqing on 2016/5/25.
 */
public class DataObjectValidator implements Validator{


    @Override
    public boolean supports(Class<?> aClass) {
        return DataObject.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        DataObject dataValidator = (DataObject)o;
        if(dataValidator.getAge() > 120){
             errors.rejectValue("age","${age.max}");
        }else if(dataValidator.getAge() < 1){
             errors.rejectValue("age","${age.min}");
        }
    }
}

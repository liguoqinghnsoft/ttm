package com.ttm.basic.api.dto;


import com.ttm.basic.api.validator.Max;

import java.io.Serializable;

/**
 * Created by liguoqing on 2016/5/26.
 */
public class DataObjects implements Serializable{

    private static final long serialVersionUID = 7233850278207168287L;

    @Max(value = 120,message = "{age.max}")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    @Override
    public String toString() {
        return "DataObjects{" +
                "age=" + age +
                '}';
    }
}

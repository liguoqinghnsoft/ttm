package com.ttm.basic.api.dto;


import com.ttm.basic.api.validator.Min;

/**
 * Created by liguoqing on 2016/5/25.
 */
public class DataObject {

    @Min(value = 1,message = "${age.min}")
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private int age;

    @Override
    public String toString() {
        return "DataValidator{" +
                "age=" + age +
                '}';
    }
}

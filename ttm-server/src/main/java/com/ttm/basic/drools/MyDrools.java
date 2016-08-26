package com.ttm.basic.drools;

/**
 * Created by liguoqing on 2016/6/30.
 */
public class MyDrools {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

//    public Boolean getValid() {
//        return valid;
//    }
//
//    public void setValid(Boolean valid) {
//        this.valid = valid;
//    }

    private String name;
    private int age;
    //private Boolean valid = true;

    public MyDrools(){}
    public MyDrools(String name,int age){
        this.name=name;
        this.age=age;
    }
}

package com.ttm.basic.utils;

/**
 * Created by liguoqing on 2016/5/20.
 */
public enum TopicType {

    Order("订单"),
    Other("其它");

    public String topic;
    TopicType(String topic){
       this.topic=topic;
    }

}

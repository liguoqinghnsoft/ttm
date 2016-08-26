package com.ttm.basic.utils;

/**
 * Created by liguoqing on 2016/5/20.
 */
public enum TagType {

    Order_Create("Order_Create","订单创建");

    public String tag;
    public String remark;
    TagType(String tag,String remark){
        this.tag=tag;
        this.remark=remark;
    }

}

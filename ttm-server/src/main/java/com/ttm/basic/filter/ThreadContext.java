package com.ttm.basic.filter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by liguoqing on 2016/6/3.
 */
public class ThreadContext {

    private static final String CONTEXT_KEY = "USER_ID";
    private static ThreadLocal<Map<String,Object>> context = new ThreadLocal<>();

    public static void put(Object value){
        Map<String,Object> map = context.get();
        if(null == map){
            map = new HashMap<>();
            context.set(map);
        }
        map.put(CONTEXT_KEY,value);
    }

    public static Object get(){
        Map<String,Object> map = context.get();
        if(null != map){
            return map.get(CONTEXT_KEY);
        }
        return null;
    }

    public static void remove(){
        context.remove();
    }


}

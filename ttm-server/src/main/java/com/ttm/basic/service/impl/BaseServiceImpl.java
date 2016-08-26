package com.ttm.basic.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by liguoqing on 2016/6/8.
 */
public class BaseServiceImpl<T> {

    private static Logger logger = LoggerFactory.getLogger(BaseServiceImpl.class);

    public void objectReflectMap(Map<String,Object> params,T t){
        Field[] fields = t.getClass().getDeclaredFields();
        /*
        Method[] methods = classZ.getDeclaredMethods();
        for(Method method:methods){
            String methodName = method.getName();
            System.out.println(methodName);
            try {
                if(methodName.startsWith("get")) {
                    System.out.println(method.invoke(user));
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        }*/
        for(Field field:fields){
            String fieldName = field.getName();
            String typeName = field.getGenericType().getTypeName();
            Object value = null;
            try {
                field.setAccessible(true);
                value = field.get(t);
                if(null != value){
                    if(typeName.equals("java.lang.String")){
                        if("".equals(value)){
                            continue;
                        }
                    }
                    params.put(fieldName,value);
                }
            } catch (IllegalAccessException e) {
                System.out.println(fieldName + "->" + value + "," + typeName);
                logger.error("BaseServiceImpl.objectReflectMap()",e);
            }
        }
    }

}

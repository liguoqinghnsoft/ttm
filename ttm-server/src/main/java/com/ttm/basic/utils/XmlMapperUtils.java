package com.ttm.basic.utils;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * Created by liguoqing on 2016/6/16.
 */
@Component
public class XmlMapperUtils {

    @Resource
    private XmlMapper xmlMapper;

    public <T> T xmlToBean(String xmlStr,Class<T> tClass){
        try {
            return xmlMapper.readValue(xmlStr,tClass);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String beanToXml(Object object){
        try {
            return xmlMapper.writeValueAsString(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}

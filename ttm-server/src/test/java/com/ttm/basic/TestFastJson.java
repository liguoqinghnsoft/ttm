package com.ttm.basic;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.ser.ToXmlGenerator;
import com.ttm.basic.api.dto.PageModel;
import com.ttm.basic.dal.model.User;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by liguoqing on 2016/6/14.
 */
public class TestFastJson {

    @Test
    public void testEncode(){
        List<User> data = new ArrayList<>();
        User user = new User();
        user.setIdentityCardNo("111");
        user.setUserName("111测试");
        user.setCreateTime(new Date());
        user.setNickName("111昵称");
        data.add(user);
        User user_ = new User();
        user_.setIdentityCardNo("222");
        user_.setUserName("222测试");
        user_.setCreateTime(new Date());
        user_.setNickName("222昵称");
        data.add(user_);
        PageModel pageModel = new PageModel();
        pageModel.setData(data);
        String jsonStr = JSON.toJSONString(pageModel);
        System.out.println(jsonStr);
        decode(jsonStr);
        xmlMapper(pageModel);
    }

    private void decode(String jsonStr){
        System.out.println(JSON.parseObject(jsonStr, PageModel.class));
    }

    private void xmlMapper(Object object){
        XmlMapper xmlMapper = new XmlMapper();
        try {
            xmlMapper.configure(ToXmlGenerator.Feature.WRITE_XML_1_1,true);
            xmlMapper.configure(DeserializationFeature.FAIL_ON_NULL_FOR_PRIMITIVES,false);
            String xmlStr = xmlMapper.writeValueAsString(object);
            System.out.println(xmlStr);
            PageModel pageModel = xmlMapper.readValue(xmlStr, PageModel.class);
            System.out.println(pageModel);
            System.out.println(JSON.toJSONString(pageModel));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

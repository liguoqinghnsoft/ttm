package com.ttm.basic;

import com.ttm.basic.StartApplication;
import com.ttm.basic.dal.model.User;
import com.ttm.basic.service.UserService;
import com.ttm.basic.utils.AESUtils;
import com.ttm.basic.utils.CookieHelper;
import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liguoqing on 2016/6/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes ={StartApplication.class })
public class TestUserService {

    @Resource
    private UserService userService;

    @Test
    public void testSaveOrUpdate(){
        User model = new User();
        model.setUserName("test-update");
        model.setNickName("测试更新");
        model.setIdentityCardNo("431111232394857383");
        model.setIsDelete(false);
        userService.saveOrUpdate(model);
    }

    @Test
    public void testUserToken()throws Exception{
        //"userId,version,mils,appId,deviceId"
        String str = "1233"+"|1.0"+"|"+System.currentTimeMillis()+"|1001"+"|"+"43:25:78:50:93:38";
        System.out.println(str);
        String secretKey = AESUtils.initKey();
        System.out.println(secretKey);
        String cookie = CookieHelper.INSTANCE.cookieUtils.encryptCookie(secretKey, str);
        System.out.println(cookie);
        str = CookieHelper.INSTANCE.cookieUtils.decryptCookie(secretKey,cookie);
        System.out.println(str);
    }

    @Autowired
    private VelocityEngine velocityEngine;

    @Test
    public void velocityTest(){
        Map<String,Object> model = new HashMap<>();
        User user = new User();
        user.setUserName("Ha测试");
        user.setIdentityCardNo("123@gmail.com");
        model.put("user",user);
        System.out.println(VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/register.vm", "UTF-8", model));
    }

}

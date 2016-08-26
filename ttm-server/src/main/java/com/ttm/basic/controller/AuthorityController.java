package com.ttm.basic.controller;

import com.ttm.basic.dal.model.User;
import com.ttm.basic.service.UserService;
import com.ttm.basic.utils.CookieHelper;
import com.ttm.basic.utils.CookieUtils;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by liguoqing on 2016/6/6.
 */
@RestController
@Configuration
public class AuthorityController {

    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public User login(@RequestParam ("userName") String userName,
                       @RequestParam ("userPassword") String userPassword,
                       HttpServletRequest request,
                       HttpServletResponse response){
        User user = userService.userLogin(userName,userPassword);
        if(null != user){
            CookieUtils cookieUtils = CookieHelper.INSTANCE.cookieUtils;
            cookieUtils.setRequest(request);
            cookieUtils.setResponse(response);
            cookieUtils.addCookie("_mtk",cookieUtils.encryptCookie("nUVFYmEAMjpyXL7KZSX1ow==",user.getUserName()+"|"+"1001"),60);
        }
        return user;
    }

}

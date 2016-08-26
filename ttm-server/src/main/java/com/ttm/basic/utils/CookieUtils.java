package com.ttm.basic.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Created by liguoqing on 2016/6/2.
 */
public class CookieUtils {

    private static final Logger logger = LoggerFactory.getLogger(CookieUtils.class);

    private HttpServletResponse response;

    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    private HttpServletRequest request;

    /**
     * 创建会话cookie
     *
     * @param name
     * @param value
     * @param expired [秒]
     */
    public void addCookie(String name, String value, int expired) {
        if (null == response) return;
        Cookie cookie = new Cookie(name, value);
        cookie.setMaxAge(expired);
        logger.info("addCookie value:{},expired:{}",value,expired);
        response.addCookie(cookie);
    }

    /**
     * 获取会话cookie的值
     *
     * @param name
     * @return
     */
    public String getCookieValue(String name) {
        Cookie cookie = getCookie(name);
        if (null != cookie) {
            return cookie.getValue();
        }
        return null;
    }

    /**
     * 获取会话cookie
     *
     * @param name
     * @return
     */
    public Cookie getCookie(String name) {
        if (null == request) return null;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if (null != cookie.getName() && !"".equals(cookie.getName()) && name.equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * 清除会话cookie
     * @param name
     */
    public void clearCookie(String name){
        if (null == request || null == response) return;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                if(null != cookie.getName() && !"".equals(cookie.getName()) && name.equals(cookie.getName())){
                    Cookie cookie_ = new Cookie(cookie.getName(),null);
                    cookie_.setMaxAge(0); //0 不记录cookie,-1 关闭浏览器cookie失效
                    cookie_.setPath(cookie.getPath());
                    cookie_.setDomain(cookie.getDomain());
                    response.addCookie(cookie_);
                }
            }
        }
    }

    /**
     * 清除所有会话cookie
     */
    public void clearAllCookie(){
        if (null == request) return;
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                Cookie cookie_ = new Cookie(cookie.getName(),null);
                cookie_.setMaxAge(0);
                cookie_.setPath(cookie.getPath());
                cookie_.setDomain(cookie.getDomain());
                response.addCookie(cookie_);
            }
        }
    }

    /**
     * decrypt 会话Cookie
     * @param secretKey
     * @param data
     * @return
     */
    public String decryptCookie(String secretKey,String data){
        try {
            return java.net.URLDecoder.decode(new String(Base64.getDecoder().decode(AESUtils.decrypt(secretKey, data))), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * encrypt 会话Cookie
     * @param secretKey
     * @param data
     * @return
     */
    public String encryptCookie(String secretKey,String data){
        try {
            return AESUtils.encrypt(secretKey,new String(Base64.getEncoder().encode(java.net.URLEncoder.encode(data,"UTF-8").getBytes())));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}

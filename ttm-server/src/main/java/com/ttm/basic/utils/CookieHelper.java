package com.ttm.basic.utils;

/**
 * Created by liguoqing on 2016/6/2.
 */
public enum CookieHelper {

    INSTANCE;

    public CookieUtils cookieUtils;

    private CookieHelper(){
        cookieUtils = new CookieUtils();
    }

}

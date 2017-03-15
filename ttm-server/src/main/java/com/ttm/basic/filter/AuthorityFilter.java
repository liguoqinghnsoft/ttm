package com.ttm.basic.filter;

import com.ttm.basic.utils.CookieHelper;
import com.ttm.basic.utils.CookieUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by liguoqing on 2016/6/2.
 */
public class AuthorityFilter implements Filter{

    private Logger logger = LoggerFactory.getLogger(AuthorityFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("AuthorityFilter init()");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        logger.info("AuthorityFilter doFilter()");
        CookieUtils cookieUtils = CookieHelper.INSTANCE.cookieUtils;
        cookieUtils.setRequest((HttpServletRequest)servletRequest);
        cookieUtils.setResponse((HttpServletResponse) servletResponse);
        Cookie cookie = cookieUtils.getCookie("_mtk");
        if(null == cookie || null == cookie.getValue()){
            throw new RuntimeException("用户未登录");
        }
        String value = cookieUtils.decryptCookie("nUVFYmEAMjpyXL7K",cookie.getValue());
        if(null != value){
            String[] res = value.split("\\|");
            logger.info("doFilter res[0]:{}",res[0]);
            ThreadContext.put(res[0]);
        }
        filterChain.doFilter(servletRequest,servletResponse);
    }

    @Override
    public void destroy() {
        logger.info("AuthorityFilter destroy()");
    }
}

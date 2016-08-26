package com.ttm.basic.config;

import com.alibaba.dubbo.config.spring.ReferenceBean;
import com.ttm.basic.api.service.UserHttpService;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.context.annotation.Bean;

/**
 * Created by liguoqing on 2016/6/7.
 */
public class DubboConsumer extends DubboConfig{

    @Bean(name = "userHttpService",autowire = Autowire.BY_NAME)
    public ReferenceBean<UserHttpService> userHttpServiceReferenceBean(){

        return null;
    }

}

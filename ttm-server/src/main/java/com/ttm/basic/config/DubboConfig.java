package com.ttm.basic.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

/**
 * Created by liguoqing on 2016/6/7.
 */
public class DubboConfig {

    public static final Logger logger = LoggerFactory.getLogger(DubboConfig.class);

    @Value("${dubbo.application.name}")
    private String appName;
    @Value("${dubbo.register.address}")
    private String registerUrl;
    @Value("${dubbo.protocol.name}")
    private String protocol;
    @Value("${dubbo.protocol.port}")
    private int port;

    @Bean
    public RegistryConfig registry(){
        logger.info("DubboConfig.registry appName:{},registerUrl:{},protocol:{}",appName,registerUrl,protocol);
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setPort(port);
        registryConfig.setAddress(registerUrl);
        registryConfig.setProtocol(protocol);
        return registryConfig;
    }

    @Bean
    public ApplicationConfig application(){
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(appName);
        return applicationConfig;
    }

    @Bean
    public MonitorConfig monitorConfig(){
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }

}

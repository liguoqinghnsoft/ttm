package com.ttm.basic.config;

import com.alibaba.dubbo.config.ProviderConfig;
import com.alibaba.dubbo.config.spring.ServiceBean;
import com.ttm.basic.api.service.UserHttpService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liguoqing on 2016/6/7.
 */
@Configuration
public class DubboProvider extends DubboConfig{

    @Value("${dubbo.export.version}")
    private String version;

    @Value("${dubbo.connect.timeout}")
    private int timeout;

    @Value("${dubbo.connect.retries}")
    private int retries;

    @Bean
    public ProviderConfig providerConfig(){
        ProviderConfig providerConfig = new ProviderConfig();
        providerConfig.setMonitor(monitorConfig());
        return providerConfig;
    }

//    @Bean
//    public ServiceBean exportUserHttpService(UserHttpService userHttpService){
//        ServiceBean bean = new ServiceBean();
//        bean.setVersion(version);
//        bean.setInterface(UserHttpService.class.getName());
//        bean.setRef(userHttpService);
//        bean.setTimeout(timeout);
//        bean.setRetries(retries);
//        bean.setProxy("javassist");
//        return bean;
//    }

}

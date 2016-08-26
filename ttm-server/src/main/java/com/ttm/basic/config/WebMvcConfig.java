package com.ttm.basic.config;

import com.ttm.basic.filter.AuthorityFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.Validator;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by liguoqing on 2016/6/2.
 */
@Configuration
@ComponentScan(basePackages = {"com.ttm.basic.controller","com.scala.basic.controller"})
@EnableAutoConfiguration
public class WebMvcConfig extends WebMvcConfigurerAdapter{


    @Bean
    public FilterRegistrationBean authorityFilter(){
        AuthorityFilter filter = new AuthorityFilter();
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(filter);
        bean.setName("authorityFilter");
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/hello/*");
        bean.setUrlPatterns(urlPatterns);
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:messages"); //（※２）
        messageSource.setCacheSeconds(0);
        messageSource.setDefaultEncoding("UTF-8");
        //System.out.println("messageSource->"+messageSource.getMessage("age.max",null, Locale.CHINA));
        return messageSource;
    }

    @Bean
    public LocalValidatorFactoryBean validator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.setValidationMessageSource(messageSource());
        return localValidatorFactoryBean;
    }

    @Override
    public Validator getValidator() {  //（※３）
        return validator();
    }



}

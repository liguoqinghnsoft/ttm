package com.ttm.basic.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;


/**
 * Created by liguoqing on 2016/5/12.
 */
@Component
public class MailConfig {

    @Value("${mail.host}")
    private String host;
    @Value("${mail.protocol}")
    private String protocol;
    @Value("${mail.port}")
    private int port;
    @Value("${mail.username}")
    private String username;
    @Value("${mail.password}")
    private String password;

    @Bean
    public JavaMailSender mailSender(){
        System.out.println("init->"+host);
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost(host);
        sender.setPort(port);
        sender.setProtocol(protocol);
        sender.setUsername(username);
        sender.setPassword(password);
        return sender;
    }



}

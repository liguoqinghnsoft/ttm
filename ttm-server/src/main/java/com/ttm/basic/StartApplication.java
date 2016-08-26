package com.ttm.basic;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * Created by liguoqing on 2016/4/16.
 */
@SpringBootApplication
@ComponentScan
@EnableAutoConfiguration
public class StartApplication {

     public static void main(String args[]){
         SpringApplication.run(StartApplication.class,args);
     }



}


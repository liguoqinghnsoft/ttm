package com.scala.baisc.controller

import java.util

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RestController, RequestMapping, RequestMethod}

/**
 * Created by liguoqing on 2016/8/5.
 */
@RequestMapping(value=Array("myscala"))
@Controller
@Configuration
class HelloController {

  @RequestMapping(value=Array("/hello"),method = Array(RequestMethod.GET))
  def hello(name:String):String={
    println(name)
//    return "hello,"+name;
    val listIds = Array("110","111","112");
    for(id <- listIds){

    }
    return "";
  }

}

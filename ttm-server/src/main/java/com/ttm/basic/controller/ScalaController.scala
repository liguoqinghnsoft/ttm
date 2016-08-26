package com.ttm.basic.controller

import org.springframework.context.annotation.Configuration
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod}

/**
  * Created by liguoqing on 2016/8/5.
  */
@RequestMapping(value=Array("scala"))
 @Controller
 @Configuration
class ScalaController {

   @RequestMapping(value=Array("/hello"),method = Array(RequestMethod.GET))
   def hello(name:String):String={
     println(name)
     return "hello,"+name;
   }

 }

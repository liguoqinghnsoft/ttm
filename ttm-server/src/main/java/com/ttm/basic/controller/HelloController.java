package com.ttm.basic.controller;

import com.ttm.basic.api.dto.DataObjects;
import com.ttm.basic.api.dto.ReactorDTO;
import com.ttm.basic.api.dto.DataObject;
import com.ttm.basic.api.dto.UserDTO;
import com.ttm.basic.api.validator.DataObjectValidator;
import com.ttm.basic.dal.model.User;
import com.ttm.basic.service.UserService;
import com.ttm.basic.utils.ValidatorUtils;
import org.apache.velocity.app.VelocityEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.velocity.VelocityEngineUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import reactor.core.Reactor;
import reactor.event.Event;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import javax.validation.Valid;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by liguoqing on 2016/4/18.
 */
@RestController
@Configuration
@RequestMapping("/hello")
public class HelloController {

//    @Value("${hello.test.work}")
//    private String workValue;

//    @InitBinder
//    protected  void initBinder(WebDataBinder webDataBinder){
//        webDataBinder.setValidator(new DataObjectValidator());
//    }

    @RequestMapping("/doRegister")
    public Object doRegister(@Validated DataObject dataObject,BindingResult bindingResult){
        System.out.println(dataObject);
        if(bindingResult.hasErrors()){
            System.out.println("doRegister.error....");
            return bindingResult.getFieldError().getDefaultMessage();
        }
        return "success";
    }

    @RequestMapping("/doRegisters")
    public Object doRegisters(@Valid DataObjects dataObject,Errors errors){
        System.out.println(dataObject);
        if(errors.hasErrors()){
            System.out.println("doRegisters.error....");
            return errors.getFieldError().getDefaultMessage();
        }
        return "success";
    }

    @RequestMapping("/validator")
    public Object validator(@Valid UserDTO userDTO,Errors errors){
        System.out.println(userDTO);
        if(errors.hasErrors()){
            System.out.println("doRegisters.error....");
            return errors.getFieldError().getDefaultMessage();
        }
        return "success";
    }

    @Resource
    private Reactor rootReactor;

    @RequestMapping("/hello")
    public String springBoot(){
        return "Hello,Spring Boot!";
    }

    @RequestMapping("/reactor")
    public String springReactor() {
        ReactorDTO reactorDTO = new ReactorDTO();
        reactorDTO.setId(1l);
        reactorDTO.setName("reactorID_0");
        System.out.println(reactorDTO);
        rootReactor.notify("TEST_REACTOR", Event.wrap(reactorDTO));
        return "Hello,Spring Reactor!";
    }

    @RequestMapping("/testService")
    public void testService() {
        //testService.testServiceSayOK();
        //System.out.println("workValue:"+workValue);
        validatorUtils.checkEmail("test@123.com");
    }

    @Resource
    private UserService userService;

    @Resource
    private ValidatorUtils validatorUtils;

    @RequestMapping("/saveOrUpdateUser")
    public void saveUser(){
        User model = new User();
        //model.setPkId(1l);
        model.setUserName("test-update");
        model.setNickName("测试更新");
        model.setIdentityCardNo("431111232394857383");
        model.setIsDelete(false);
        userService.saveOrUpdate(model);
        System.out.println("pkId->"+model.getPkId());
    }

    @RequestMapping("/queryUser")
    public Object query(){
        User user = new User();
        return userService.query(1,10,user);
    }

    @RequestMapping("/deleteUser")
    public void deleteUser(){
        userService.delete(1L);
    }

    @Resource
    private JavaMailSender mailSender;

    @RequestMapping("/sendEmail")
    public void sendEmail(@RequestParam(required = true) String mailTo){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);
            helper.setFrom("liguoqinghnsoft@163.com");
            helper.setTo(mailTo);
            helper.setSubject("JUST 测试");
            //helper.setText("测试邮件发送");
            File file = new File("E:\\基因检测应用\\use_geneService.png");
            helper.addAttachment(file.getName(),file);
            helper.addInline(file.getName(), file);
            Map<String,Object> model = new HashMap<>();
            User user = new User();
            user.setUserName("Ha测试");
            user.setIdentityCardNo("123@gmail.com");
            model.put("user",user);
            String text = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "velocity/register.vm", "UTF-8", model);
            helper.setText(text, true); //html
            //helper.setText(text); //text
            mailSender.send(mimeMessage);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    @Autowired
    private VelocityEngine velocityEngine;



}

package com.ma.jpa.controller;

import com.ma.jpa.bean.JpaPageTemplateBean;
import com.ma.jpa.bean.ResultTemplateBean;
import com.ma.jpa.entity.TUser;
import com.ma.jpa.service.JpaTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author xiaoma
 * @description ...
 * @date 2020-03-25 22:49:52
 */
@RestController
@RequestMapping("/jpa/")
public class JpaTestController {

    @Autowired
    JpaTestService jpaTestService;

    @PostMapping("jpaTest")
    public TUser findPhoneNumberRuleById(@RequestBody Map<String,Object> map){
        return jpaTestService.findPhoneNumberRuleById(map);
    };

    @PostMapping("resultTemplateBeanTest")
    public ResultTemplateBean resultTemplateBeanTest(@RequestBody Map<String,Object> map){
        return jpaTestService.resultTemplateBeanTest(map);
    };
    @PostMapping("jpaPageTemplateBeanTest")
    public ResultTemplateBean jpaPageTemplateBeanTest(@RequestBody Map<String,Object> map){
        return jpaTestService.jpaPageTemplateBeanTest(map);
    };
}

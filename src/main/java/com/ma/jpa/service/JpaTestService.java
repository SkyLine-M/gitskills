package com.ma.jpa.service;

import com.ma.jpa.bean.JpaPageTemplateBean;
import com.ma.jpa.bean.ResultTemplateBean;
import com.ma.jpa.entity.TUser;

import java.util.Map;

/**
 * @author xiaoma
 * @description ...
 * @date 2020-03-27 08:40:22
 */
public interface JpaTestService {
    TUser findPhoneNumberRuleById(Map map);

    ResultTemplateBean resultTemplateBeanTest(Map<String, Object> map);
    ResultTemplateBean jpaPageTemplateBeanTest(Map<String,Object> map);

}

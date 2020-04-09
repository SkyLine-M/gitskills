package com.ma.jpa.service.Impl;

import com.ma.jpa.bean.JpaPageTemplateBean;
import com.ma.jpa.bean.ResultTemplateBean;
import com.ma.jpa.common.query.JpaSpec;
import com.ma.jpa.entity.TUser;
import com.ma.jpa.repository.JpaTestRepository;
import com.ma.jpa.service.JpaTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author xiaoma
 * @description ...
 * @date 2020-03-27 08:40:44
 */
@Service
public class JpaTestServiceImpl implements JpaTestService {

    @Autowired
    JpaTestRepository jpaTestRepository;

    @Override
    public TUser findPhoneNumberRuleById(Map map) {
        return null;
    }

    @Override
    public ResultTemplateBean resultTemplateBeanTest(Map<String, Object> map) {
        return ResultTemplateBean.success("成功了",111);
    }

    @Override
    public ResultTemplateBean jpaPageTemplateBeanTest(Map<String,Object> map) {
        JpaSpec userSpec = (predicates, root, criteriaBuilder)->{
            predicates.add(criteriaBuilder.like(root.get("name"),"%sdfsf%"));
            //.....
        };

        //查询
        List<TUser> tUsers = jpaTestRepository.findAll(userSpec);
        System.out.println(tUsers);

        //单条件排序,多个条件只能同时升序或降序（按id和name降序）:注意坑：下面的page是从0开始算的，所以-1才是我们人类逻辑
//        PageRequest pageRequest = PageRequest.of(1,2, Sort.Direction.ASC,"id");
        List<Sort.Order> orders=new ArrayList< Sort.Order>();
        orders.add( new Sort.Order(Sort.Direction. ASC, "id"));
        orders.add( new Sort.Order(Sort.Direction. DESC, "name"));
        PageRequest pageRequest = PageRequest.of(0,4, Sort.by(orders));
        Page singleSort = jpaTestRepository.findAll(userSpec, pageRequest);

        JpaPageTemplateBean convert = JpaPageTemplateBean.convert(singleSort);
        return ResultTemplateBean.success("成功了",convert);
    }
}

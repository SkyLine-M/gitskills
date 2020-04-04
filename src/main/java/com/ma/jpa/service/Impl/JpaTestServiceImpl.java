package com.ma.jpa.service.Impl;

import com.ma.jpa.entity.TUser;
import com.ma.jpa.repository.JpaTestRepository;
import com.ma.jpa.service.JpaTestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}

package com.ma.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import javax.persistence.*;

/**
 * @author xiaoma
 * @description ...
 * @date 2020-03-25 23:00:16
 */
@Data
//标明这是一个实体类
@Entity
//指定数据库名，如果类名和数据库名一样(_a在类命中为A)，那么可以不写
@Table(name = "t_user")
public class TUser {
    @Id                  //这个字段为主键
    @Column              //标识实体类中属性与数据表中字段的对应关系，属性与字段名字一样时可以省略
    @GeneratedValue(generator = "uuid")         //主键生成策略
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column
    private String name;
    @Column
    private Integer age;
}

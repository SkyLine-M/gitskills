package com.ma.jpa.entity;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * @author xiaoma
 * @description ...
 * @date 2020-03-29 21:19:12
 */
@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(generator = "uuid")         //主键生成策略
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String userName;

    //多个用户对应一个角色（多对一）
    @ManyToOne
    @JoinColumn(name = "roles_id")   //name:数据库列名
    private Role role;

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                '}';
    }
}

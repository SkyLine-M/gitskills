package com.ma.jpa.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author xiaoma
 * @description ...
 * @date 2020-03-29 21:17:54
 */
@Getter
@Setter
@EqualsAndHashCode
@Entity
@Table(name = "t_role")
public class Role {
    @Id
    @GeneratedValue(generator = "uuid")         //主键生成策略
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String roleName;

    //一个角色被多个用户使用（一对多）
    @OneToMany(mappedBy = "role", cascade = CascadeType.ALL, fetch = FetchType.EAGER)   //关系由用户实体类中的role维护
    private Set<User> users = new HashSet<User>();

    @Override
    public String toString() {
        return "Role{" +
                "id='" + id + '\'' +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}

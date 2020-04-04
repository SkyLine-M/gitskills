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
 * @date 2020-03-29 20:33:52
 */

@Data
@Entity
@Table(name = "t_grade")
public class Grade {
    @Id
    @GeneratedValue(generator = "uuid")         //主键生成策略
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String gradeName;

    @OneToOne(mappedBy = "grade")              //mappedBy：维护外键关系关联，其中 mappedBy = "grade"，关系则由学生实体类中的grade维护
    @ToString.Exclude                           //排除此字段的toString，不然打印对象会报错
    private Student student;
}

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
 * @date 2020-03-29 20:36:05
 */

@Entity
@Data
@Table(name = "t_student")
public class Student {
    @Id
    @GeneratedValue(generator = "uuid")         //主键生成策略
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String studentName;

    @OneToOne(cascade = CascadeType.PERSIST) //一对一,cascade表示级联
    @JoinColumn(name = "grade_id")           //外键列
    @ToString.Exclude                        //排除此字段的toString，不然打印对象会报错
    private Grade grade;
}

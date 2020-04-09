package com.ma.jpa.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 假期
 * @author ceven
 *
 */
@Data
@Entity
public class Holiday  {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Column(length = 64)
    private String holidayId;
    @Column
    private String name; //假期名称 - 中文/本地语言
    @Column
    private String nameEn; //假期英文名称
    @Column
    private Long startTime; //假期开始时间 00:00:00到23:59:59
    @Column
    private Long endTime; //假期结束时间 00:00:00到23:59:59
    @Column
    private Integer delayDays;
    @Column(length = 64)
    private String merchantId;
    @Column(length = 64)
    private String createdBy;//创建人    
    @Column
    private Long creationTime;//
    @Column
    private Long lastUpdatedTime;
    @Column
    private Boolean deleteFlag;//删除标记
}

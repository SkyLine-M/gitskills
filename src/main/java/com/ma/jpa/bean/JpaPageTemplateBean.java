package com.ma.jpa.bean;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @description ...
 * @date 2020-04-07 09:36:19
 * @author xiaoma
 */
@Data
@Accessors(chain = true)
public class JpaPageTemplateBean<T> {
    /** 总条数 */
    private Long totalCount;
    /** 总页数 */
    private Integer totalPage;
    /** 当前条数 */
    private Integer currentCount;
    /** 当前页数 */
    private Integer currentPage;
    /** 每页数量 */
    private Integer size;
    /** 排序规则 */
    private String sort;
    /** 数据 */
    private List<T> entity;


    /**
     * @description 注意，当前页面CurrentPage，系统是从0开始
     * @param page 1
     * @return com.ma.jpa.bean.JpaPageTemplateBean
     *
     * @date 2020/4/8 11:46
     * @author xiaoma
     */
    @SuppressWarnings("unchecked")
    public static JpaPageTemplateBean convert(Page page) {
        return new JpaPageTemplateBean()
                .setTotalCount(page.getTotalElements())
                .setTotalPage(page.getTotalPages())
                .setCurrentCount(page.getNumberOfElements())
                .setCurrentPage(page.getNumber()+1)
                .setSize(page.getSize())
                .setSort(page.getSort().toString())
                .setEntity(page.getContent());
    }
}

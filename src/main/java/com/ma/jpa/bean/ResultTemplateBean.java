package com.ma.jpa.bean;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @description 返回结果集封装   return ResultTemplateBean.success("111",map);
 *                               return ResultTemplateBean.error("失败了");
 * @date 2020-04-07 02:51:13
 * @author xiaoma
 */
@Data
@Accessors(chain = true)
public class ResultTemplateBean<T> implements Serializable {

    private static final long serialVersionUID = -8143566284567789026L;
    /** 状态码(不是HTTP的状态，只是一种信息提示) */
    private int code;
    /** 信息提示 */
    private String message;
    /** 内容 */
    private T data;

    /**
     * @description 成功返回调用方法
     * @param message 1
     * @param data 2
     * @return com.ma.exceptiontest.common.bean.ResultTemplateBean<T>
     *
     * @date 2020/4/3 9:34
     * @author xiaoma
     */

    public static <T> ResultTemplateBean<T> success(String message, T data) {
        return new ResultTemplateBean<T>().setCode(200).setMessage(message).setData(data);
    }

    /**
     * @description 失败返回调用方法
     *                  ：这个方法很少用，因为只要程序出现异常，就会被ExceptionControllerAdvice拦截并返回非200状态码
     *                  ：所以它的适用情况是，后台出错，但是前端想要状态为200的HTTP返回
     * @param message 1
     * @return com.ma.exceptiontest.common.bean.ResultTemplateBean<T>
     *
     * @date 2020/4/6 19:53
     * @author xiaoma
     */
    public static <T> ResultTemplateBean<T> error(String message) {
        return new ResultTemplateBean<T>().setCode(500).setMessage(message);
    }
}

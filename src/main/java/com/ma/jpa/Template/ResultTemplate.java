package com.ma.jpa.Template;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaoma
 * @description ...
 * @date 2020-03-25 17:03:06
 */
public class ResultTemplate {
    /**
     * @description 结果集封装
     * @param result     example:"true"
     * @param returnCode example:"200"
     * @param message    example:"提交成功"
     * @param data       example:[{"serviceId":176884},{}...]
     * @return java.util.Map
     *
     * @date 2020/3/25 17:13
     * @author xiaoma
     */
    public static Map getResultTemplate(String result , String returnCode , String message , Object data) {
        Map<String,Object> resultMap = new HashMap<>(16);
        resultMap.put("result",result);
        resultMap.put("returnCode",returnCode);
        resultMap.put("message",message);
        resultMap.put("data",data);
        return resultMap;

    }
}

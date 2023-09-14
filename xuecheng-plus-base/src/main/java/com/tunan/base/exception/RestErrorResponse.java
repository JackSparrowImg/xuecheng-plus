package com.tunan.base.exception;

import java.io.Serializable;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.base.exception
 * @className: RestErrorResponse
 * @author: Jack
 * @description: 错误响应参数包装
 * @date: 2023/9/13 20:56
 * @version: 1.0
 */

public class RestErrorResponse implements Serializable {

    private String errMessage;

    public RestErrorResponse(String errMessage){
        this.errMessage= errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public void setErrMessage(String errMessage) {
        this.errMessage = errMessage;
    }
}

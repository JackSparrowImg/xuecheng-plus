package com.tunan.base.exception;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.base.exception
 * @className: XueChengPlusException
 * @author: Jack
 * @description: 学成在线项目异常类
 * @date: 2023/9/13 20:55
 * @version: 1.0
 */

public class XueChengPlusException extends RuntimeException {

    private String errMessage;

    public XueChengPlusException() {
        super();
    }

    public XueChengPlusException(String errMessage) {
        super(errMessage);
        this.errMessage = errMessage;
    }

    public String getErrMessage() {
        return errMessage;
    }

    public static void cast(CommonError commonError){
        throw new XueChengPlusException(commonError.getErrMessage());
    }
    public static void cast(String errMessage){
        throw new XueChengPlusException(errMessage);
    }

}


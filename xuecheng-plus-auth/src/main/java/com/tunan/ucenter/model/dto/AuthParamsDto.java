package com.tunan.ucenter.model.dto;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Mr.Jack
 * @version 1.0
 * @description 认证用户请求参数
 * @date 2023/10/14 10:56
 */
@Data
public class AuthParamsDto {

    private String username; //用户名
    private String password; //域  用于扩展
    private String cellphone;//手机号
    private String checkcode;//验证码
    private String checkcodekey;//验证码key
    private String authType; // 认证的类型   password:用户名密码模式类型    sms:短信模式类型
    private Map<String, Object> payload = new HashMap<>();//附加数据，作为扩展，不同认证类型可拥有不同的附加数据。如认证类型为短信时包含smsKey : sms:3d21042d054548b08477142bbca95cfa; 所有情况下都包含clientId


}

/*
* 1. 统一认证入口
* 包括密码认证   微信扫码认证   手机验证码认证
* 统一认证请求参数
* */

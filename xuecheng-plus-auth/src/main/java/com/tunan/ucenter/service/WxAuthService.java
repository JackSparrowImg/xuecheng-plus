package com.tunan.ucenter.service;

import com.tunan.ucenter.model.po.XcUser;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.ucenter.service
 * @className: WxAuthService
 * @author: Jack
 * @description: 微信扫码接口
 * @date: 2023/10/31 19:46
 * @version: 1.0
 */

public interface WxAuthService {
    /**
     * 微信扫码认证，申请令牌，携带令牌查询用户信息、保存用户信息
     * @param code
     * @return
     */
    public XcUser wxAuth(String code);
}

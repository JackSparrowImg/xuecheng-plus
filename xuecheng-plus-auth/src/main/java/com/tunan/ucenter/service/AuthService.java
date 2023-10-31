package com.tunan.ucenter.service;

import com.tunan.ucenter.model.dto.AuthParamsDto;
import com.tunan.ucenter.model.dto.XcUserExt;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.ucenter.service
 * @className: AuthService
 * @author: Jack
 * @description: TODO
 * @date: 2023/10/22 15:34
 * @version: 1.0
 */

public interface AuthService {


    /**
     * @description 认证方法
     * @param authParamsDto 认证参数
     * @return com.tunan.ucenter.model.po.XcUser 用户信息
     * @author Mr.Jack
     * @date 2023/10/22 15:34
     */
    XcUserExt execute(AuthParamsDto authParamsDto);
}

package com.tunan.auth.controller;

import com.tunan.ucenter.model.po.XcUser;
import com.tunan.ucenter.service.WxAuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.auth.controller
 * @className: WxLoginController
 * @author: Jack
 * @description: TODO
 * @date: 2023/10/31 17:31
 * @version: 1.0
 */

@Slf4j
@Controller
public class WxLoginController {
    @Autowired
    WxAuthService wxAuthService;


    @RequestMapping("/wxLogin")
    public String wxLogin(String code, String state) throws IOException {
        log.debug("微信扫码回调,code:{},state:{}",code,state);
        //请求微信申请令牌，拿到令牌查询用户信息，将用户信息写入本项目数据
        XcUser xcUser = wxAuthService.wxAuth(code);


        if(xcUser==null){
            return "redirect:http://www.tunan.cn/error.html";
        }
        String username = xcUser.getUsername();
        return "redirect:http://www.tunan.cn/sign.html?username="+username+"&authType=wx";
    }
}

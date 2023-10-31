package com.tunan.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.tunan.ucenter.mapper.XcUserMapper;
import com.tunan.ucenter.model.dto.AuthParamsDto;
import com.tunan.ucenter.model.dto.XcUserExt;
import com.tunan.ucenter.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.ucenter.service.impl
 * @className: UserServiceImpl
 * @author: Jack
 * @description: TODO
 * @date: 2023/10/15 21:00
 * @version: 1.0
 */

@Slf4j
@Component
public class UserServiceImpl implements UserDetailsService {
    @Autowired
    XcUserMapper xcUserMapper;
    
    @Autowired
    ApplicationContext applicationContext;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        //将传入的json转成AuthParamsDto
        AuthParamsDto authParamsDto = null;
        try {
            authParamsDto = JSON.parseObject(s, AuthParamsDto.class);
        }catch (Exception e){
            throw new RuntimeException("请求认证参数不符合要求");
        }


        //认证类型，有password，wx。。。。
        String authType = authParamsDto.getAuthType();

        //根据认证类型从sping容器中取到对应的bean
        String beanName = authType + "_authservice";
        AuthService authService = applicationContext.getBean(beanName, AuthService.class);

        //调用统一execute方法完成认证
        XcUserExt xcUserExt = authService.execute(authParamsDto);

        UserDetails userPrincipal = getUserPrincipal(xcUserExt);

        return userPrincipal;
    }

    /**
     * @description 查询用户信息
     * @param user  用户id，主键
     * @return com.xuecheng.ucenter.model.po.XcUser 用户信息
     * @author Mr.Jack
     * @date 2023/10/22 12:19
     */
    public UserDetails getUserPrincipal(XcUserExt user){
        //用户权限,如果不加报Cannot pass a null GrantedAuthority collection
        String[] authorities = {"p1"};
        String password = user.getPassword();
        //为了安全在令牌中不放密码
        user.setPassword(null);
        //将user对象转json
        String userString = JSON.toJSONString(user);
        //创建UserDetails对象
        UserDetails userDetails = User.withUsername(userString).password(password ).authorities(authorities).build();
        return userDetails;
    }
}

package com.tunan.ucenter.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tunan.ucenter.feignclient.CheckCodeClient;
import com.tunan.ucenter.mapper.XcUserMapper;
import com.tunan.ucenter.model.dto.AuthParamsDto;
import com.tunan.ucenter.model.dto.XcUserExt;
import com.tunan.ucenter.model.po.XcUser;
import com.tunan.ucenter.service.AuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.ucenter.service.impl
 * @className: PasswordAuthServiceImpl
 * @author: Jack
 * @description: 账号名密码方式
 * @date: 2023/10/22 15:36
 * @version: 1.0
 */
@Service("password_authservice")
public class PasswordAuthServiceImpl implements AuthService {

    @Autowired
    XcUserMapper xcUserMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    CheckCodeClient checkCodeClient;

    @Override
    public XcUserExt execute(AuthParamsDto authParamsDto) {

        //账号
        String username = authParamsDto.getUsername();

        //校验验证码
        String checkcode = authParamsDto.getCheckcode();
        String checkcodekey = authParamsDto.getCheckcodekey();

        if(StringUtils.isBlank(checkcodekey) || StringUtils.isBlank(checkcode)){
            throw new RuntimeException("验证码为空");

        }
        //远程调用验证码服务接口去校验验证码
        Boolean verify = checkCodeClient.verify(checkcodekey, checkcode);

        if(verify == null || !verify){
            throw new RuntimeException("验证码输入错误");
        }

        XcUser user = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, username));
        if(user==null){
            //返回空表示用户不存在
            throw new RuntimeException("账号不存在");
        }
        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(user,xcUserExt);
        //校验密码
        //取出数据库存储的正确密码
        String passwordDb  =user.getPassword();
        String passwordForm = authParamsDto.getPassword();
        boolean matches = passwordEncoder.matches(passwordForm, passwordDb);
        if(!matches){
            throw new RuntimeException("账号或密码错误");
        }
        return xcUserExt;
    }
}

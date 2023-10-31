package com.tunan.ucenter.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.tunan.ucenter.mapper.XcUserMapper;
import com.tunan.ucenter.mapper.XcUserRoleMapper;
import com.tunan.ucenter.model.dto.AuthParamsDto;
import com.tunan.ucenter.model.dto.XcUserExt;
import com.tunan.ucenter.model.po.XcUser;
import com.tunan.ucenter.model.po.XcUserRole;
import com.tunan.ucenter.service.AuthService;
import com.tunan.ucenter.service.WxAuthService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

/**
 * @projectName: xuecheng-plus
 * @package: com.tunan.ucenter.service.impl
 * @className: WxAuthServiceImpl
 * @author: Jack
 * @description: 微信扫码认证
 * @date: 2023/10/22 15:37
 * @version: 1.0
 */

@Service("wx_authservice")
public class WxAuthServiceImpl implements AuthService, WxAuthService {


    @Autowired
    XcUserMapper xcUserMapper;

    @Value("${weixin.appid}")
    String appid;

    @Value("${weixin.secret}")
    String secret;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WxAuthServiceImpl currentPorxy;

    @Override
    public XcUserExt execute(AuthParamsDto authParamsDto) {

        String username = authParamsDto.getUsername();
        XcUser xcUser = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getUsername, username));

        if(xcUser == null){
            throw new RuntimeException("用户不存在");
        }


        XcUserExt xcUserExt = new XcUserExt();
        BeanUtils.copyProperties(xcUser,xcUserExt);


        return xcUserExt;
    }

    @Override
    public XcUser wxAuth(String code) {
        //申请令牌
        Map<String, String> access_token_map = getAccess_token(code);
        //访问令牌
        String access_token = access_token_map.get("access_token");
        String openid = access_token_map.get("openid");


        // 携带令牌查询用户信息
        Map<String, String> userinfo = getUserinfo(access_token,openid);


        // 保存用户信息
        if(userinfo==null){
            return null;
        }
        //将用户信息保存到数据库
        XcUser xcUser = currentPorxy.addWxUser(userinfo);
        return xcUser;

    }

    /**
     * 携带授权码申请令牌
     *
     * https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorization_code
     *
     * {
     * "access_token":"ACCESS_TOKEN",
     * "expires_in":7200,
     * "refresh_token":"REFRESH_TOKEN",
     * "openid":"OPENID",
     * "scope":"SCOPE",
     * "unionid": "o6_bmasdasdsad6_2sgVt7hMZOPfL"
     * }
     *
     * @param code
     * @return
     */

    private Map<String,String> getAccess_token(String code){
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=%s&secret=%s&code=%s&grant_type=authorization_code";
        //最终的请求路径
        String format = String.format(url, appid, secret, code);

        //远程调用url
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.POST, null, String.class);
        //获取相应的结果
        String result = exchange.getBody();
        //将result转成map
        Map<String, String> map = JSON.parseObject(result, Map.class);
        return map;
    }

    /**
     * 携带令牌查询用户信息
     *
     * https://api.weixin.qq.com/sns/userinfo?access_token=ACCESS_TOKEN&openid=OPENID
     *
     * {
     * "openid":"OPENID",
     * "nickname":"NICKNAME",
     * "sex":1,
     * "province":"PROVINCE",
     * "city":"CITY",
     * "country":"COUNTRY",
     * "headimgurl": "https://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
     * "privilege":[
     * "PRIVILEGE1",
     * "PRIVILEGE2"
     * ],
     * "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
     *
     * }
     *
     * @param access_token
     * @param openid
     * @return
     */
    private Map<String,String> getUserinfo(String access_token,String
            openid){
        String url_template = "https://api.weixin.qq.com/sns/userinfo?access_token=%s&openid=%s";
        String url = String.format(url_template,access_token,openid);
        ResponseEntity<String> exchange = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        String result = new String(exchange.getBody().getBytes(StandardCharsets.ISO_8859_1),StandardCharsets.UTF_8);
        Map<String,String> map = JSON.parseObject(result, Map.class);
        return map;
    }

    @Autowired
    XcUserRoleMapper xcUserRoleMapper;

    @Transactional
    public XcUser addWxUser(Map<String,String> userInfo_map){
        String unionid = userInfo_map.get("unionid");

        // 查询用户信息，根据unionid
        XcUser xcUser = xcUserMapper.selectOne(new LambdaQueryWrapper<XcUser>().eq(XcUser::getWxUnionid, unionid));
        if(xcUserMapper != null){
            return xcUser;
        }

        String userId = UUID.randomUUID().toString();  //主键
        xcUser = new XcUser();
        xcUser.setId(userId);
        xcUser.setWxUnionid(unionid);
        //记录从微信得到的昵称
        xcUser.setNickname(userInfo_map.get("nickname").toString());
        xcUser.setUserpic(userInfo_map.get("headimgurl").toString());
        xcUser.setName(userInfo_map.get("nickname").toString());
        xcUser.setUsername(unionid);
        xcUser.setPassword(unionid);
        xcUser.setUtype("101001");//学生类型
        xcUser.setStatus("1");//用户状态
        xcUser.setCreateTime(LocalDateTime.now());
        xcUserMapper.insert(xcUser);
        XcUserRole xcUserRole = new XcUserRole();
        xcUserRole.setId(UUID.randomUUID().toString());
        xcUserRole.setUserId(userId);
        xcUserRole.setRoleId("17");//学生角色
        xcUserRoleMapper.insert(xcUserRole);
        return xcUser;
    }
}

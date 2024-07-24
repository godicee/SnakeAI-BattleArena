package com.kob.backend.service.impl.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.LoginService;
import com.kob.backend.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Collections;
import java.util.Map;

@Service//实现Service中定义的接口（需要注解）
public class LoginServiceImpl implements LoginService {//接口后面加Impl表示对一个接口的实现
    //这里按ctrl + enter实现接口
    //opt + 回车可以快速导入类

    @Autowired
    private AuthenticationManager authenticationManager;//

    @Override
    public Map<String, String> getToken(String username, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(username, password);//创建认证令牌
        //authenticationManager.authenticate(authenticationToken).var可以自动定义出来变量名和类型
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);//验证用户身份
        //如果验证登录失败会抛异常，验证成功后会返回一个Authentication对象
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();//这里获取登录的用户信息（UserDetailsImpl）强制类型转换
        User user = loginUser.getUser();//取出用户信息
        //把 UserId 封装成 JWToken
        String jwt = JwtUtil.createJWT(user.getId().toString());

        //返回结果
        Map<String, String> map = new HashMap<>();
        map.put("error_message", "success");//前面如果登录失败会自动处理，执行到这里一定登录成功了
        map.put("token", jwt);//返回token

        return map;
    }
}

package com.kob.backend.service.impl.user.account;

import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.account.InfoService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@Service
public class InfoServiceImpl implements InfoService {

    @Override
    public Map<String, String> getInfo() {
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();

        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();//从 spring 安全上下文中获取当前登录用户
        User user = loginUser.getUser();//从获取的登录用户信息中再获取信息，用 pojo 层中自定义的结构保存

        //返回结果
        Map<String, String> map = new HashMap<>();
        map.put("error_message","success");
        map.put("id", user.getId().toString());
        //注意这里不能把密码传出去了！！
        map.put("username", user.getUsername());
        map.put("photo", user.getPhoto());
        return map;
    }
}

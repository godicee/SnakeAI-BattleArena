package com.kob.backend.controller.user.account;

import com.kob.backend.service.user.account.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class LoginController {
    @Autowired
    private LoginService loginService;

    @PostMapping("/user/account/token")//定义后记得去 config 中放行
    public Map<String, String> getToken(@RequestParam Map<String, String> map){//从post的参数中取出username和password放到字典 map 中
        String username = map.get("username");
        String password = map.get("password");
        return loginService.getToken(username, password);//把数据返回接口中
    }
}

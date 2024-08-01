package com.kob.backend.controller.user.bot;

import com.kob.backend.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.Map;

@RestController
public class AddController {
    @Autowired
    private AddService addService;
    @PostMapping("/user/bot/add/")
    public Map<String, String> add(@RequestParam Map<String, String> data){
        //参数从前端传过来
        //调用 Service 的接口，存储到数据库中
        return addService.add(data);
    }
}

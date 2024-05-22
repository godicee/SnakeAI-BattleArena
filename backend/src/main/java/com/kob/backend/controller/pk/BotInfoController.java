package com.kob.backend.controller.pk;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//前后端分离
@RestController
@RequestMapping("/pk/")
public class BotInfoController {
    @RequestMapping("getBotInfo/")
    public List<Map<String, String>> getBotInfo(){
        List<Map<String, String>> list_map = new LinkedList<>();
        Map<String, String> bot1 = new HashMap<>();
        bot1.put("name", "bot1");
        bot1.put("rating", "1500");
        Map<String, String> bot2 = new HashMap<>();
        bot2.put("name", "bot2");
        bot2.put("rating", "1300");
        list_map.add(bot1);
        list_map.add(bot2);
        return list_map;
    }
}

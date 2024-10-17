package com.kob.backend.controller.ranklist;


import com.alibaba.fastjson2.JSONObject;
import com.kob.backend.mapper.RecordMapper;
import com.kob.backend.service.impl.ranklist.GetRankListServiceImpl;
import com.kob.backend.service.ranklist.GetRankListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class GetRankListServiceController {
    @Autowired
    private GetRankListService getRankListService;

    @GetMapping("/api/ranlist/getlist/")
    public JSONObject getList(@RequestParam Map<String, String> data){
        Integer page = Integer.parseInt(data.get("page"));
        return getRankListService.getList(page);
    }

}

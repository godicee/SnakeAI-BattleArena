package com.kob.backend.service.user.bot;

import java.util.Map;

public interface AddService {
    //接口中的函数默认是 public，所以这个 public 是灰色
    Map<String, String> add(Map<String, String> data);
}

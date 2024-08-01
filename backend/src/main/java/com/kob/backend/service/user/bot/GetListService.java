package com.kob.backend.service.user.bot;

import com.kob.backend.pojo.Bot;

import java.util.List;
import java.util.Map;

public interface GetListService {
    //返回自己的一堆 bot 信息，自己的 userid 在 token 中有，不需要再传参了
    List<Bot> getList();
}

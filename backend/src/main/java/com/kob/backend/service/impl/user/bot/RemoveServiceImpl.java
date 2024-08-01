package com.kob.backend.service.impl.user.bot;

import com.kob.backend.mapper.BotMapper;
import com.kob.backend.pojo.Bot;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.bot.RemoveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class RemoveServiceImpl implements RemoveService {
    @Autowired
    BotMapper botMapper;//注入 bot 数据库表信息

    @Override
    public Map<String, String> remove(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        int bot_id = Integer.parseInt(data.get("bot_id"));//Controller中传参都是字符串，需要转换
        Bot bot = botMapper.selectById(bot_id);
        Map<String, String> map = new HashMap<>();

        if(bot == null){
            map.put("error_message", "Bot不存在或已被删除");
            return map;
        }

        //判断登录用户和bot用户是否一致
        if(!bot.getUserId().equals(user.getId())){
            map.put("error_message", "不能删别人的Bot哦");
            return map;
        }

        botMapper.deleteById(bot_id);
        map.put("error_message", "success");

        return map;
    }
}


package com.kob.backend.service.impl.user.bot;

import com.kob.backend.mapper.BotMapper;
import com.kob.backend.pojo.Bot;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.bot.AddService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class AddServiceImpl implements AddService {

    @Autowired
    BotMapper botMapper;
    @Override
    public Map<String, String> add(Map<String, String> data) {
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();
        //id、userId、title、description、content、rating、createtime、modifytime
        //title、description、content需要传
        //id自增、userId从 token 取、rating 默认 1500、createtime/modifytime默认现在
        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");
        Map<String, String> map = new HashMap<>();
        //title 在业务上：创建 bot 时不能为空
        //但是数据库存储的时候可以不设置不能为空 eg：以后实现 bot 草稿功能，用户可能暂时没想好 bot 名字
        if(title.length() == 0 || title == null){
            map.put("error_message", "标题不能为空哟");
            return map;
        }

        if(title.length() > 100){
            map.put("error_message", "标题长度不能大于100");
            return map;
        }

        //给用户提供一个默认描述
        if(description == null || description.length() == 0){
            description = "这个Bot很懒，什么都没写";
        }

        if(description.length() > 300){
            map.put("error_message", "不行不行，too big,Bot描述<=300");
            return map;
        }

        if(content == null || content.length() == 0){
            map.put("error_message", "不给代码，它咋跑呢？");
            return map;
        }

        if(content.length() > 10000){
            map.put("error_message", "不接受长度>10000的屎山");
            return map;
        }

        Date now = new Date();//当前时间
        Bot bot = new Bot(null, user.getId(), title, description, content, now, now);

        botMapper.insert(bot);
        map.put("error_message", "success");
        return map;
    }
}

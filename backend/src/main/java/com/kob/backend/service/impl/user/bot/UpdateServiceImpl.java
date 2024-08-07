package com.kob.backend.service.impl.user.bot;

import com.kob.backend.mapper.BotMapper;
import com.kob.backend.pojo.Bot;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import com.kob.backend.service.user.bot.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class UpdateServiceImpl implements UpdateService {
    @Autowired
    private BotMapper botMapper;

    @Override
    public Map<String, String> update(Map<String, String> data) {
        //更新某个Bot的信息
        UsernamePasswordAuthenticationToken authenticationToken =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authenticationToken.getPrincipal();
        User user = loginUser.getUser();

        //老 bot 的 id
        int bot_id = Integer.parseInt(data.get("bot_id"));
        Bot bot = botMapper.selectById(bot_id);

        //前端新传入的 bot 信息
        String title = data.get("title");
        String description = data.get("description");
        String content = data.get("content");
        Map<String, String> map = new HashMap<>();

        //输入合法性判断
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
            //description = bot.getDescription();
            description = "这个用户很懒，什么都没留下";
        }

        if(description.length() > 300){
            map.put("error_message", "不行不行，too big,Bot描述<=300");
            return map;
        }

        if(content == null || content.length() == 0){
            content = bot.getContent();
//            map.put("error_message", "不给代码，它咋跑呢？");
//            return map;
        }

        if(content.length() > 10000){
            map.put("error_message", "不接受长度>10000的屎山");
            return map;
        }

        //Bot bot = botMapper.selectById(bot_id);
        if(bot == null){//bot存在否
            map.put("error_message", "Bot不存在或已被删除");
            return map;
        }
        if(!bot.getUserId().equals(user.getId())){//是否是作者
            map.put("error_message", "不能动别人的东西哦");
            return map;
        }

        //生成新 Bot，并更新到数据库
        Date now = new Date();
        Bot new_bot = new Bot(
                bot.getId(),
                user.getId(),
                title,
                description,
                content,
                bot.getRating(),
                bot.getCreatetime(),
                now
        );
        botMapper.updateById(new_bot);
        map.put("error_message", "success");

        return map;
    }
}

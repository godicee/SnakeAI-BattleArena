package com.kob.backend.controller.user;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import com.kob.backend.pojo.User;
import java.util.List;

@RestController//默认返回数据为json格式
public class UserController {

    @Autowired//需要用到数据库中的 Mapper，需要加的注解
    UserMapper userMapper;//Mybatis-pulse实现的 mapper 接口可以去查询https://baomidou.com/guides/data-interface/

    @GetMapping("/user/all/")//这里 RequestMapping 会映射所有请求，也可以用单个需要的请求
    public List<User> getAll() {
        return userMapper.selectList(null);//mapper 的接口查询所有用户
    }

    @GetMapping("/user/{userId}/")//按id 查表名
    public User getuser(@PathVariable int userId){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", userId);
        return userMapper.selectOne(queryWrapper);
        //return userMapper.selectById(userId);
    }
    //删除插入一般都用 post，这里用 get 是为了方便调试
    @GetMapping("/user/add/{userId}/{username}/{password}/")//插入信息
    public String addUser(
            @PathVariable int userId,
            @PathVariable String username,
            @PathVariable String password){
        if(password.length() < 6){
            return "密码太短，至少需要 6 位";
        }
        //加密密码
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(password);
        //插入数据
        User user = new User(userId, username, encodedPassword);
        userMapper.insert(user);
        return "Add User Successfully";
    }

    @GetMapping("/user/delete/{userId}/")
    public String deleteUser(@PathVariable int userId){
        userMapper.deleteById(userId);
        return "Delete User Successfully";
    }

}

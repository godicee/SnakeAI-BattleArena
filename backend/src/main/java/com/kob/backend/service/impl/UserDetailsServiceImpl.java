package com.kob.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kob.backend.mapper.UserMapper;
import com.kob.backend.pojo.User;
import com.kob.backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

//实现Spring-security访问控制的修改（Override重写）
@Service
public class UserDetailsServiceImpl implements UserDetailsService{
    @Autowired
    private UserMapper userMapper;//原则上一般用私有，不容易错

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {//Override是重写:对已有参数列表相同的同名方法重写
        //传入username的信息，返回一个 UserDetails 的对象。应该包含用户名密码等信息
        QueryWrapper<User> querywrapper = new QueryWrapper<>();//导入
        querywrapper.eq("username", username);//从数据库的"username"键中，查询和传入的 username 参数一样的值
        User user = userMapper.selectOne(querywrapper);////从数据中查询一个（querywrapper判断的数据）
        if(user == null){//如果 user 是空，则抛异常
            throw new RuntimeException("用户不存在");
        }
        return new UserDetailsImpl(user);
    }
}

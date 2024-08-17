package com.kob.backend.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.kob.backend.pojo.User;//导入 pojo 层的 User 类
//将pojo层的class中的操作，映射成sql语句
//crud增删改查：create、read、update、delete
@Mapper
public interface UserMapper extends BaseMapper<User> {
}

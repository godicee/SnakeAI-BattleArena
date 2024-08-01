package com.kob.backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//用于把 数据库的User 表翻译为 class
@Data//get、toString等实现
@NoArgsConstructor//无参构造函数
@AllArgsConstructor//所有参数构造函数
public class User {
    @TableId(type = IdType.AUTO)//主键自增
    private Integer id;
    private String username;
    private String password;
    private String photo;
}

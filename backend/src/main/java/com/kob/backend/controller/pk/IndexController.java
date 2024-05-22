package com.kob.backend.controller.pk;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
//前后端不分离
@Controller
@RequestMapping("/pk/")//父目录
public class IndexController {
    @RequestMapping("index/")//函数的父目录
    public String pkIndex() {//函数返回一个html页面：返回字符串表示 html 页面的路径
        return "pk/index.html";//templates/pk/index.html
    }
}

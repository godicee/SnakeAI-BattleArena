package com.kob.botrunningsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

//RestTemplate用于发送http请求（可以在两个服务器间通讯）
@Configuration
public class RestTemplateConfig {
    @Bean//被Autowired注入的，会去找一个bean绑定的唯一函数
    public RestTemplate getRestTemplate(){
        return new RestTemplate();
    }
}
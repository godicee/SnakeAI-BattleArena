package com.kob.backend.Consumer.Utils;

import com.kob.backend.utils.JwtUtil;
import io.jsonwebtoken.Claims;

public class JwtAuthentication {
    //static，是静态方法——可以直接调用，而不希望通过实例调用
    public static Integer getUserId(String token){
        int userId = -1;
        try {//解析 token，如果能解析出 userid，则为合法
            Claims claims = JwtUtil.parseJWT(token);
            userId = Integer.parseInt(claims.getSubject());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return userId;
    }
}

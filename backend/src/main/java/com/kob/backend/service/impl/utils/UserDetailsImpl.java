package com.kob.backend.service.impl.utils;

import com.kob.backend.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDetailsImpl implements UserDetails {//作为UserDetailsServiceImpl的工具类（实现用户的访问控制重写）

    private User user;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {//账号是否没有过期
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {//是否没有没锁定
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {//授权是否没有过期
        return true;
    }

    @Override
    public boolean isEnabled() {//用户是否被启用了，这里改为 True
        return true;
    }
}

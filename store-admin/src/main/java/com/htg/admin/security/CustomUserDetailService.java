package com.htg.admin.security;

import com.htg.admin.service.IUserService;
import com.htg.common.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userService.getUserByUserName(username);
        if (user == null) throw new UsernameNotFoundException("用户不存在");
        String name = user.getUsername();
        String password = user.getPassword();
        Integer status = user.getStatus();

        List<GrantedAuthority> list=new ArrayList<>();
        list.add(new SimpleUserAuthority(""));

        CustomUserDetails details = new CustomUserDetails(password, name, null, status);
        return details;
    }
}

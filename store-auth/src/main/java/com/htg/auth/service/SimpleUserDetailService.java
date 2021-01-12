package com.htg.auth.service;

import com.htg.api.UserClient;
import com.htg.auth.entity.SimpleUserDetails;
import com.htg.common.entity.user.User;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserDetailService implements UserDetailsService {
    @Autowired
    private UserClient userClient;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(StringUtils.isBlank(username)) throw new UsernameNotFoundException("用户不存在:"+ username);
        User userInfo = userClient.getUserInfo(username);
        if(userInfo==null) throw new UsernameNotFoundException("用户不存在:"+username);
        SimpleUserDetails userDetails=new SimpleUserDetails(userInfo.getUsername(),userInfo.getPassword());
        return userDetails;
    }
}

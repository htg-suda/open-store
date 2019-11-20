package com.htg.admin.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/*
参考
    https://blog.csdn.net/yaomingyang/article/details/98785488
*/
@Component
public class CustomAuthProvider implements AuthenticationProvider {
    @Autowired
    private CustomUserDetailService userDetailService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = (String) authentication.getCredentials();
        /* 获取用户信息 */
        UserDetails details = userDetailService.loadUserByUsername(username);
        if (passwordEncoder.matches(password, details.getPassword())) {
            /* UsernamePasswordAuthenticationToken 不能 设置为true */
            //authentication.setAuthenticated(true);
            UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(username, password, details.getAuthorities());
            token.setDetails(details);
            return token;
        }

        return authentication;
    }

    /* 该 provider 支持的 */
    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}

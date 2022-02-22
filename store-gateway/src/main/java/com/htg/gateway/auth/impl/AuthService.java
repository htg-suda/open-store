package com.htg.gateway.auth.impl;

import com.htg.gateway.auth.IAuthService;
import org.springframework.stereotype.Component;

@Component
public class AuthService implements IAuthService {

    @Override
    public boolean hasPermission(String auth, String url, String method) {

        return false;
    }
}

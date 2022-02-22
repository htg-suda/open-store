package com.htg.admin.security;

import org.springframework.security.core.GrantedAuthority;

public class SimpleUserAuthority implements GrantedAuthority {
    private String authority;

    public SimpleUserAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

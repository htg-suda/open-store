package com.htg.admin.security;

import org.springframework.security.core.GrantedAuthority;

public class MySimpleAuthority implements GrantedAuthority {
    private String authority;

    public MySimpleAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public String getAuthority() {
        return authority;
    }
}

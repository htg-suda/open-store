package com.htg.admin.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {
    private String password;
    private String username;
    private List<GrantedAuthority> list;

    private Integer status;

    public CustomUserDetails(String password, String username, List<GrantedAuthority> list, Integer status) {
        this.password = password;
        this.username = username;
        this.list = list;
        this.status = status;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return list;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return status==1;
    }

    @Override
    public boolean isAccountNonLocked() {
        return status==1;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status==1;
    }


    @Override
    public String toString() {
        return "CustomUserDetails{" +
                "password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", list=" + list +
                ", status=" + status +
                '}';
    }
}

package com.htg.common.vo;

public class LoginVO {
    private String token;

    public LoginVO(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

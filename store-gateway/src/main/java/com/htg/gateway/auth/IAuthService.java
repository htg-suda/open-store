package com.htg.gateway.auth;

public interface IAuthService {
    boolean hasPermission(String auth, String url, String method);
}

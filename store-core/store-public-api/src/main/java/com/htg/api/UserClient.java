package com.htg.api;

import com.htg.common.entity.user.User;
import com.htg.common.permission.UserPermisson;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "store-admin")
public interface UserClient {
    @GetMapping("/manage_user/info/{username}")
    User getUserInfo(@PathVariable("username") String username);

    @PostMapping("/manage_user/auth")
    UserPermisson parseToken(@RequestParam(value = "token") String token);
}

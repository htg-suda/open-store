package com.htg.api;

import com.htg.common.entity.user.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "store-auth")
public interface AuthClient {
    @GetMapping("/user/info/{username}")
    public User getUserInfo(@PathVariable("username") String username);

}

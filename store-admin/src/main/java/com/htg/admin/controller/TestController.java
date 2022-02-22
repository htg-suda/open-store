package com.htg.admin.controller;

import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import com.htg.common.permission.UserPermisson;
import com.htg.utils.JWTUtil;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Validated
@Api(tags = "001-数据相关")
@RestController
@RequestMapping(value = "/data_update", name = "数据相关")
public class TestController {
    @ApiOperationSupport(order = 10)
    @ApiOperation(value = "获取加密串")
    @ResponseBody
    @PostMapping("/encode_pwd")
    public String getPasswordEncode(String password) {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        return encode;
    }

    @ApiOperationSupport(order = 20)
    @ApiOperation(value = "解析用户认证数据")
    @ResponseBody
    @PostMapping("/auth")
    public UserPermisson parseToken(String token) {
        try {
            /* token 中获取 用户名 */
            String userName = JWTUtil.getUserName(token);
            UserPermisson userPermisson = new UserPermisson();
            userPermisson.setUsername(userName);
            List<String> authorities = new ArrayList<>();
            userPermisson.setAuthorities(authorities);
            return userPermisson;
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException e) { // token 过期
            log.error(e.getMessage());
            /* 存在一个 开头为 token_ 的 token 但是 这个token 解析不到用户名 ,或则当前线程中存在一个 已经验证过的用户 */
            return null;
        }

    }
}

package com.htg.admin;

import com.htg.admin.utils.JWTUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@Slf4j
public class AdminTest {
    @Test
    public void Test() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode("123qwe");
        System.out.println(encode);
        String jwt = JWTUtil.generateJWTByUserName("htg");
        System.out.println(jwt);

        Claims claims = null;
        try {
            claims = JWTUtil.parseJWT(jwt);
            String username = claims.get("username", String.class);
            Date exp = claims.getExpiration();
            String issuer = claims.getIssuer();
            log.info("username is {}", username);
            log.info("exp {}",exp);
            log.info("issuer is {}",issuer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void Test01() {
        Claims claims = null;
        try {
            claims = JWTUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJURy5IIiwiZXhwIjoxNTczNTI3MTkzLCJpYXQiOjE1NzM1MjcxOTAsInVzZXJuYW1lIjoiaHRnIn0.QQsO-oXEW_r4R2kQju39vKNn8iLkUU0ECfUrQFt3iP4");
            String username = claims.get("username", String.class);
            Date exp = claims.getExpiration();
            String issuer = claims.getIssuer();
            log.info("username is {}", username);
            log.info("exp {}",exp);
            log.info("issuer is {}",issuer);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

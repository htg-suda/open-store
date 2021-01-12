package com.htg.auth;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestAuth {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void test01(){
        log.info(passwordEncoder.encode("123qweasd"));
        log.info(passwordEncoder.encode("123456"));

    }

}

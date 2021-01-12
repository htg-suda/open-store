package com.htg.test.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RestController
@RequestMapping(value = "test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;
    private int count = 0;

    @PostMapping(value = "/test01")
    public String getTest(String param) {
        log.info("param is {}", param);
        return "===> " + param + " " + count++;
    }


    @PostMapping(value = "/test02")
    public String getTest02(String param) {
        log.info("param is {}", param);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://188.188.188.188/doc", param, String.class);
        return "===> " + param + " " + count++;
    }
}

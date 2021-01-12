package com.htg.gateway.controller;

import com.htg.gateway.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;


/*  所谓的 WebFlux 返回的 Mono 就是像 去饭馆吃饭,总是 先返回小票，但是返回小票有意义吗？
* 饭还是要厨子做啊！！！ 队还是要排啊。
* */
@Slf4j
@RestController
@RequestMapping("/flux")
public class FluxSTDController {
    @GetMapping(value = "/getstr")
    public Mono<Person> getString() {
        String[] strArr = {"a", "b", "c"};
        Mono<Person> mono = Mono.create(v -> {
            Person person = new Person("java", 15);
            v.success(person);
        });

        mono.doOnSubscribe(subscription -> {
            log.info(subscription.toString());
        }).doOnNext(person -> {
            log.info(person.getName());
        });
        return mono;
    }

    @GetMapping(value = "/getUserInfo")
    public Mono<String> getUserInfo(String username) {
        Mono<String> stringMono = Mono.fromSupplier(() -> {
            return getInfoByUserName(username);
        });
        return stringMono;
    }


    public String getInfoByUserName(String username) {

        return "htg";
    }


}

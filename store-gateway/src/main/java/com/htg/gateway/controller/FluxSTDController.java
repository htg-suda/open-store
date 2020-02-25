package com.htg.gateway.controller;

import com.htg.gateway.domain.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/flux")
public class FluxSTDController {
    @GetMapping(value = "/getstr")
    public Mono<Person> getString() {
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


}

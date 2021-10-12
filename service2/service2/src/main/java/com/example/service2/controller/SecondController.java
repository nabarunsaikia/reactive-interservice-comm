package com.example.service2.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@Slf4j
public class SecondController {

    @RequestMapping(value = "/api/service2", method = RequestMethod.GET)
    public Mono<String> getService2() {
        log.info("Inside service2");
        return Mono.just("Response From Service2");
    }
}

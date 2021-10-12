package com.example.service1.controller;

import io.netty.handler.logging.LogLevel;
import org.springframework.http.ResponseCookie;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.http.codec.ClientCodecConfigurer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.ExchangeStrategies;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

import java.util.Optional;
import java.util.Random;
import java.util.UUID;
import java.util.function.Consumer;

@RestController
public class FirstController {

    private final WebClient webClient;

    public FirstController(WebClient.Builder webClientBuilder) {
//        HttpClient httpClient = HttpClient
//                .create()
//                .wiretap("reactor.netty.http.client.HttpClient",
//                        LogLevel.INFO, AdvancedByteBufFormat.TEXTUAL);
//        this.webClient = webClientBuilder.baseUrl("http://localhost:8091").clientConnector(new ReactorClientHttpConnector(httpClient)).build();
//        Consumer<ClientCodecConfigurer> consumer = configurer ->
//                configurer.defaultCodecs().enableLoggingRequestDetails(true);


        // Add exchange strategies.
        ExchangeStrategies exchangeStrategies= ExchangeStrategies.builder().codecs(configured ->  configured.defaultCodecs().enableLoggingRequestDetails(true)).build();
        this.webClient = webClientBuilder.baseUrl("http://localhost:8091").exchangeStrategies(exchangeStrategies).build();
    }

    @RequestMapping(value = "/api/service1", method = RequestMethod.GET)
    public Mono<String> getService1() {
        System.out.println("Inside Get service1");
        return this.webClient.get().uri("/api/service2")
                .header("reqId", UUID.randomUUID().toString())
                .retrieve().bodyToMono(String.class);
    }

    @RequestMapping(value = "/api/service1/test", method = RequestMethod.GET)
    public Mono<String> getTest() {
        System.out.println("Inside Get service1");
        return Mono.just("Hello World");
    }

//    private ExchangeFilterFunction logRequest() {
//        return ExchangeFilterFunction.ofRequestProcessor(clinetRequest -> {
//            String reqId= clinetRequest.headers().getFirst("requestId");
//            clinetRequest.logPrefix();
//            System.out.println("Request "+ reqId + "Log prefix " + clinetRequest.logPrefix() +"method "+ clinetRequest.method() +"url "+clinetRequest.url());
//            return Mono.just(clinetRequest);
//        });
//    }
//
//    private ExchangeFilterFunction logResponse() {
//        return ExchangeFilterFunction.ofResponseProcessor(clinetResponse -> {
//            clinetResponse.logPrefix();
//            System.out.println("Response status code " + clinetResponse.statusCode());
//            return Mono.just(clinetResponse);
//        });
//    }
}

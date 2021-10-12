package com.example.service2.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
@Slf4j
public class WebStatsFilter implements WebFilter {

    @Override
    public Mono<Void> filter(
            ServerWebExchange exchange,
            WebFilterChain webFilterChain) {
        Long start= System.currentTimeMillis();
        return webFilterChain.filter(exchange).doOnSuccess(success->{
            log.info("time taken " + (System.currentTimeMillis() - start));
            log.info("reqId " + CollectionUtils.firstElement(exchange.getRequest().getHeaders().get("reqId")));
            log.info("status code "+exchange.getResponse().getStatusCode());
        }).doOnError(err->{
            log.info("time taken " + (System.currentTimeMillis() - start));
            log.info("reqId " + CollectionUtils.firstElement(exchange.getRequest().getHeaders().get("reqId")));
            log.info("status code "+exchange.getResponse().getStatusCode());
        });
    }


}
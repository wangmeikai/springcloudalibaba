package com.wmk;

import com.alibaba.csp.sentinel.adapter.gateway.sc.exception.SentinelGatewayBlockExceptionHandler;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.result.view.ViewResolver;

import java.util.Collections;
import java.util.List;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/21
 * @TIME: 10:49
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class GatewayMainApp {
    public static void main(String[] args) {
        SpringApplication.run(GatewayMainApp.class,args);
    }
}

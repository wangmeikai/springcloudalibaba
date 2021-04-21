package com.wmk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

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

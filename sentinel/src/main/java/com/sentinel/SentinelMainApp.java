package com.sentinel;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/25
 * @TIME: 20:28
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class SentinelMainApp {

    public static void main(String[] args) {
        SpringApplication.run(SentinelMainApp.class,args);
    }
}

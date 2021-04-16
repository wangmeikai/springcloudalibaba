package com.nacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/22
 * @TIME: 22:14
 **/
@SpringBootApplication
@EnableDiscoveryClient
public class NacosMainApp {
    public static void main(String[] args) {
        SpringApplication.run(NacosMainApp.class,args);
    }
}

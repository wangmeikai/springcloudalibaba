package com.wmk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 11:33
 **/
@SpringBootApplication
@MapperScan("com.wmk.mapper")
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement
public class ProductRpcMainApp {
    public static void main(String[] args) {
        SpringApplication.run(ProductRpcMainApp.class,args);
    }
}

package com.wmk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 11:32
 **/
@SpringBootApplication
@MapperScan("com.wmk.mapper")  // 扫描mapper接口
@EnableDiscoveryClient
@EnableFeignClients
@ServletComponentScan("com.wmk.filters")   //必须有次注解，否则过滤器无效
//@EnableTransactionManagement  //开启spring事务，springboot中#默认#已经开启了
//@EnableAspectJAutoProxy   //开启aop，springboot中#默认#已经开启了
public class OrderRpcMainApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderRpcMainApp.class,args);
    }
}


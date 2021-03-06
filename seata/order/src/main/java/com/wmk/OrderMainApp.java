package com.wmk;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 11:32
 **/
//剔除自动装配的数据源
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.wmk.mapper")  // 扫描mapper接口
@EnableDiscoveryClient
@EnableFeignClients
@EnableTransactionManagement  //开启spring事务
public class OrderMainApp {
    public static void main(String[] args) {
        SpringApplication.run(OrderMainApp.class,args);
    }
}

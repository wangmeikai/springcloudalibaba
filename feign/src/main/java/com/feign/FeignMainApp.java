package com.feign;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/23
 * @TIME: 15:29
 **/
@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
public class FeignMainApp {
    public static void main(String[] args) {
        SpringApplication.run(FeignMainApp.class,args);
    }

    // 全局负载均衡策略
    @Bean
    public IRule iRule(){
        return new RandomRule();
    }
}

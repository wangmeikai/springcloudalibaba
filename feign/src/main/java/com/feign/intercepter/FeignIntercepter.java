package com.feign.intercepter;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

import java.util.UUID;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/23
 * @TIME: 16:28
 **/
// feign的拦截器
public class FeignIntercepter implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        requestTemplate.header("token", UUID.randomUUID().toString());
    }
}

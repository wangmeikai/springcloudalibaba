package com.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/26
 * @TIME: 17:10
 **/
@Configuration
public class RibbonConfig {
    @Bean
    public IRule iRule() {
        return new RandomRule();
    }
}

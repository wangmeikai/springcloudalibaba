package com.wmk.config;

import com.alibaba.cloud.nacos.ribbon.NacosRule;
import com.netflix.client.config.CommonClientConfigKey;
import com.netflix.client.config.DefaultClientConfigImpl;
import com.netflix.client.config.IClientConfig;
import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.netflix.ribbon.RibbonClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/26
 * @TIME: 17:08
 **/
@Configuration
//@RibbonClients(
//        //指定微服务负载均衡
//        value = @RibbonClient(value ="productRpc-service",configuration = com.config.RibbonConfig.class)
//        //@RibbonClient(xxxxx),
//        //defaultConfiguration = xxxx.class  //指定全局默认配置
//)
//@PropertySource("classpath:ribbon.properties")
public class RibbonConfig {
    //@Bean  //全局负载均衡
    public IRule iRule(){
        return new NacosRule();
        //return new RandomRule();
    }


}

package com.wmk.config;

import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.google.common.collect.Lists;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/5/26
 * @TIME: 16:04
 **/
@Configuration
public class CanalConfig {

    @Bean
    public CanalConnector canalConnector(){
        CanalConnector canalConnector = CanalConnectors.newClusterConnector(
                Lists.newArrayList(new InetSocketAddress("127.0.0.1",11111)),
                "test","canal","canal");
        canalConnector.connect();
        //指定要监听的表，格式：{database}.{table}
        canalConnector.subscribe("*.*");
        canalConnector.rollback();
        return canalConnector;
    }


}

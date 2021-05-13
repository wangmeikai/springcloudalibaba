package com.wmk.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class ZkConfig {

    @Bean
    public ZooKeeper zooKeeper() throws IOException {
        String conn = "192.168.40.3:2181";  // 如果是集群，则用逗号隔开
        ZooKeeper zooKeeper = new ZooKeeper(conn, 40000, new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
//                System.out.println("全局WatchedEvent"+watchedEvent.getPath());
//                System.out.println("全局WatchedEvent"+watchedEvent);
            }
        });
        return zooKeeper;
    }
}

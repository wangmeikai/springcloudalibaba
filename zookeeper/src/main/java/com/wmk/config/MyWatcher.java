package com.wmk.config;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.springframework.stereotype.Component;

@Component
public class MyWatcher implements Watcher {
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("自定义watchedEvent"+watchedEvent.getPath());
        System.out.println("自定义watchedEvent"+watchedEvent);
    }
}

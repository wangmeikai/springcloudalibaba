package com.wmk.controller;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class zkController {
    private ZooKeeper zooKeeper;
    private Watcher watcher;

    @Autowired
    public void setWatcher(Watcher watcher) {
        this.watcher = watcher;
    }

    @Autowired
    public void setZooKeeper(ZooKeeper zooKeeper) {
        this.zooKeeper = zooKeeper;
    }

    @GetMapping("/get0")
    public void getData0() throws Exception {
        //使用自定义监听器 一次性的
        byte[] data = this.zooKeeper.getData("/wmk",watcher,null);
        System.out.println(new String(data));
    }

    @GetMapping("/get/{mse}")
    public void getData(@PathVariable("mse") String mse) throws Exception {
        Stat stat = new Stat();  //用来承接属性的
        //使用lambda监听器 一次性的
        byte[] data = this.zooKeeper.getData("/"+mse, watchedEvent -> {
            System.out.println("自定义watchedEvent"+watchedEvent.getPath());
            System.out.println("自定义watchedEvent"+watchedEvent);
        }, stat);
        System.out.println(new String(data));
        System.out.println(stat);
    }

    @GetMapping("/get1")
    public void getData1() throws Exception {
        //使用匿名内部类监听器 内部再次添加监听 成为永久的
        byte[] data = this.zooKeeper.getData("/wmk", new Watcher() {
            @Override
            public void process(WatchedEvent watchedEvent) {
                System.out.println("自定义watchedEvent"+watchedEvent.getPath());
                System.out.println("自定义watchedEvent"+watchedEvent);
                try {
                    // 再次添加监听
                    zooKeeper.getData(watchedEvent.getPath(),this,null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, (Stat) null);
        System.out.println(new String(data));
    }

    @GetMapping("/get2")
    public void getData2() throws Exception {
        //使用全局监听器
        byte[] data = this.zooKeeper.getData("/wmk", true, (Stat) null);
        System.out.println(new String(data));
    }

}

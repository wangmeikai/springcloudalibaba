package com.nacos.controller;

import com.alibaba.cloud.nacos.NacosConfigProperties;
import com.alibaba.cloud.nacos.NacosDiscoveryProperties;
import com.alibaba.cloud.nacos.NacosServiceManager;
import com.alibaba.nacos.api.exception.NacosException;
import com.alibaba.nacos.api.naming.NamingService;
import com.alibaba.nacos.api.naming.pojo.Instance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Properties;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/23
 * @TIME: 10:50
 **/
@Controller
@RefreshScope   //支持配置中心中的公共配置文件动态刷新（不支持修改端口）
public class nacosController {

    private DiscoveryClient discoveryClient;
    private NacosConfigProperties nacosConfigProperties;
    private NacosDiscoveryProperties nacosDiscoveryProperties;
    private NacosServiceManager nacosServiceManager;

    public nacosController(DiscoveryClient discoveryClient, NacosConfigProperties nacosConfigProperties,
                           NacosDiscoveryProperties nacosDiscoveryProperties, NacosServiceManager nacosServiceManager) {
        this.discoveryClient = discoveryClient;
        this.nacosConfigProperties = nacosConfigProperties;
        this.nacosDiscoveryProperties = nacosDiscoveryProperties;
        this.nacosServiceManager = nacosServiceManager;
    }


//    @Value("${server.port}")
//    private String port;

//    @Value("${a.b}")
//    private String commonStr;
//
//    @RequestMapping("/feignInvoke")
//    @ResponseBody
//    public String feignInvoke(){
//        return "feign 调用成功！！！！"+ port+" "+commonStr;
//    }



    @RequestMapping("/info")
    @ResponseBody
    public List<ServiceInstance> get() throws NacosException {
        List<ServiceInstance> instances = discoveryClient.getInstances("provider-application");
        return instances;
//        [
    //        {
    //            "serviceId": "provider-application",
    //            "host": "219.221.192.169",
    //            "port": 8081,
    //            "secure": false,
    //            "metadata": {
    //                  "nacos.instanceId": "219.221.192.169#8081#DEFAULT#DEFAULT_GROUP@@provider-application",
    //                  "nacos.weight": "1.0",
    //                  "nacos.cluster": "DEFAULT",
    //                  "nacos.ephemeral": "true",
    //                  "nacos.healthy": "true",
    //                  "preserved.register.source": "SPRING_CLOUD"
    //             },
    //            "uri": "http://219.221.192.169:8081",
    //            "scheme": null,
    //            "instanceId": null
    //        }
//        ]
    }

    @RequestMapping("/info1")
    @ResponseBody
    public List<Instance> get1() throws NacosException {
        Properties properties = new Properties();
        properties.setProperty("serverAddr","127.0.0.1:8848");
        NamingService namingService = nacosServiceManager.getNamingService(properties);
        List<Instance> allInstances = namingService.getAllInstances("provider-application");

//        [
    //        {
    //            "instanceId": "219.221.192.169#8081#DEFAULT#DEFAULT_GROUP@@provider-application",
//                "ip": "219.221.192.169",
//                "port": 8081,
//                "weight": 1,
//                "healthy": true,
//                "enabled": true,
//                "ephemeral": true,
//                "clusterName": "DEFAULT",
//                "serviceName": "DEFAULT_GROUP@@provider-application",
//                "metadata": {
    //                  "preserved.register.source": "SPRING_CLOUD"
    //            },
    //            "ipDeleteTimeout": 30000,
//                "instanceHeartBeatInterval": 5000,
//                "instanceHeartBeatTimeOut": 15000
    //        }
//        ]

        return allInstances;
    }
}

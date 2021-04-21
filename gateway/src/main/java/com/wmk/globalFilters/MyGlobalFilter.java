package com.wmk.globalFilters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/21
 * @TIME: 16:12
 **/
@Component    //全局过滤器（不需要配置，只需要交给IOC容器即可）
public class MyGlobalFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        exchange.getAttributes().put("start",System.currentTimeMillis());
        return chain.filter(exchange)
                .then(Mono.fromRunnable(new Runnable() {   //执行完之后的回调函数
                    @Override
                    public void run() {
                        /**
                         * 计算该地址的请求时间
                         */
                        long startTime = (long)exchange.getAttribute("start");
                        System.out.println(exchange.getRequest().getURI().getRawPath()+"执行时间为："
                                +(System.currentTimeMillis()-startTime));
                    }
                }));
    }

    @Override
    public int getOrder() {
        return 0;
    }
}

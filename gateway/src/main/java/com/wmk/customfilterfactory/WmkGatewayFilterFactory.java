package com.wmk.customfilterfactory;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.factory.AbstractNameValueGatewayFilterFactory;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/21
 * @TIME: 15:22
 **/
@Component    //单个微服务的过滤器（需要配置）
public class WmkGatewayFilterFactory extends AbstractNameValueGatewayFilterFactory {

    @Override
    public GatewayFilter apply(NameValueConfig config) {

        return new MyGatewayFilter(config);
    }


    //自定义过滤器
    public static class MyGatewayFilter implements GatewayFilter, Ordered {

        private NameValueConfig config;

        public MyGatewayFilter(NameValueConfig config) {
            this.config = config;
        }

        @Override
        public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
            String name = config.getName();
            String value = config.getValue();
            System.out.println("name=" + name + " " + "value=" + value);
            if (value.equals("false")){
                chain.filter(exchange);
            }
            exchange.getAttributes().put("startTime",System.currentTimeMillis());
            return chain.filter(exchange)
                    .then(Mono.fromRunnable(new Runnable() {   //执行完之后的回调函数
                        @Override
                        public void run() {
                            /**
                             * 计算该地址的请求时间
                             */
                            long startTime = (long)exchange.getAttribute("startTime");
                            System.out.println(exchange.getRequest().getURI().getRawPath()+"执行时间为："
                                    +(System.currentTimeMillis()-startTime));
                        }
                    }));
        }

        @Override
        public int getOrder() {
            return -100;  //越小越先执行
        }
    }


    //此方式无法控制GatewayFilter执行顺序
    //@Override
//    public GatewayFilter apply(NameValueConfig config) {
//        return new GatewayFilter() {
//            @Override
//            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
//                String name = config.getName();
//                String value = config.getValue();
//                System.out.println("name="+name+" "+"value="+value);
//                if (value.equals("false")){
//                    chain.filter(exchange);
//                }
//                exchange.getAttributes().put("startTime",System.currentTimeMillis());
//                return chain.filter(exchange)
//                        .then(Mono.fromRunnable(new Runnable() {   //执行完之后的回调函数
//                            @Override
//                            public void run() {
//                                /**
//                                 * 计算该地址的请求时间
//                                 */
//                                long startTime = (long)exchange.getAttribute("startTime");
//                                System.out.println(exchange.getRequest().getURI().getRawPath()+"执行时间为："
//                                        +(System.currentTimeMillis()-startTime));
//                            }
//                        }));
//            }
//        };
//    }
}

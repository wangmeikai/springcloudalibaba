package com.wmk.custompredicatesfactory;

import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.cloud.gateway.handler.predicate.GatewayPredicate;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.server.ServerWebExchange;

import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/21
 * @TIME: 15:03
 **/
@Component
public class WmkRoutePredicateFactory extends AbstractRoutePredicateFactory<WmkRoutePredicateFactory.Config> {

    public static final String Wmk_KEY = "name";

    public WmkRoutePredicateFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(Wmk_KEY);
    }

    @Override
    public Predicate<ServerWebExchange> apply(WmkRoutePredicateFactory.Config config) {

        return new GatewayPredicate() {
            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                if (config.getName().equals("wangmeikai")){
                    System.out.println("Wmk断言正确！！！！"+config.getName());
                    return true;
                }else {
                    System.out.println("Wmk断言错误！！！！"+config.getName());
                    return false;
                }


            }

            @Override
            public String toString() {
                return String.format("my name is %s",config.getName());
            }
        };
    }

    @Validated
    public static class Config {
        @NotNull
        private String name;


        public String getName() {
            return name;
        }

        public WmkRoutePredicateFactory.Config setName(String name) {
            this.name = name;
            return this;
        }
    }
}

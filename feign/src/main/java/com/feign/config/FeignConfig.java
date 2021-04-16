package com.feign.config;

import com.feign.intercepter.FeignIntercepter;
import feign.Logger;
import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/23
 * @TIME: 15:33
 **/
//不能加@Configuration ,否则会成为全局配置
public class FeignConfig {

    //feign日志打印配置
    @Bean
    public Logger.Level level(){
        return Logger.Level.FULL;    //生产不推荐
//        return Logger.Level.HEADERS;
//        return Logger.Level.BASIC;  //生产推荐
//        return Logger.Level.NONE;   //默认值
    }

    @Bean
    public RequestInterceptor requestInterceptor(){
        return new FeignIntercepter();
    }

}

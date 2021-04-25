package com.wmk.config;

import com.wmk.interceptors.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/25
 * @TIME: 16:21
 **/
@Configuration
public class WebMVCConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor()).addPathPatterns("/**");  //   /*:拦截一层（两层会放过）   /**:拦截所有
    }
}

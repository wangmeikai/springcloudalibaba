package com.wmk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.web.client.RestTemplate;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/25
 * @TIME: 20:58
 **/
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private RestTemplate restTemplate;

    @Bean
    public ResourceServerTokenServices resourceServerTokenServices(){
        RemoteTokenServices remoteTokenServices = new RemoteTokenServices();
        remoteTokenServices.setClientId("resource-app1");
        remoteTokenServices.setClientSecret("123456");
        //若连接注册中心则可以用服务名调http://oauth-server/oauth/check_token
        remoteTokenServices.setCheckTokenEndpointUrl("http://localhost:9999/oauth/check_token");
        remoteTokenServices.setRestTemplate(restTemplate);
        //校验令牌只会拿到用户名，若想要整个用户信息，则需要这个转换器
//        remoteTokenServices.setAccessTokenConverter();
        return remoteTokenServices;
    }

}

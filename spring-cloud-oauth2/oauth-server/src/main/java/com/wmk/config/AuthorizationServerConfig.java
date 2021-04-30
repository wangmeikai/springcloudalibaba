package com.wmk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

import java.util.HashMap;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/25
 * @TIME: 19:03
 **/
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig implements AuthorizationServerConfigurer {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 表示的资源服务器 校验token的时候需要干什么
     * (这里表示需要带入自己appid,和app secret)来进行验证
     * @param security
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        security.checkTokenAccess("isAuthenticated()");
    }

    /**
     * 方法实现说明:作为授权服务器,必须知道有哪些第三方app 来向我们授权服务器
     * 索取令牌,所以这个方法就是配置 哪些第三方app可以来获取我们的令牌的.
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
//        clients.jdbc(datasource);

        clients.inMemory()  //基于内存的
                .withClient("appId")   //允许哪些App能够访问
                .secret(passwordEncoder.encode("123456"))  //密码
                .authorizedGrantTypes("password", "authorization_code")  //支持的模式
                .scopes("read")   //颁发给令牌的权限
                .accessTokenValiditySeconds(3600)   //令牌有效期
                .resourceIds("resource-server1","resource-server2")   //颁发的令牌能访问哪些资源服务器
                .redirectUris("http://localhost:8080/getToken1")   //授权码模式的回调地址
           .and()
                .withClient("resource-app1")   //允许哪些App能够访问  （能够访问的资源也要在此处配置）
                .secret(passwordEncoder.encode("123456"))  //密码
                .accessTokenValiditySeconds(1800)   //令牌有效期
                .scopes("read")   //颁发给令牌的权限
                .authorizedGrantTypes("password")
                .resourceIds("resource-server1")
           .and()
                .withClient("resource-app2")   //允许哪些App能够访问  （能够访问的资源也要在此处配置）
                .secret(passwordEncoder.encode("123456"))  //密码
                .accessTokenValiditySeconds(1800)   //令牌有效期
                .scopes("read")   //颁发给令牌的权限
                .authorizedGrantTypes("password")
                .resourceIds("resource-server2");
    }

    /**
     * 方法实现说明:认证服务器 需要知道 是哪个用户来访问资源服务器，所以该方法是用来
     * 验证用户信息的
     * @param endpoints 认证服务器的 识别用户信息的配置
     * @return:
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //认证服务器委托一个AuthenticationManager 来验证我们的用户信息
        endpoints.authenticationManager(authenticationManager);
        //生产上 需要把token存储到redis中或者使用jwt
        //endpoints.tokenStore(new RedisTokenStore(redisConnectionFactory).setSerializationStrategy(new JsonSerializationStrategy));
//        endpoints.tokenStore(new JdbcTokenStore(dataSource));
        endpoints.accessTokenConverter(jwtAccessTokenConverter());
    }


    /**
     * 这个组件 用于jwt basecode 字符串和 安全认证对象的信息转化
     * @return
     */
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter(){
            @Override
            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
                HashMap<String, Object> additionalInformation = new HashMap<>();
                additionalInformation.put("user_id", "001");
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
                return super.enhance(accessToken, authentication);
            }
        };
        //jwt的密钥(用来保证jwt 字符串的安全性  jwt可以防止篡改  但是不能防窃听  所以jwt不要 放敏感信息)
//        converter.setKeyPair(keyPair());
        converter.setSigningKey("123456");
        return converter;
    }

    /**
     * KeyPair是 非对称加密的公钥和私钥的保存者
     * @return
     */
//    @Bean
//    public KeyPair keyPair() {
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("test.jks"), "mypass".toCharArray());
//        return keyStoreKeyFactory.getKeyPair("test", "mypass".toCharArray());
//    }
}

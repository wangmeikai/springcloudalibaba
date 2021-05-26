package com.wmk.config;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/5/25
 * @TIME: 19:59
 **/
@Configuration
public class EsConfig {

    @Bean
    public RestHighLevelClient esRestClient() {
        return new RestHighLevelClient(RestClient.builder(new HttpHost("127.0.0.1", 9200)));
    }

}

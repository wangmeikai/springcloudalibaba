package com.wmk.controller;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/5/26
 * @TIME: 14:17
 **/
@RestController
public class EsController {
    private final RestHighLevelClient esRestClient;

    public EsController(RestHighLevelClient esRestClient) {
        this.esRestClient = esRestClient;
    }

    @GetMapping("/t")
    public String test(){


        return "ok";
    }
}

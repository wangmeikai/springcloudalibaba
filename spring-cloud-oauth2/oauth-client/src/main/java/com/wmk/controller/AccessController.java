package com.wmk.controller;

import com.alibaba.fastjson.JSONObject;
import com.netflix.ribbon.RequestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.ToLongBiFunction;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/26
 * @TIME: 9:32
 **/
@RestController
public class AccessController {

    @Autowired
    private RestTemplate restTemplate;

    private String token;

    @GetMapping("/getToken")
    public String getToken(){
        //String url = "http://localhost:9999/oauth/token?username='wmk'&password='wmk'&grant_type='password'&client_id='appId'&client_secret='123456'&scope='read'";
        String url = "http://localhost:9999/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("appId","123456");

        // 一个键对应多个值
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.set("username","wmk");
        paramsMap.set("password","wmk");
        paramsMap.set("grant_type","password");
        paramsMap.set("scope","read");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(paramsMap,headers);
        try {
            ResponseEntity<JSONObject> responseEntity = restTemplate.postForEntity(url, requestEntity, JSONObject.class);
            System.out.println(responseEntity.toString());
            token =  (String) Objects.requireNonNull(responseEntity.getBody()).get("access_token");
            return token;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return "error r1";
    }

    @GetMapping("/r1")
    public String r1(){

        String url = "http://localhost:8888/r1";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization","Bearer "+token);
        HttpEntity<String> requestEntity = new HttpEntity<>(null, headers);
        try {
            //restTemplate.getForObject(url,String.class,uri路径可变参数)  此方式无法携带请求头
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            return responseEntity.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return "error r1";
    }
}

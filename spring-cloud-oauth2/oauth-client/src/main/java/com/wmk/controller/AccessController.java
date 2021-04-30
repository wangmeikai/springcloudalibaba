package com.wmk.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

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


    /**
     * 用户直接在客户端输入用户名密码，直接获取token，容易密码泄露
     * 模拟前端服务器password方式获取token
     * @return
     */
    @GetMapping("/getToken")
    public String getToken_Password() {
        //String url = "http://localhost:9999/oauth/token?username='wmk'&password='wmk'&grant_type='password'&client_id='appId'&client_secret='123456'&scope='read'";
        String url = "http://localhost:9999/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("appId", "123456");

        // 一个键对应多个值
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.set("username", "wmk");
        paramsMap.set("password", "wmk");
        paramsMap.set("grant_type", "password");
        paramsMap.set("scope", "read");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(paramsMap, headers);
        try {
            ResponseEntity<JSONObject> responseEntityGetToken = restTemplate.postForEntity(url, requestEntity, JSONObject.class);
            System.out.println(responseEntityGetToken.toString());
            token = (String) Objects.requireNonNull(responseEntityGetToken.getBody()).get("access_token");
            ResponseEntity<JSONObject> responseEntityCheckToken = this.checkToken(token);
            System.out.println(responseEntityCheckToken.toString());
            return token+"\n"+responseEntityCheckToken.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Token access fail";
        }
    }

    /**
     * 客户端引导用户访问授权服务器，在授权服务器输入用户名密码返回code，回调地址写此地址
     * 此接口接收code，并向授权服务器发送获取token的请求，携带客户端id，密码和授权码
     * 模拟前端服务器authorization_code方式获取token
     * @return
     */
    @GetMapping("/getToken1")
    public String getToken_Code(String code, String state) {

        System.out.println("================="+code);
        System.out.println("================="+state);

        String url = "http://localhost:9999/oauth/token";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("appId", "123456");

        // 一个键对应多个值
        MultiValueMap<String, String> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.set("grant_type", "authorization_code");
        paramsMap.set("scope", "read");
        paramsMap.set("code",code);
        //paramsMap.set("redirect_uri","http://localhost:8080/getToken1");
        paramsMap.set("redirect_uri","http://localhost:8080/getToken1");
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(paramsMap, headers);
        try {
            ResponseEntity<JSONObject> responseEntityGetToken = restTemplate.postForEntity(url, requestEntity, JSONObject.class);
            System.out.println(responseEntityGetToken.toString());
            token = (String) Objects.requireNonNull(responseEntityGetToken.getBody()).get("access_token");
            ResponseEntity<JSONObject> responseEntityCheckToken = this.checkToken(token);
            System.out.println(responseEntityCheckToken.toString());
            return token+"\n"+responseEntityCheckToken.getBody();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Token access fail";
        }
    }


    /**
     * 校验token的有效性
     * @param token
     * @return
     */
    public ResponseEntity<JSONObject> checkToken(String token) {
        String url = "http://localhost:9999/oauth/check_token";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("appId", "123456");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("token", token);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(params, headers);

        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, JSONObject.class);

        return responseEntity;
    }

    @GetMapping("/r1")
    public String r1() {

        String url = "http://localhost:8888/r1";

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);
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

    public static void main(String[] args) {
        AccessController accessController = new AccessController();
        ResponseEntity<JSONObject> responseEntity = accessController.checkToken("0c95cc1c-442f-443a-9080-b95d76534a88");
        System.out.println(responseEntity);
    }
}

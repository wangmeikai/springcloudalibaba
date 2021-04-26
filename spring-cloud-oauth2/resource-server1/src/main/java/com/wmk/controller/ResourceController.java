package com.wmk.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/25
 * @TIME: 21:23
 **/
@RestController
public class ResourceController {

    @GetMapping("/r1")
    public String r1(){
        return "资源服务器r1";
    }

    @GetMapping("/w1")
    public String w1(){
        return "资源服务器w1";
    }
}

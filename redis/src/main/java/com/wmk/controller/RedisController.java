package com.wmk.controller;

import com.wmk.common.Result;
import com.wmk.pojo.Student;
import com.wmk.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RedisController {

    @Autowired
    Student student;

    @Autowired
    RedisService redisService;

    @GetMapping("/m")
    @ResponseBody
    public Result<Object> m(){
        redisService.m();
        return new Result<>(200,"success",student,null);
    }

    @GetMapping("/m1")
    @ResponseBody
    public Result<Object> m1(){
        redisService.m1();
        return new Result<>(200,"success",student,null);
    }

    @GetMapping("/m2")
    @ResponseBody
    public Result<Object> m2(){
        redisService.m2();
        return new Result<>(200,"success",student,null);
    }

    @GetMapping("/m3")
    @ResponseBody
    public Result<Object> m3(){
        boolean b = redisService.m3();
        while (!b){
            b = redisService.m3();
        }
        return new Result<>(200,"success",student,null);
    }

    @GetMapping("/m4")
    @ResponseBody
    public Result<Object> m4(){
        redisService.m4();
        return new Result<>(200,"success",student,null);
    }
}

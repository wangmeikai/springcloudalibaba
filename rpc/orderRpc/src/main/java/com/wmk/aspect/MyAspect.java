package com.wmk.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/25
 * @TIME: 17:11
 **/
@Aspect
@Component
public class MyAspect {

    @Before("execution(public * com.wmk.controller.OrderController.m(..))))")
    public void before(JoinPoint jp){
        Object[] o = jp.getArgs();
        System.out.println("MyAspect====before()"+ Arrays.toString(o));
    }
}

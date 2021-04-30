package com.wmk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/25
 * @TIME: 21:14
 **/
@SpringBootApplication
public class RibbonMainApp {

    public static void main(String[] args) {
        SpringApplication.run(RibbonMainApp.class,args);

        //AnnotationConfigApplicationContext acac = new AnnotationConfigApplicationContext(RibbonMainApp.class);

    }
}

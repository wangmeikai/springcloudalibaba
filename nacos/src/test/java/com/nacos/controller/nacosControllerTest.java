package com.nacos.controller;

import com.alibaba.nacos.api.exception.NacosException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/23
 * @TIME: 11:08
 **/
@SpringBootTest
class nacosControllerTest {

    @Autowired
    nacosController nacosController;

    @Test
    void get() throws NacosException {
        nacosController.get();
    }
}
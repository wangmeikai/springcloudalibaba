package com.wmk.controller;

import com.wmk.service.OrderService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 13:57
 **/
@Controller
@RequestMapping("/order")
public class OrderController {

    private OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/addOrderCount")
    @ResponseBody
    public String addOrderCount(String name,HttpServletRequest request){
        System.out.println("网关处添加的请求头："+request.getHeader("X-Request-red"));
        System.out.println("网关处添加的请求参数："+name);
        int i = orderService.addOrderCount();
        System.out.println(i);
        return "Order ok";
    }

}

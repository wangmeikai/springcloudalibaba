package com.wmk.service.impl;

import com.wmk.common.Result;
import com.wmk.feignApi.ProductFeignService;
import com.wmk.mapper.OrderMapper;
import com.wmk.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.TimeUnit;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 14:13
 **/
@Service
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;
    private ProductFeignService productFeignService;

    public OrderServiceImpl(OrderMapper orderMapper, ProductFeignService productFeignService) {
        this.orderMapper = orderMapper;
        this.productFeignService = productFeignService;
    }

    @Override
    @Transactional
    @GlobalTransactional(name = "creat-order" ,rollbackFor = Exception.class)
    public int addOrderCount() {
        int i = orderMapper.addOrderCount();
        System.out.println("======================"+i);
        String s = productFeignService.descProductCount();
        int a = 1/0;
        return i;
    }
}

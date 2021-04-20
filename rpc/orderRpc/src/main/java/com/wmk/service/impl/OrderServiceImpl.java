package com.wmk.service.impl;

import com.wmk.feignApi.ProductFeignService;
import com.wmk.mapper.OrderMapper;
import com.wmk.service.OrderService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public int addOrderCount() {
        int i = orderMapper.addOrderCount();
        System.out.println("======================"+i);
        String s = productFeignService.descProductCount();
        System.out.println("========================"+s);
        return i;
    }
}

package com.wmk.feignApi;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 15:03
 **/
//feign不支持下划线_（会报错），支持横线-
@FeignClient("product-service")
public interface ProductFeignService {

    @GetMapping("/product/descProductCount")
    @ResponseBody
    String descProductCount();
}

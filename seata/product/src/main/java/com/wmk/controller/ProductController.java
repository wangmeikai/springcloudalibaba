package com.wmk.controller;

import com.wmk.service.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 13:58
 **/
@Controller
@RequestMapping("/product")
public class ProductController {
    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/descProductCount")
    @ResponseBody
    public String descProductCount(){
        int i = productService.descProductCount();
        System.out.println(i);
        return "product ok";
    }
}

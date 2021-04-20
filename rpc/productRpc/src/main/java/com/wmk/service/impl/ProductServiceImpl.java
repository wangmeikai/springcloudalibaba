package com.wmk.service.impl;

import com.wmk.mapper.ProductMapper;
import com.wmk.service.ProductService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 14:39
 **/
@Service
public class ProductServiceImpl implements ProductService {
    private ProductMapper productMapper;

    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;
    }

    @Override
    @Transactional
    public int descProductCount() {
        int i = productMapper.descProductCount();
        return i;
    }
}

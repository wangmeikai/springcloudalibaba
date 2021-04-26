package com.wmk.service.impl;

import com.wmk.dao.JDBCUtil;
import com.wmk.dao.RowJDBC;
import com.wmk.feignApi.ProductFeignService;
import com.wmk.mapper.OrderMapper;
import com.wmk.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/19
 * @TIME: 14:13
 **/
@Service
public class OrderServiceImpl implements OrderService {

    private OrderMapper orderMapper;
    private ProductFeignService productFeignService;

    @Autowired
    private RowJDBC rowJDBC;

    public OrderServiceImpl(OrderMapper orderMapper, ProductFeignService productFeignService) {
        this.orderMapper = orderMapper;
        this.productFeignService = productFeignService;
    }

    @Override
    @Transactional
    public int addOrderCount() {
        int i = orderMapper.addOrderCount();
        System.out.println("======================"+i);
        //int a = 1/0;
        String s = productFeignService.descProductCount();
        System.out.println("========================"+s);

        return i;
    }

//    @Override   //原生JDBC
//    public int addOrderCount() {
//        int i = 0;
//        Connection conn = JDBCUtil.creatConn();
//        try {
//            conn.setAutoCommit(false);
//            i = rowJDBC.addOrderCount(conn);
//            System.out.println("======================"+i);
//            conn.commit();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }finally {
//            if (conn!=null){
//                try {
//                    conn.close();
//                } catch (SQLException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        return i;
//    }
}

package com.sentinel.utils;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.RequestOriginParser;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * 用于处理针对来源
 * @USER: WangMeiKai
 * @DATE: 2021/4/17
 * @TIME: 20:49
 **/

//@Component   //开启后生效
public class MyOrignPaser implements RequestOriginParser {
    @Override
    public String parseOrigin(HttpServletRequest request) {
        String origin = request.getHeader("origin");

        return origin;
    }
}

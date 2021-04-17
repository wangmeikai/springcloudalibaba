package com.sentinel.utils;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.UrlCleaner;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/17
 * @TIME: 20:40
 **/

//@Component   //开启后生效
public class MyURLClean implements UrlCleaner {
    @Override
    public String clean(String originUrl) {
        //处理url资源名称不同的问题   /m/1 与/m/2 为不同的资源
        //将1或者2替换为{number}     /m/{number}
        List<String> strs = Arrays.asList(originUrl.split("/"));
        strs.set(strs.size()-1,"{number}");
        StringBuffer sb = new StringBuffer("/");
        for (String str : strs) {
            sb.append(str);
        }
        return sb.toString();
    }
}

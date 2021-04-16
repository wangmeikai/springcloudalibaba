package com.sentinel.utils;

import com.alibaba.csp.sentinel.slots.block.BlockException;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/16
 * @TIME: 20:26
 **/
public class MyBlockHandler {
    //方法返回值和入参要一致（入参仅仅可以多一个BlockException）,最好加上，
    //不然blockHandler和fallback同时存在时，blockHandler会无法被调用，只会调用fallback
    // **** 此处必须为static
    public static String m2BlockHandler(BlockException e) {
        //处理sentinel配置异常
        return "HelloWorldController_m2方法被流控了 "+e;
    }
}

package com.sentinel.utils;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/16
 * @TIME: 20:26
 **/
public class MyFallback {
    // **** 此处必须为static
    public static String m2fallback() {
        //处理java逻辑异常
        return "HelloWorldController_m2方法逻辑异常";
    }
}

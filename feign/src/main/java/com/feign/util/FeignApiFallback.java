package com.feign.util;

import com.feign.feignservice.FeignApi;
import org.springframework.stereotype.Component;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/17
 * @TIME: 17:59
 **/

@Component
public class FeignApiFallback implements FeignApi {
    @Override
    public String feignInvoke() {
        //统一处理异常
        return null;
    }
}

package com.feign.util;

import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.feign.feignservice.FeignApi;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/4/17
 * @TIME: 17:59
 **/

@Component
public class FeignApiFallbackFactory implements FallbackFactory<FeignApi> {

    @Override
    public FeignApi create(Throwable cause) {
        //拿到Throwable cause异常类型，可根据不同异常进行有区别处理
        return new FeignApi() {
            @Override
            public String feignInvoke() {
                if (cause instanceof FlowException) {
                    //流控异常

                }else if (cause instanceof DegradeException){
                    //降级异常

                }
                return null;
            }
        };
    }
}

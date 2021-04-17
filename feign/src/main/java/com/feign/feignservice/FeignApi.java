package com.feign.feignservice;

import com.feign.config.FeignConfig;
import com.feign.util.FeignApiFallback;
import com.feign.util.FeignApiFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/23
 * @TIME: 15:19
 **/

/**
 * fallbackFactory 与 fallback方法不能同时使用
 */
//@FeignClient(name = "provider-application", configuration = FeignConfig.class,
//            fallback = FeignApiFallback.class)
@FeignClient(name = "provider-application", configuration = FeignConfig.class,
            fallbackFactory = FeignApiFallbackFactory.class)
public interface FeignApi {

    @RequestMapping("/feignInvoke")
    @ResponseBody
    String feignInvoke();
}

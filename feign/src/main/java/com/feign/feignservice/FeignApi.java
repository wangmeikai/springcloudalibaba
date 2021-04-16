package com.feign.feignservice;

import com.feign.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/23
 * @TIME: 15:19
 **/
@FeignClient(name = "provider-application", configuration = FeignConfig.class)
public interface FeignApi {

    @RequestMapping("/feignInvoke")
    @ResponseBody
    String feignInvoke();
}

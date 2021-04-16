package com.feign.controller;

import com.feign.feignservice.FeignApi;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/23
 * @TIME: 15:40
 **/
@Controller
public class FeignController {

    private FeignApi feignApi;

    public FeignController(FeignApi feignApi) {
        this.feignApi = feignApi;
    }

    @RequestMapping("/feign/feignInvoke")
    @ResponseBody
    public String feignInvoke(){
        return feignApi.feignInvoke();
    }
}

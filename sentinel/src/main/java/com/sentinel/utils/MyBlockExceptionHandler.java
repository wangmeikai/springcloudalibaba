package com.sentinel.utils;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 可针对获取到的异常类型进行流控、降级等区别性对待
 * @USER: WangMeiKai
 * @DATE: 2021/4/17
 * @TIME: 20:57
 **/
@Component
public class MyBlockExceptionHandler implements BlockExceptionHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, BlockException e) throws Exception {
        if (e instanceof FlowException) {
            //流控异常

        }else if (e instanceof DegradeException){
            //降级异常

        }else if (e instanceof ParamFlowException){
            //热点参数

        }else if (e instanceof AuthorityException){
            //授权异常

        }else if (e instanceof SystemBlockException){
            //系统

        }else {
            //降级

        }
    }
}

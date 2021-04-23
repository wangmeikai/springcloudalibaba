package com.sentinel.controller;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowItem;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowRuleManager;
import com.sentinel.utils.MyBlockHandler;
import com.sentinel.utils.MyFallback;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @USER: WangMeiKai
 * @DATE: 2021/3/25
 * @TIME: 19:26
 **/
@Controller
public class HelloWorldController {

    //@PostConstruct
//    public void init(){
//        List<FlowRule> flowRules = new ArrayList<>();
//        /**
//         * 定义 HelloWorldController_m1 受保护的资源的规则
//         */
//        //创建流控规则对象
//        FlowRule flowRule = new FlowRule();
//        //设置流控规则 QPS
//        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        //设置受保护的资源
//        flowRule.setResource("HelloWorldController_m1");
//        //设置受保护的资源的阈值
//        flowRule.setCount(1);
//
//        /**
//         * 定义 HelloWorldController_m2 受保护的资源的规则
//         */
//        //创建流控规则对象
//        FlowRule flowRule2 = new FlowRule();
//        //设置流控规则 QPS
//        flowRule2.setGrade(RuleConstant.FLOW_GRADE_QPS);
//        //设置受保护的资源
//        flowRule2.setResource("HelloWorldController_m2");
//        //设置受保护的资源的阈值
//        flowRule2.setCount(1);
//
//        flowRules.add(flowRule);
//        flowRules.add(flowRule2);
//
//        //加载配置好的规则
//        FlowRuleManager.loadRules(flowRules);
//
//        //降级配置
////        DegradeRule degradeRule = new DegradeRule();
////        degradeRule.setGrade(RuleConstant.DEGRADE_GRADE_EXCEPTION_COUNT);
////        degradeRule.setCount(1);
////        degradeRule.setTimeWindow(10);
////        degradeRule.setResource("HelloWorldController_m1");
////
////        DegradeRuleManager.loadRules(Arrays.asList(degradeRule));
//
////        //热点配置
////        ParamFlowRule rule = new ParamFlowRule("HelloWorldController_m1")
////                .setParamIdx(0)
////                .setCount(5);
////        // 针对 int 类型的参数 PARAM_B，单独设置限流 QPS 阈值为 10，而不是全局的阈值 5.
////        ParamFlowItem item = new ParamFlowItem().setObject("a")
////                .setClassType(int.class.getName())
////                .setCount(10);
////        rule.setParamFlowItemList(Collections.singletonList(item));
////
////        ParamFlowRuleManager.loadRules(Collections.singletonList(rule));
//
//    }

    @RequestMapping("/m1")
    @ResponseBody
    //@SentinelResource(value = "HelloWorldController_m1")
    public String m1(Integer a) {

        Entry entity =null;
        //关联受保护的资源
        try {
            entity = SphU.entry("HelloWorldController_m1");
            //TODO 开始执行 自己的业务方法
            System.out.println(a);
            //TODO 结束执行自己的业务方法
        } catch (BlockException e) {

            return "HelloWorldController_m1方法被流控了";
        } catch (Throwable throwable){
            System.out.println(throwable.getMessage());
        } finally {
            if(entity!=null) {
                entity.exit();
            }
        }
        return "OK1";
    }

    @RequestMapping("/m2")
    @ResponseBody
    //@SentinelResource(value = "HelloWorldController_m2",blockHandler ="m2BlockHandler", fallback = "m2fallback")
    @SentinelResource(value = "HelloWorldController_m2",
            blockHandlerClass = MyBlockHandler.class, blockHandler ="m2BlockHandler",
            fallbackClass = MyFallback.class, fallback = "m2fallback")
    public String m2() {
        //TODO 开始执行 自己的业务方法
        System.out.println("public String m2()===============");
        //int i= 1/0;
        return "OK2";
    }

//    //方法返回值和入参要一致（入参仅仅可以多一个BlockException）,最好加上，
//    // 不然blockHandler和fallback同时存在时，blockHandler会无法被调用，只会调用fallback
//    public String m2BlockHandler(BlockException e) {
//        //处理sentinel配置异常
//        return "HelloWorldController_m2方法被流控了 "+e.getMessage();
//    }
//    public String m2fallback() {
//        //处理java逻辑异常
//        return "HelloWorldController_m2方法逻辑异常";
//    }

}

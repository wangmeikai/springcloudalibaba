package com.wmk.sentinelpersistence;

import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.datasource.Converter;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;

/**
* @desc: 类的描述:规则列表解析工具类
*/
public class RuleListParserUtils {

    /**
     * 流控列表解析器
     */
    public static final Converter<String, Set<GatewayFlowRule>> flowRuleListParser = new Converter<String, Set<GatewayFlowRule>>() {
        @Nullable
        @Override
        public Set<GatewayFlowRule> convert(String source) {
            return JSON.parseObject(source, new TypeReference<Set<GatewayFlowRule>>() {});
        }
    };

    public static final Converter<String,List<DegradeRule>> degradeRuleListParse = new Converter<String, List<DegradeRule>>() {
        @Nullable
        @Override
        public List<DegradeRule> convert(String source) {
            return JSON.parseObject(source,new TypeReference<List<DegradeRule>>(){});
        }
    };

    public static final Converter<String,List<SystemRule>> sysRuleListParse = new Converter<String, List<SystemRule>>() {
        @Nullable
        @Override
        public List<SystemRule> convert(String source) {
            return JSON.parseObject(source,new TypeReference<List<SystemRule>>(){});
        }
    };


    public static final Converter<Set<GatewayFlowRule>,String>  flowFuleEnCoding= new Converter<Set<GatewayFlowRule>,String>() {

        @Nullable
        @Override
        public String convert(Set<GatewayFlowRule> source) {
            return JSON.toJSONString(source);
        }
    };

    public static final Converter<List<DegradeRule>,String>  degradeRuleEnCoding= new Converter<List<DegradeRule>,String>() {

        @Nullable
        @Override
        public String convert(List<DegradeRule> source) {
            return JSON.toJSONString(source);
        }
    };

    public static final Converter<List<SystemRule>,String>  sysRuleEnCoding= new Converter<List<SystemRule>,String>() {

        @Nullable
        @Override
        public String convert(List<SystemRule> source) {
            return JSON.toJSONString(source);
        }
    };


}

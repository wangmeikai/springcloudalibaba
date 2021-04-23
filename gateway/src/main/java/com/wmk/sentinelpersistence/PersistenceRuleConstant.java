package com.wmk.sentinelpersistence;

import java.util.HashMap;
import java.util.Map;

/**
* @desc: 类的描述:Sentinel 规则持久化 常量配置类
*/
public class PersistenceRuleConstant {

    /**
     * 存储文件路径
     */
    public static final String storePath = System.getProperty("user.home")+"\\sentinel-gateway\\rules\\";

    /**
     * 各种存储sentinel规则映射map
     */
    public static final Map<String,String> rulesMap = new HashMap<>();

    //流控规则文件
    public static final String FLOW_RULE_PATH = "flowRulePath";

    //降级规则文件
    public static final String DEGRAGE_RULE_PATH = "degradeRulePath";

    //系统规则文件
    public static final String SYSTEM_RULE_PATH = "systemRulePath";

    static {
        rulesMap.put(FLOW_RULE_PATH,storePath+"flowRule.json");
        rulesMap.put(DEGRAGE_RULE_PATH,storePath+"degradeRule.json");
        rulesMap.put(SYSTEM_RULE_PATH,storePath+"systemRule.json");
    }
}

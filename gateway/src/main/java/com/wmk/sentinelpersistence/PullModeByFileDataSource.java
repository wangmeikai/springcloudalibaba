package com.wmk.sentinelpersistence;

import com.alibaba.csp.sentinel.adapter.gateway.common.command.UpdateGatewayRuleCommandHandler;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.datasource.FileRefreshableDataSource;
import com.alibaba.csp.sentinel.datasource.FileWritableDataSource;
import com.alibaba.csp.sentinel.datasource.ReadableDataSource;
import com.alibaba.csp.sentinel.datasource.WritableDataSource;
import com.alibaba.csp.sentinel.init.InitFunc;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRule;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeRuleManager;
import com.alibaba.csp.sentinel.slots.system.SystemRule;
import com.alibaba.csp.sentinel.slots.system.SystemRuleManager;
import com.alibaba.csp.sentinel.transport.util.WritableDataSourceRegistry;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;

/**
 * poll持久化方式
 */
public class PullModeByFileDataSource implements InitFunc {

    @Override
    public void init() {
        try {
            //创建文件存储目录
            RuleFileUtils.mkdirIfNotExits(PersistenceRuleConstant.storePath);
            //创建规则文件
            RuleFileUtils.createFileIfNotExits(PersistenceRuleConstant.rulesMap);
            // 处理流控规则逻辑
            dealGatewayFlowRules();
            // 处理降级规则
            dealGatewayDegradeRules();
            // 处理系统规则
            dealSystemRules();

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 方法实现说明:处理流控规则逻辑
     */
    private void dealGatewayFlowRules() throws FileNotFoundException {
        String ruleFilePath = PersistenceRuleConstant.rulesMap.get(PersistenceRuleConstant.FLOW_RULE_PATH);

        ReadableDataSource<String, Set<GatewayFlowRule>> flowRuleRDS = new FileRefreshableDataSource<Set<GatewayFlowRule>>(
                new File(ruleFilePath),RuleListParserUtils.flowRuleListParser,2000,1024 * 1024, Charset.forName("utf-8")
        );

        // 将可读数据源注册至FlowRuleManager 这样当规则文件发生变化时，就会更新规则到内存
        GatewayRuleManager.register2Property(flowRuleRDS.getProperty());


        WritableDataSource<Set<GatewayFlowRule>> flowRuleWDS = new FileWritableDataSource<Set<GatewayFlowRule>>(
                ruleFilePath, RuleListParserUtils.flowFuleEnCoding
        );

        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        UpdateGatewayRuleCommandHandler.setWritableDataSource(flowRuleWDS);

    }

    /**
     * 方法实现说明:处理降级规则
     */
    private void dealGatewayDegradeRules() throws FileNotFoundException {
        //讲解规则文件路径
        String degradeRuleFilePath = PersistenceRuleConstant.rulesMap.get(PersistenceRuleConstant.DEGRAGE_RULE_PATH);

        //创建流控规则的可读数据源
        ReadableDataSource<String, List<DegradeRule>> degradeRuleRDS = new FileRefreshableDataSource<List<DegradeRule>>(
                degradeRuleFilePath,RuleListParserUtils.degradeRuleListParse
        );

        // 将可读数据源注册至FlowRuleManager 这样当规则文件发生变化时，就会更新规则到内存
        DegradeRuleManager.register2Property(degradeRuleRDS.getProperty());


        WritableDataSource<List<DegradeRule>> degradeRuleWDS = new FileWritableDataSource<>(
                degradeRuleFilePath, RuleListParserUtils.degradeRuleEnCoding
        );

        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerDegradeDataSource(degradeRuleWDS);
    }

    /**
     * 方法实现说明:处理系统规则
     */
    private void dealSystemRules() throws FileNotFoundException {
        //讲解规则文件路径
        String systemRuleFilePath = PersistenceRuleConstant.rulesMap.get(PersistenceRuleConstant.SYSTEM_RULE_PATH);

        //创建流控规则的可读数据源
        ReadableDataSource<String, List<SystemRule>> systemRuleRDS = new FileRefreshableDataSource<>(
                systemRuleFilePath,RuleListParserUtils.sysRuleListParse
        );

        // 将可读数据源注册至FlowRuleManager 这样当规则文件发生变化时，就会更新规则到内存
        SystemRuleManager.register2Property(systemRuleRDS.getProperty());


        WritableDataSource<List<SystemRule>> systemRuleWDS = new FileWritableDataSource<>(
                systemRuleFilePath, RuleListParserUtils.sysRuleEnCoding
        );

        // 将可写数据源注册至 transport 模块的 WritableDataSourceRegistry 中.
        // 这样收到控制台推送的规则时，Sentinel 会先更新到内存，然后将规则写入到文件中.
        WritableDataSourceRegistry.registerSystemDataSource(systemRuleWDS);
    }

}

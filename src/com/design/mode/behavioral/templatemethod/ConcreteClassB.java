package com.design.mode.behavioral.templatemethod;

/**
 * 具体子类B（Concrete Class B）
 *
 * 该类提供一组偏“配置加载 + 数据查询 + 报告生成”的步骤实现，
 * 与 ConcreteClassA 形成对照，体现模板方法模式下“流程一致、实现可变”。
 */
public class ConcreteClassB extends AbstractClass {
    
    /**
     * 初始化阶段
     *
     * 演示从外部配置与基础设施（如数据库）准备运行环境。
     */
    @Override
    protected void initialize() {
        System.out.println("ConcreteClassB: 初始化操作");
        System.out.println("ConcreteClassB: 加载配置文件");
        System.out.println("ConcreteClassB: 建立数据库连接");
    }
    
    /**
     * 核心处理阶段
     *
     * 演示查询、处理、汇总并输出报告的业务链路。
     */
    @Override
    protected void coreOperation() {
        System.out.println("ConcreteClassB: 执行核心操作");
        System.out.println("ConcreteClassB: 查询数据库");
        System.out.println("ConcreteClassB: 处理数据");
        System.out.println("ConcreteClassB: 生成报告");
    }
    
    /**
     * 结果处理阶段
     *
     * 演示结果通知与归档等后置动作。
     */
    @Override
    protected void processResult() {
        System.out.println("ConcreteClassB: 处理结果");
        System.out.println("ConcreteClassB: 发送邮件通知");
        System.out.println("ConcreteClassB: 归档报告");
    }
    
    /**
     * 本类未覆盖 cleanup()，直接复用父类默认清理逻辑。
     */
}

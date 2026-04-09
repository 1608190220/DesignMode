package com.design.mode.behavioral.templatemethod;

/**
 * 具体子类A（Concrete Class A）
 *
 * 该类提供一组偏“本地计算/数据处理”风格的步骤实现，
 * 用于演示在统一模板流程下定制具体业务行为。
 */
public class ConcreteClassA extends AbstractClass {
    
    /**
     * 初始化阶段
     *
     * 负责准备算法执行前置条件，例如加载输入数据与申请运行资源。
     */
    @Override
    protected void initialize() {
        System.out.println("ConcreteClassA: 初始化操作");
        System.out.println("ConcreteClassA: 准备数据和资源");
    }
    
    /**
     * 核心处理阶段
     *
     * 执行核心业务逻辑并产出中间结果。
     */
    @Override
    protected void coreOperation() {
        System.out.println("ConcreteClassA: 执行核心操作");
        System.out.println("ConcreteClassA: 处理业务逻辑");
        System.out.println("ConcreteClassA: 计算结果");
    }
    
    /**
     * 结果处理阶段
     *
     * 对核心阶段结果进行整理、输出或存储。
     */
    @Override
    protected void processResult() {
        System.out.println("ConcreteClassA: 处理结果");
        System.out.println("ConcreteClassA: 格式化输出");
        System.out.println("ConcreteClassA: 保存结果");
    }
    
    /**
     * 自定义清理阶段
     *
     * 覆盖父类默认实现，演示子类可按需定制资源回收策略。
     */
    @Override
    protected void cleanup() {
        System.out.println("ConcreteClassA: 执行自定义的清理操作");
        System.out.println("ConcreteClassA: 释放资源");
    }
}

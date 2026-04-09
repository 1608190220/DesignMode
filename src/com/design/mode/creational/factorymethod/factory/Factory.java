package com.design.mode.creational.factorymethod.factory;

import com.design.mode.creational.factorymethod.product.Product;

/**
 * 抽象工厂（创建者）
 *
 * 该类定义了工厂方法模式中的“创建者”角色：
 * - 通过工厂方法 createProduct() 将“创建哪个具体产品”的决定延迟到子类
 * - 通过 doSomething() 展示“创建 + 使用”的典型业务流程
 *
 * 设计意图：
 * 1. 把对象创建职责从客户端中剥离，避免直接 new 具体产品
 * 2. 把可变点（具体产品类型）封装到子类，稳定点（业务流程）放在父类
 * 3. 通过模板方法固定执行骨架，减少重复流程代码
 */
public abstract class Factory {

    /**
     * 工厂方法：创建产品对象
     *
     * 子类需要实现该方法并返回具体产品实例。
     * 客户端和父类流程只依赖 Product 抽象，不感知具体产品细节。
     *
     * @return 产品对象
     */
    public abstract Product createProduct();

    /**
     * 模板方法：定义标准业务流程
     *
     * 典型步骤：
     * 1. 进行前置处理（日志、参数准备等）
     * 2. 调用工厂方法创建产品
     * 3. 使用产品完成核心业务
     * 4. 进行收尾处理（日志、资源释放等）
     *
     * 说明：
     * - 当前示例使用控制台输出模拟步骤
     * - 在真实场景中可扩展异常处理、埋点、事务等横切逻辑
     */
    public void doSomething() {
        System.out.println("工厂开始工作...");

        // 调用工厂方法创建产品（具体类型由子类决定）
        Product product = createProduct();

        // 使用产品执行业务行为（通过抽象接口多态调用）
        product.use();

        System.out.println("工厂工作完成...");
    }
}

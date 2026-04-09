package com.design.mode.creational.factorymethod.product;

/**
 * 具体产品 B
 *
 * 该类是 Product 的另一种实现，用于表达与产品 A 不同的业务能力。
 * 在工厂方法模式中，它通常由 ConcreteFactoryB 进行创建并返回给客户端。
 *
 * 设计价值：
 * 1. 通过多态替代条件分支，减少客户端对具体类型的判断
 * 2. 支持在统一接口下封装差异化实现
 * 3. 与对应工厂解耦演化，便于独立扩展
 */
public class ConcreteProductB implements Product {

    @Override
    public void use() {
        // 这里演示产品B的行为入口，真实项目中可承载不同于A的业务处理
        System.out.println("使用具体产品B的功能");
    }
}

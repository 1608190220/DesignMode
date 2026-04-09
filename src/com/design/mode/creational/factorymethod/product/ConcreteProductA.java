package com.design.mode.creational.factorymethod.product;

/**
 * 具体产品 A
 *
 * 该类是 Product 的一种实现，代表“产品族中的 A 类型产品”。
 * 它通常与 ConcreteFactoryA 配对出现，由对应工厂负责实例化。
 *
 * 职责边界：
 * 1. 专注于产品 A 自身的业务行为实现
 * 2. 不关心对象创建过程（由工厂负责）
 * 3. 对外仅通过 Product 抽象参与协作
 */
public class ConcreteProductA implements Product {

    @Override
    public void use() {
        // 这里演示产品A的行为入口，真实项目中可替换为具体业务逻辑
        System.out.println("使用具体产品A的功能");
    }
}

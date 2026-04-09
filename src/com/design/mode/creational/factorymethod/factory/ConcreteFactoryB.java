package com.design.mode.creational.factorymethod.factory;

import com.design.mode.creational.factorymethod.product.ConcreteProductB;
import com.design.mode.creational.factorymethod.product.Product;

/**
 * 具体工厂 B
 *
 * 该类实现抽象工厂中的工厂方法，负责创建 ConcreteProductB。
 * 它与 ConcreteFactoryA 一起构成平行扩展结构，用于演示开闭原则。
 *
 * 使用建议：
 * 1. 当系统需要新增产品类型时，优先新增“产品 + 对应工厂”
 * 2. 尽量避免在已有工厂中增加大量条件分支
 * 3. 保持每个工厂职责单一，便于维护与测试
 */
public class ConcreteFactoryB extends Factory {

    @Override
    public Product createProduct() {
        // 这里可加入产品B专属的装配或初始化逻辑
        System.out.println("工厂B正在创建产品B...");
        return new ConcreteProductB();
    }
}

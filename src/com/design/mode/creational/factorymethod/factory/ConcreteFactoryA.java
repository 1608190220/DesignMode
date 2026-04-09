package com.design.mode.creational.factorymethod.factory;

import com.design.mode.creational.factorymethod.product.ConcreteProductA;
import com.design.mode.creational.factorymethod.product.Product;

/**
 * 具体工厂 A
 *
 * 该类实现抽象工厂中的工厂方法，负责创建 ConcreteProductA。
 * 它体现了“一个具体工厂对应一种具体产品”的工厂方法模式实践方式。
 *
 * 优势：
 * 1. 创建逻辑集中在工厂内，客户端无需关注产品构造细节
 * 2. 当产品 A 的创建规则变化时，只需要修改本类
 * 3. 与其他工厂并行扩展，互不影响
 */
public class ConcreteFactoryA extends Factory {

    @Override
    public Product createProduct() {
        // 这里可加入产品A专属的创建前校验、配置加载等逻辑
        System.out.println("工厂A正在创建产品A...");
        return new ConcreteProductA();
    }
}

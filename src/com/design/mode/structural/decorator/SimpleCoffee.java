package com.design.mode.structural.decorator;

/**
 * 具体组件（Concrete Component）
 * 
 * 装饰器模式中的具体组件角色，实现了抽象组件接口，是被装饰的原始对象。
 * 这是装饰器链的起点，提供最基础的功能实现。
 * 
 * 设计特点：
 * - 实现 Coffee 接口，提供基础功能
 * - 不持有其他 Coffee 对象的引用
 * - 可以被多个装饰器嵌套装饰
 * 
 * 类比Java I/O：
 * - FileInputStream、ByteArrayInputStream 就是具体组件
 * - 它们提供最基础的 I/O 功能
 * - 可以被 BufferedInputStream、DataInputStream 等装饰器包装
 */
public class SimpleCoffee implements Coffee {

    /**
     * 计算基础咖啡的价格
     * 
     * 这是装饰器链的起点，返回基础咖啡的价格。
     * 后续的装饰器会在这个基础上添加各自配料的价格。
     * 
     * @return 基础咖啡的价格，固定为10.0元
     */
    @Override
    public double cost() {
        return 10.0;
    }

    /**
     * 获取基础咖啡的描述
     * 
     * 这是装饰器链的起点，返回基础咖啡的描述。
     * 后续的装饰器会在这个基础上添加各自配料的描述。
     * 
     * @return 基础咖啡的描述字符串
     */
    @Override
    public String getDescription() {
        return "基础咖啡";
    }
}

package com.design.mode.structural.decorator;

/**
 * 具体装饰器类 - 牛奶装饰器
 * 
 * 装饰器模式中的具体装饰器角色，继承抽象装饰器，添加牛奶配料的功能。
 * 
 * 设计特点：
 * - 继承 CoffeeDecorator 抽象类
 * - 通过 super 调用委托给被装饰对象
 * - 在委托的基础上添加自己的功能（价格和描述）
 * 
 * 工作原理：
 * 1. cost() 方法：先调用 super.cost() 获取被装饰对象的价格，再加上牛奶的价格
 * 2. getDescription() 方法：先调用 super.getDescription() 获取被装饰对象的描述，再追加牛奶的描述
 * 
 * 类比Java I/O：
 * - BufferedInputStream、DataInputStream 就是具体装饰器
 * - 它们在基础流的功能上添加了缓冲、数据类型读取等功能
 */
public class MilkDecorator extends CoffeeDecorator {

    /**
     * 牛奶的价格常量
     * 使用 static final 修饰，确保不可变且全局共享
     */
    private static final double MILK_PRICE = 2.0;

    /**
     * 构造函数
     * 
     * 接收被装饰的咖啡对象，并传递给父类构造函数。
     * 
     * @param coffee 被装饰的咖啡对象，可以是具体组件或另一个装饰器
     */
    public MilkDecorator(Coffee coffee) {
        super(coffee);
    }

    /**
     * 计算添加牛奶后的咖啡价格
     * 
     * 先通过 super.cost() 委托调用被装饰对象的 cost() 方法，
     * 然后在其基础上加上牛奶的价格。
     * 
     * @return 添加牛奶后的总价格
     */
    @Override
    public double cost() {
        return super.cost() + MILK_PRICE;
    }

    /**
     * 获取添加牛奶后的咖啡描述
     * 
     * 先通过 super.getDescription() 委托调用被装饰对象的 getDescription() 方法，
     * 然后在其基础上追加牛奶的描述。
     * 
     * @return 添加牛奶后的描述字符串
     */
    @Override
    public String getDescription() {
        return super.getDescription() + " + 牛奶";
    }
}

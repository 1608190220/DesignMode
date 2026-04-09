package com.design.mode.structural.decorator;

/**
 * 抽象装饰器（Decorator）
 * 
 * 装饰器模式中的抽象装饰器角色，实现了组件接口，并持有一个被装饰对象的引用。
 * 这是装饰器模式的核心，通过组合而非继承来扩展功能。
 * 
 * 关键设计特点：
 * 1. 实现了与被装饰对象相同的接口（Coffee）- 保证透明性
 * 2. 持有一个被装饰对象的引用（被装饰的Coffee）- 通过组合扩展功能
 * 3. 将方法调用委托给被装饰对象，并添加额外的行为
 * 4. 使用 protected 修饰符，允许子类访问被装饰对象
 * 
 * 设计原则体现：
 * - 组合优于继承（Favor Composition Over Inheritance）
 * - 开闭原则（Open/Closed Principle）：对扩展开放，对修改关闭
 * 
 * 类比Java I/O：
 * - FilterInputStream 就是这个抽象装饰器
 * - 它持有 InputStream 的引用
 * - 所有具体装饰器（如 BufferedInputStream）都继承自它
 */
public abstract class CoffeeDecorator implements Coffee {

    /**
     * 被装饰的咖啡对象
     * 
     * 这是装饰器模式的关键：通过组合而非继承来扩展功能。
     * 使用 protected 修饰符，确保子类可以访问这个引用。
     * 
     * 这个引用可以指向：
     * - 具体组件（SimpleCoffee）
     * - 另一个装饰器（MilkDecorator、SugarDecorator等）
     * - 从而形成装饰器链
     */
    protected Coffee decoratedCoffee;

    /**
     * 构造函数，接收一个咖啡对象
     * 
     * 通过构造函数注入被装饰的咖啡对象，这是依赖注入的一种形式。
     * 
     * @param coffee 被装饰的咖啡对象，可以是具体组件，也可以是另一个装饰器
     */
    public CoffeeDecorator(Coffee coffee) {
        this.decoratedCoffee = coffee;
    }

    /**
     * 计算咖啡的价格
     * 
     * 默认实现：直接委托给被装饰对象。
     * 具体装饰器会重写这个方法，在委托的基础上添加自己的价格。
     * 
     * @return 咖啡的价格
     */
    @Override
    public double cost() {
        return decoratedCoffee.cost();
    }

    /**
     * 获取咖啡的描述
     * 
     * 默认实现：直接委托给被装饰对象。
     * 具体装饰器会重写这个方法，在委托的基础上添加自己的描述。
     * 
     * @return 咖啡的描述
     */
    @Override
    public String getDescription() {
        return decoratedCoffee.getDescription();
    }
}

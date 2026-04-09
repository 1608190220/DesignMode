package com.design.mode.structural.bridge;

/**
 * 正方形（RefinedAbstraction）- 具体抽象部分
 * 
 * 桥接模式中的扩充抽象类，继承自抽象部分（Shape），提供正方形的具体实现。
 * 
 * 具体抽象部分特点：
 * - 继承 Abstraction 抽象类（Shape）
 * - 提供具体的业务逻辑实现
 * - 可以有多个具体抽象类，彼此独立变化
 * - 通过调用实现部分（Color）的方法来完成功能
 * 
 * 在本示例中：
 * - Circle 是圆形的具体抽象
 * - Square 是正方形的具体抽象
 * - 两者可以独立变化，添加新形状不影响颜色
 */
public class Square extends Shape {
    
    /**
     * 构造方法
     * 
     * 接收颜色实现部分，并传递给父类构造函数。
     * 
     * @param color 颜色实现部分，可以是 Red 或 Blue
     */
    public Square(Color color) {
        super(color);
    }
    
    /**
     * 绘制正方形
     * 
     * 具体实现 Shape 抽象类的 draw() 方法。
     * 在实现中，调用实现部分（Color）的 applyColor() 方法来获取颜色信息。
     * 这是桥接模式的典型用法：抽象部分调用实现部分的方法。
     * 
     * @return 绘制结果字符串，如"用一个蓝色的正方形"
     */
    @Override
    public String draw() {
        return "用一个" + color.applyColor() + "的正方形";
    }
}

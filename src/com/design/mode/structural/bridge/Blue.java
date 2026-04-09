package com.design.mode.structural.bridge;

/**
 * 蓝色（ConcreteImplementor）- 具体实现部分
 * 
 * 桥接模式中的具体实现类，实现了 Color 接口，提供蓝色的具体实现。
 * 
 * 具体实现类特点：
 * - 实现 Implementor 接口（Color）
 * - 提供具体的业务逻辑实现
 * - 可以有多个具体实现类，彼此独立变化
 * - 不依赖于抽象部分
 * 
 * 在本示例中：
 * - Red 是红色的具体实现
 * - Blue 是蓝色的具体实现
 * - 两者可以独立变化，添加新颜色不影响形状
 */
public class Blue implements Color {
    
    /**
     * 应用蓝色
     * 
     * 具体实现 Color 接口的方法，返回蓝色的颜色名称。
     * 这是实现部分的具体业务逻辑。
     * 
     * @return 颜色名称 "蓝色"
     */
    @Override
    public String applyColor() {
        return "蓝色";
    }
}

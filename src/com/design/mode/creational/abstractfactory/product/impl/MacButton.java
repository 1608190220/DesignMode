package com.design.mode.creational.abstractfactory.product.impl;

import com.design.mode.creational.abstractfactory.product.Button;

/**
 * 具体产品：Mac风格的按钮
 *
 * 该类实现 Button 接口，封装 Mac 风格按钮的外观与交互反馈。
 * 它通常由 MacFactory 统一创建并交由客户端使用。
 */
public class MacButton implements Button {

    @Override
    public void render() {
        // 模拟 Mac 按钮渲染效果（圆角、毛玻璃等视觉特征）
        System.out.println("渲染 Mac 风格的按钮：");
        System.out.println(" └─ 带有圆角和毛玻璃效果");
    }

    @Override
    public void onClick() {
        // 模拟 Mac 平台点击事件回调
        System.out.println("Mac 按钮被点击，触发 Mac 风格的事件处理");
    }
}

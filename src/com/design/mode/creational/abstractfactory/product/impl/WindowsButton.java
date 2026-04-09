package com.design.mode.creational.abstractfactory.product.impl;

import com.design.mode.creational.abstractfactory.product.Button;

/**
 * 具体产品：Windows风格的按钮
 *
 * 该类封装 Windows 按钮的渲染效果与点击行为。
 * 作为产品族成员之一，通常由 WindowsFactory 统一创建。
 */
public class WindowsButton implements Button {

    @Override
    public void render() {
        // 模拟 Windows 按钮的视觉特征
        System.out.println("渲染 Windows 风格的按钮：");
        System.out.println(" └─ 带有蓝色边框和'确定'文字");
    }

    @Override
    public void onClick() {
        // 模拟 Windows 平台的按钮交互事件
        System.out.println("Windows 按钮被点击，触发 Windows 风格的事件处理");
    }
}

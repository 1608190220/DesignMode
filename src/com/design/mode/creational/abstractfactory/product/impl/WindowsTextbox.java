package com.design.mode.creational.abstractfactory.product.impl;

import com.design.mode.creational.abstractfactory.product.Textbox;

/**
 * 具体产品：Windows风格的文本框
 *
 * 该类实现 Textbox 接口，模拟 Windows 文本框的渲染与文本输入行为。
 * 与 MacTextbox 一样遵循统一抽象接口，但呈现不同风格实现。
 */
public class WindowsTextbox implements Textbox {

    private String text = "";

    @Override
    public void render() {
        System.out.println("渲染 Windows 风格的文本框：");
        System.out.println(" ┌---------------------┐");
        // 控制台渲染示例：为空则显示空白，有值则直接展示
        System.out.println(" │ " + (text.isEmpty() ? "                " : text) + " │");
        System.out.println(" └---------------------┘");
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
        // 模拟输入完成后的状态反馈
        System.out.println("Windows 文本框内容设置为: " + text);
    }
}

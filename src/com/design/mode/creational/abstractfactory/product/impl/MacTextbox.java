package com.design.mode.creational.abstractfactory.product.impl;

import com.design.mode.creational.abstractfactory.product.Textbox;

/**
 * 具体产品：Mac风格的文本框
 *
 * 该类实现 Textbox 抽象接口，模拟 Mac 风格文本框的渲染和文本读写。
 * 通过固定宽度输出与空白填充，保持控制台渲染整齐。
 */
public class MacTextbox implements Textbox {

    private String text = "";

    @Override
    public void render() {
        System.out.println("渲染 Mac 风格的文本框：");
        System.out.println(" ╭---------------------╮");
        // 为空时输出占位空格；有内容时进行长度对齐
        System.out.println(" │ " + (text.isEmpty() ? "                " : padString(text, 16)) + " │");
        System.out.println(" ╰---------------------╯");
    }

    @Override
    public String getText() {
        return text;
    }

    @Override
    public void setText(String text) {
        this.text = text;
        // 模拟文本输入后的反馈输出
        System.out.println("Mac 文本框内容设置为: " + text);
    }

    /**
     * 辅助方法：将字符串填充到指定长度
     *
     * @param str 原始字符串
     * @param length 目标长度
     * @return 填充或截断后的字符串
     */
    private String padString(String str, int length) {
        if (str.length() >= length) {
            return str.substring(0, length);
        }
        StringBuilder sb = new StringBuilder(str);
        while (sb.length() < length) {
            sb.append(" ");
        }
        return sb.toString();
    }
}

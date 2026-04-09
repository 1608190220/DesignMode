package com.design.mode.creational.abstractfactory.product;

/**
 * 抽象产品C：文本框
 *
 * 定义文本输入组件的公共接口，屏蔽不同平台渲染与存储细节。
 * 通过该接口，客户端可以统一执行文本渲染、读取与设置操作。
 */
public interface Textbox {

    /**
     * 文本框的渲染方法
     *
     * 负责将当前文本框外观与内容输出到界面。
     */
    void render();

    /**
     * 获取文本框的内容
     *
     * @return 当前文本内容
     */
    String getText();

    /**
     * 设置文本框的内容
     *
     * @param text 需要写入文本框的内容
     */
    void setText(String text);
}

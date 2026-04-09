package com.design.mode.creational.abstractfactory.factory;

import com.design.mode.creational.abstractfactory.product.Button;
import com.design.mode.creational.abstractfactory.product.Checkbox;
import com.design.mode.creational.abstractfactory.product.Textbox;

/**
 * 抽象工厂接口
 *
 * 该接口定义“产品族创建规范”，用于一次性创建同一风格的 UI 组件集合。
 * 在本示例中，一套产品族包含：
 * - Button（按钮）
 * - Checkbox（复选框）
 * - Textbox（文本框）
 *
 * 约束要点：
 * 1. 同一个具体工厂返回的组件应来自同一风格体系
 * 2. 客户端只依赖本接口，不依赖具体工厂实现
 * 3. 新增产品族时，只需新增具体工厂并实现该接口
 */
public interface GUIFactory {

    /**
     * 创建按钮
     *
     * @return 与当前工厂风格一致的按钮产品
     */
    Button createButton();

    /**
     * 创建复选框
     *
     * @return 与当前工厂风格一致的复选框产品
     */
    Checkbox createCheckbox();

    /**
     * 创建文本框
     *
     * @return 与当前工厂风格一致的文本框产品
     */
    Textbox createTextbox();
}

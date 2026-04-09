package com.design.mode.creational.abstractfactory.factory.impl;

import com.design.mode.creational.abstractfactory.factory.GUIFactory;
import com.design.mode.creational.abstractfactory.product.Button;
import com.design.mode.creational.abstractfactory.product.Checkbox;
import com.design.mode.creational.abstractfactory.product.Textbox;
import com.design.mode.creational.abstractfactory.product.impl.MacButton;
import com.design.mode.creational.abstractfactory.product.impl.MacCheckbox;
import com.design.mode.creational.abstractfactory.product.impl.MacTextbox;

/**
 * 具体工厂：Mac风格的工厂
 *
 * 负责创建 Mac 产品族中的全部组件。
 * 该工厂产出的 Button/Checkbox/Textbox 都遵循 Mac 视觉与交互规范，
 * 从而保证同一应用内组件风格统一。
 */
public class MacFactory implements GUIFactory {

    @Override
    public Button createButton() {
        // 统一由工厂封装具体产品构造细节
        System.out.println("MacFactory: 正在创建 Mac 风格的按钮...");
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        // 返回与当前工厂匹配的复选框实现
        System.out.println("MacFactory: 正在创建 Mac 风格的复选框...");
        return new MacCheckbox();
    }

    @Override
    public Textbox createTextbox() {
        // 返回与当前工厂匹配的文本框实现
        System.out.println("MacFactory: 正在创建 Mac 风格的文本框...");
        return new MacTextbox();
    }
}

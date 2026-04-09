package com.design.mode.creational.abstractfactory.factory.impl;

import com.design.mode.creational.abstractfactory.factory.GUIFactory;
import com.design.mode.creational.abstractfactory.product.Button;
import com.design.mode.creational.abstractfactory.product.Checkbox;
import com.design.mode.creational.abstractfactory.product.Textbox;
import com.design.mode.creational.abstractfactory.product.impl.WindowsButton;
import com.design.mode.creational.abstractfactory.product.impl.WindowsCheckbox;
import com.design.mode.creational.abstractfactory.product.impl.WindowsTextbox;

/**
 * 具体工厂：Windows风格的工厂
 *
 * 负责创建 Windows 产品族中的全部组件。
 * 通过该工厂创建的按钮、复选框、文本框可保持统一的 Windows 风格。
 *
 * 使用场景：
 * 1. 系统需要独立于其产品的创建、组合和表示时
 * 2. 产品体系中有多个产品族，系统只消费其中某一族的产品时
 * 3. 属于同一产品族的产品将一起使用时
 * 4. 提供一个产品类的库，所有产品以同样的接口出现，从而使客户端不依赖于具体实现
 */
public class WindowsFactory implements GUIFactory {

    @Override
    public Button createButton() {
        // 创建并返回 Windows 风格按钮实现
        System.out.println("WindowsFactory: 正在创建 Windows 风格的按钮...");
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        // 创建并返回 Windows 风格复选框实现
        System.out.println("WindowsFactory: 正在创建 Windows 风格的复选框...");
        return new WindowsCheckbox();
    }

    @Override
    public Textbox createTextbox() {
        // 创建并返回 Windows 风格文本框实现
        System.out.println("WindowsFactory: 正在创建 Windows 风格的文本框...");
        return new WindowsTextbox();
    }
}

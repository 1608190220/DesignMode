package com.design.mode.creational.abstractfactory;

import com.design.mode.creational.abstractfactory.factory.GUIFactory;
import com.design.mode.creational.abstractfactory.product.Button;
import com.design.mode.creational.abstractfactory.product.Checkbox;
import com.design.mode.creational.abstractfactory.product.Textbox;

/**
 * 应用程序类 - 客户端代码
 *
 * 该类是抽象工厂模式中的“客户端角色”：
 * - 只依赖 GUIFactory 与抽象产品接口
 * - 不直接依赖任何具体产品实现（如 WindowsButton / MacButton）
 * - 通过构造注入具体工厂，在运行时切换整套 UI 风格
 *
 * 设计收益：
 * 1. 业务逻辑与具体平台实现解耦
 * 2. 可在不改客户端核心流程的前提下扩展新产品族
 * 3. 能确保一次创建的组件彼此兼容、视觉风格一致
 */
public class Application {

    private Button button;
    private Checkbox checkbox;
    private Textbox textbox;

    /**
     * 通过构造函数接收工厂，创建产品族
     *
     * 构造时一次性创建一组相关组件，保证 button/checkbox/textbox
     * 来自同一具体工厂，从而避免风格混搭。
     *
     * @param factory 抽象工厂
     */
    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
        this.textbox = factory.createTextbox();
    }

    /**
     * 绘制UI界面
     *
     * 演示流程：
     * 1. 渲染按钮并触发点击事件
     * 2. 渲染并切换复选框状态
     * 3. 渲染文本框并写入文本内容
     */
    public void drawUI() {
        System.out.println("\n========== 开始绘制应用程序界面 ==========");
        System.out.println("\n┌--------------------------┐");
        System.out.println("│      我的应用程序      │");
        System.out.println("└--------------------------┘\n");

        System.out.println("【1. 渲染按钮】");
        // 通过抽象接口调用，实际行为由具体产品决定
        button.render();
        button.onClick();

        System.out.println("\n【2. 渲染复选框】");
        checkbox.render();
        checkbox.setChecked(true);
        checkbox.render();

        System.out.println("\n【3. 渲染文本框】");
        textbox.render();
        textbox.setText("Hello, Abstract Factory!");
        textbox.render();

        System.out.println("\n========== 界面绘制完成 ==========\n");
        System.out.println("╔════════════════════════════════════════════════╗");
        System.out.println("║  所有UI组件保持风格一致，协同工作！          ║");
        System.out.println("╚════════════════════════════════════════════════╝\n");
    }
}

package com.design.mode.creational.abstractfactory;

import com.design.mode.creational.abstractfactory.factory.GUIFactory;
import com.design.mode.creational.abstractfactory.factory.impl.MacFactory;
import com.design.mode.creational.abstractfactory.factory.impl.WindowsFactory;

/**
 * 抽象工厂模式测试类
 *
 * 本示例以“跨平台 GUI 组件”作为业务背景，演示如何通过抽象工厂模式
 * 一次性创建同一产品族中的多个组件（Button/Checkbox/Textbox），并保证风格一致。
 *
 * 抽象工厂模式的适用场景：
 * 1. 系统需要独立于其产品的创建、组合和表示时
 * 2. 产品体系中有多个产品族，系统只消费其中某一族的产品时
 * 3. 属于同一产品族的产品将一起使用时
 * 4. 提供一个产品类的库，所有产品以同样的接口出现，从而使客户端不依赖于具体实现
 *
 * 抽象工厂和工厂方法的区别：
 * 1. 工厂方法：一个抽象产品，多个具体产品，一个抽象工厂，多个具体工厂
 * 2. 抽象工厂：多个抽象产品，每个抽象产品有多个具体产品，一个抽象工厂，多个具体工厂
 * 3. 工厂方法：创建单个产品
 * 4. 抽象工厂：创建一组相关产品（产品族）
 *
 * 抽象工厂的优点：
 * 1. 保证客户端使用的产品来自同一个产品族（风格一致）
 * 2. 易于扩展产品族，符合开闭原则
 * 3. 客户端与具体产品分离，实现了解耦
 *
 * 抽象工厂的缺点：
 * 1. 难以支持新种类的产品，需要修改抽象工厂接口和所有具体工厂
 * 2. 增加了系统的抽象性和理解难度
 *
 * 示例关注点：
 * - 如何根据环境（操作系统）选择具体工厂
 * - 如何让客户端始终依赖抽象接口而非具体实现类
 */
public class AbstractFactoryTest {

    /**
     * 根据操作系统类型获取对应的工厂
     *
     * 该方法相当于一个简单的“工厂选择器”，用于把运行时环境信息
     * （如 Windows / Mac）映射到对应的产品族工厂。
     *
     * @param os 操作系统类型
     * @return 对应的工厂
     */
    public static GUIFactory getFactory(String os) {
        switch (os) {
            case "Windows":
                return new WindowsFactory();
            case "Mac":
                return new MacFactory();
            default:
                throw new IllegalArgumentException("未知操作系统: " + os);
        }
    }

    /**
     * 演示入口：
     * 1. 分别创建 Windows 与 Mac 两套应用界面
     * 2. 观察同一产品族内组件风格保持一致
     * 3. 输出模式价值总结，帮助理解抽象工厂与工厂方法的差异
     *
     * @param args 命令行参数（示例中未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║          抽象工厂模式测试 - GUI组件框架模拟               ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        System.out.println("========== 场景1：为 Windows 系统创建应用 ==========");
        System.out.println("系统检测：当前为 Windows 系统");
        System.out.println("→ 将使用 Windows 风格的UI组件...\n");

        // 根据系统类型选择具体工厂，再由应用统一创建该族全部组件
        GUIFactory windowsFactory = getFactory("Windows");
        Application windowsApp = new Application(windowsFactory);
        windowsApp.drawUI();

        System.out.println("\n========== 场景2：为 Mac 系统创建应用 ==========");
        System.out.println("系统检测：当前为 Mac 系统");
        System.out.println("→ 将使用 Mac 风格的UI组件...\n");

        GUIFactory macFactory = getFactory("Mac");
        Application macApp = new Application(macFactory);
        macApp.drawUI();

        System.out.println("\n========== 总结 ==========");
        System.out.println("抽象工厂模式的核心价值：");
        System.out.println("1. 确保同一产品族的组件风格一致");
        System.out.println("2. 客户端只依赖抽象接口，不依赖具体实现");
        System.out.println("3. 新增产品族时只需要增加新的具体工厂类");
        System.out.println("4. 符合开闭原则：对扩展开放，对修改关闭");
        System.out.println("\n举例：");
        System.out.println("- Windows 应用使用 WinAPI 风格的按钮、复选框、文本框");
        System.out.println("- Mac 应用使用 Cocoa 风格的按钮、复选框、文本框");
        System.out.println("- 两种风格不能混用，各自风格内部保持一致");
    }
}

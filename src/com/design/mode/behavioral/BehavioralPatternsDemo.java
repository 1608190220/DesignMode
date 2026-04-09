package com.design.mode.behavioral;

/**
 * 行为型设计模式统一演示入口
 *
 * 十一种行为型模式概览：
 * 1. 策略模式（Strategy）
 *    - 场景：定义一系列算法，把它们封装起来，并且使它们可以互相替换
 *    - 核心：策略接口、具体策略、上下文
 *    - 优点：算法可以独立于使用它的客户端而变化
 *
 * 2. 观察者模式（Observer）
 *    - 场景：定义对象间的一种一对多依赖关系，当一个对象状态发生变化时，所有依赖它的对象都得到通知
 *    - 核心：主题、观察者
 *    - 优点：实现松耦合，支持广播通信
 *
 * 3. 命令模式（Command）
 *    - 场景：将请求封装为对象，使请求的发送者和接收者解耦
 *    - 核心：命令、接收者、调用者
 *    - 优点：支持撤销操作，支持宏命令
 *
 * 4. 状态模式（State）
 *    - 场景：允许对象在内部状态改变时改变它的行为
 *    - 核心：状态接口、具体状态、上下文
 *    - 优点：将状态转换逻辑集中管理
 *
 * 5. 责任链模式（Chain of Responsibility）
 *    - 场景：将请求从链的一端传递到另一端，直到有一个对象处理它
 *    - 核心：抽象处理器、具体处理器
 *    - 优点：降低耦合度，增强系统的灵活性
 *
 * 6. 模板方法模式（Template Method）
 *    - 场景：定义一个算法的骨架，而将一些步骤延迟到子类中
 *    - 核心：抽象类、具体子类
 *    - 优点：代码复用，控制子类扩展
 *
 * 7. 迭代器模式（Iterator）
 *    - 场景：提供一种方法顺序访问一个聚合对象中的各个元素，而不暴露其内部表示
 *    - 核心：迭代器接口、具体迭代器、聚合接口
 *    - 优点：简化客户端代码，支持多种遍历方式
 *
 * 8. 访问者模式（Visitor）
 *    - 场景：表示一个作用于某对象结构中的各元素的操作
 *    - 核心：访问者接口、具体访问者、元素接口
 *    - 优点：分离算法与对象结构
 *
 * 9. 中介者模式（Mediator）
 *    - 场景：用一个中介对象来封装一系列的对象交互
 *    - 核心：中介者接口、具体中介者、同事类
 *    - 优点：减少对象间的直接依赖
 *
 * 10. 备忘录模式（Memento）
 *     - 场景：在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态
 *     - 核心：原发器、备忘录、管理者
 *     - 优点：支持撤销操作，保存对象状态
 *
 * 11. 解释器模式（Interpreter）
 *     - 场景：给定一个语言，定义它的文法的一种表示，并定义一个解释器
 *     - 核心：抽象表达式、终结符表达式、非终结符表达式
 *     - 优点：易于改变和扩展文法
 */
public class BehavioralPatternsDemo {

    public static void main(String[] args) {
        System.out.println("==============================================================");
        System.out.println("          Java 行为型设计模式演示系统                              ");
        System.out.println("          —— 十一种行为型模式完整实现                                ");
        System.out.println("==============================================================\n");

        boolean exit = false;
        while (!exit) {
            showMenu();
            int choice = readChoice();

            switch (choice) {
                case 1:
                    System.out.println("\n【正在启动：策略模式测试】\n");
                    runStrategyTest();
                    pressEnterToContinue();
                    break;
                case 2:
                    System.out.println("\n【正在启动：观察者模式测试】\n");
                    runObserverTest();
                    pressEnterToContinue();
                    break;
                case 3:
                    System.out.println("\n【正在启动：命令模式测试】\n");
                    runCommandTest();
                    pressEnterToContinue();
                    break;
                case 4:
                    System.out.println("\n【正在启动：状态模式测试】\n");
                    runStateTest();
                    pressEnterToContinue();
                    break;
                case 5:
                    System.out.println("\n【正在启动：责任链模式测试】\n");
                    runChainOfResponsibilityTest();
                    pressEnterToContinue();
                    break;
                case 6:
                    System.out.println("\n【正在启动：迭代器模式测试】\n");
                    runIteratorTest();
                    pressEnterToContinue();
                    break;
                case 7:
                    System.out.println("\n【正在启动：访问者模式测试】\n");
                    runVisitorTest();
                    pressEnterToContinue();
                    break;
                case 8:
                    System.out.println("\n【正在启动：中介者模式测试】\n");
                    runMediatorTest();
                    pressEnterToContinue();
                    break;
                case 9:
                    System.out.println("\n【正在启动：备忘录模式测试】\n");
                    runMementoTest();
                    pressEnterToContinue();
                    break;
                case 10:
                    System.out.println("\n【正在启动：模板方法模式测试】\n");
                    runTemplateMethodTest();
                    pressEnterToContinue();
                    break;
                case 11:
                    System.out.println("\n【正在启动：解释器模式测试】\n");
                    runInterpreterTest();
                    pressEnterToContinue();
                    break;
                case 12:
                    System.out.println("\n【正在启动：所有模式完整演示】\n");
                    runAllTests();
                    pressEnterToContinue();
                    break;
                case 0:
                    exit = true;
                    System.out.println("\n感谢使用，再见！");
                    break;
                default:
                    System.out.println("\n无效选择，请重新输入！");
            }
        }
    }

    /**
     * 显示系统菜单
     */
    private static void showMenu() {
        System.out.println("==================================================");
        System.out.println("                    主菜单                     ");
        System.out.println("==================================================");
        System.out.println("  1. 策略模式（Strategy Pattern）                         ");
        System.out.println("  2. 观察者模式（Observer Pattern）                       ");
        System.out.println("  3. 命令模式（Command Pattern）                         ");
        System.out.println("  4. 状态模式（State Pattern）                           ");
        System.out.println("  5. 责任链模式（Chain of Responsibility Pattern）        ");
        System.out.println("  6. 迭代器模式（Iterator Pattern）                      ");
        System.out.println("  7. 访问者模式（Visitor Pattern）                       ");
        System.out.println("  8. 中介者模式（Mediator Pattern）                      ");
        System.out.println("  9. 备忘录模式（Memento Pattern）                       ");
        System.out.println(" 10. 模板方法模式（Template Method Pattern）               ");
        System.out.println(" 11. 解释器模式（Interpreter Pattern）                   ");
        System.out.println(" 12. 查看所有模式完整演示                                   ");
        System.out.println("  0. 退出系统                                               ");
        System.out.println("==================================================");
        System.out.print("请输入选项（0-12）：");
    }

    /**
     * 读取用户输入
     *
     * @return 用户选择的选项
     */
    private static int readChoice() {
        try {
            java.util.Scanner scanner = new java.util.Scanner(System.in);
            return scanner.nextInt();
        } catch (Exception e) {
            return 99;  // 无效选项
        }
    }

    /**
     * 等待用户按回车键继续
     */
    private static void pressEnterToContinue() {
        System.out.println("\n--------------------------------------------------");
        System.out.print("  按回车键返回主菜单...                                 ");
        System.out.println("\n--------------------------------------------------");
        try {
            System.in.read();
            System.in.skip(System.in.available());  // 清空输入缓冲区
        } catch (Exception e) {
            // 忽略异常
        }
    }

    /**
     * 运行策略模式测试
     */
    private static void runStrategyTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.strategy.StrategyTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("策略模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行观察者模式测试
     */
    private static void runObserverTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.observer.ObserverTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("观察者模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行命令模式测试
     */
    private static void runCommandTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.command.CommandTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("命令模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行状态模式测试
     */
    private static void runStateTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.state.StateTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("状态模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行责任链模式测试
     */
    private static void runChainOfResponsibilityTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.chainofresponsibility.ChainOfResponsibilityTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("责任链模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行迭代器模式测试
     */
    private static void runIteratorTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.iterator.IteratorTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("迭代器模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行访问者模式测试
     */
    private static void runVisitorTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.visitor.VisitorTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("访问者模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行中介者模式测试
     */
    private static void runMediatorTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.mediator.MediatorTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("中介者模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行备忘录模式测试
     */
    private static void runMementoTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.memento.MementoTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("备忘录模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行解释器模式测试
     */
    private static void runInterpreterTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.interpreter.InterpreterTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("解释器模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行模板方法模式测试
     */
    private static void runTemplateMethodTest() {
        try {
            // 动态加载类，避免编译错误
            Class.forName("com.design.mode.behavioral.templatemethod.TemplateMethodTest")
                 .getMethod("main", String[].class)
                 .invoke(null, (Object) new String[]{});
        } catch (ClassNotFoundException e) {
            System.out.println("模板方法模式尚未实现，敬请期待！");
        } catch (Exception e) {
            System.out.println("测试执行失败：" + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * 运行所有模式的测试
     */
    private static void runAllTests() {
        System.out.println("\n==================================================");
        System.out.println("          开始执行所有行为型模式演示                      ");
        System.out.println("==================================================");

        System.out.println("\n--------------------------------------------------");
        System.out.println("1/10 - 策略模式运行中...");
        runStrategyTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("2/10 - 观察者模式运行中...");
        runObserverTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("3/10 - 命令模式运行中...");
        runCommandTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("4/10 - 状态模式运行中...");
        runStateTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("5/10 - 责任链模式运行中...");
        runChainOfResponsibilityTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("6/10 - 迭代器模式运行中...");
        runIteratorTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("7/10 - 访问者模式运行中...");
        runVisitorTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("8/10 - 中介者模式运行中...");
        runMediatorTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("9/11 - 备忘录模式运行中...");
        runMementoTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("10/11 - 模板方法模式运行中...");
        runTemplateMethodTest();

        System.out.println("\n--------------------------------------------------");
        System.out.println("11/11 - 解释器模式运行中...");
        runInterpreterTest();

        System.out.println("\n==================================================");
        System.out.println("          所有行为型模式演示完成！                        ");
        System.out.println("==================================================");
    }
}
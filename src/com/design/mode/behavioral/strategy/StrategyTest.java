package com.design.mode.behavioral.strategy;

/**
 * 策略模式测试类
 * 演示策略模式（Strategy Pattern）的结构与运行行为
 *
 * 测试目标：
 * 1. 验证不同策略在统一上下文中的可替换性。
 * 2. 展示运行时动态切换策略的使用方式。
 * 3. 说明“面向抽象编程”如何降低条件分支复杂度。
 *
 * 策略模式（Strategy Pattern）详解：
 *
 * 一、模式意图
 * 定义一系列算法，把它们封装起来，并且使它们可以互相替换。
 * 策略模式使得算法可以独立于使用它的客户而变化。
 *
 * 二、模式结构
 * 1. 策略接口（Strategy）: 定义所有支持的算法的公共接口
 * 2. 具体策略（Concrete Strategy）: 实现策略接口，提供具体的算法实现
 * 3. 上下文（Context）: 维护一个对策略对象的引用，负责在运行时切换策略
 *
 * 三、适用场景
 * 1. 当一个系统需要动态地在几种算法中选择一种时
 * 2. 当一个对象有很多行为，而使用if-else或switch-case语句来选择时
 * 3. 当需要算法的变体可以被客户端独立选择时
 *
 * 四、优缺点
 * 优点：
 * - 算法可以独立于使用它的客户端而变化
 * - 避免使用多重条件判断
 * - 符合开闭原则
 * 
 * 缺点：
 * - 客户端必须知道所有的策略类
 * - 策略类可能会增多
 */
public class StrategyTest {
    
    /**
     * 程序入口
     * 
     * 通过多个测试用例演示策略模式的工作原理：
     * 1. 测试策略A（快速排序）- 验证单个策略的执行
     * 2. 测试策略B（冒泡排序）- 展示可替换性
     * 3. 测试策略C（插入排序）- 展示更多策略选择
     * 4. 测试运行时切换策略 - 展示动态切换能力
     * 
     * 测试设计目标：
     * - 验证不同策略在统一上下文中的可替换性
     * - 展示运行时动态切换策略的使用方式
     * - 说明"面向抽象编程"如何降低条件分支复杂度
     *
     * @param args 命令行参数（本示例不使用）
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("                  策略模式测试                          ");
        System.out.println("==================================================");
        
        // 测试1：使用策略A（快速排序）
        testStrategyA();
        
        // 测试2：使用策略B（冒泡排序）
        testStrategyB();
        
        // 测试3：使用策略C（插入排序）
        testStrategyC();
        
        // 测试4：运行时切换策略
        testStrategySwitching();
        
        System.out.println("==================================================");
        System.out.println("                  模式总结                            ");
        System.out.println("==================================================");
        System.out.println("  1. 策略模式定义了算法族，分别封装起来，让它们之间可以互相替换");
        System.out.println("  2. 策略模式让算法的变化独立于使用算法的客户端");
        System.out.println("  3. Context类持有Strategy引用，负责在运行时切换策略");
        System.out.println("  4. 客户端可以根据需要选择不同的策略");
        System.out.println("  5. 符合开闭原则，新增策略只需实现Strategy接口");
        System.out.println("==================================================");
    }
    
    /**
     * 场景1：测试策略A（快速排序）
     *
     * 说明：创建上下文并注入策略A，验证基础执行链路。
     */
    private static void testStrategyA() {
        System.out.println("\n========== 测试1：使用策略A（快速排序） ==========");
        // 创建策略A
        Strategy strategyA = new ConcreteStrategyA();
        // 创建上下文，设置初始策略
        Context context = new Context(strategyA);
        // 执行策略
        context.executeStrategy();
    }
    
    /**
     * 场景2：测试策略B（冒泡排序）
     *
     * 说明：保持调用方式不变，仅替换策略实现。
     */
    private static void testStrategyB() {
        System.out.println("\n========== 测试2：使用策略B（冒泡排序） ==========");
        // 创建策略B
        Strategy strategyB = new ConcreteStrategyB();
        // 创建上下文，设置初始策略
        Context context = new Context(strategyB);
        // 执行策略
        context.executeStrategy();
    }
    
    /**
     * 场景3：测试策略C（插入排序）
     *
     * 说明：继续复用 Context，展示第三种可替换算法。
     */
    private static void testStrategyC() {
        System.out.println("\n========== 测试3：使用策略C（插入排序） ==========");
        // 创建策略C
        Strategy strategyC = new ConcreteStrategyC();
        // 创建上下文，设置初始策略
        Context context = new Context(strategyC);
        // 执行策略
        context.executeStrategy();
    }
    
    /**
     * 场景4：测试运行时切换策略
     *
     * 说明：同一个 Context 在运行过程中动态切换策略对象，
     * 验证策略模式的核心能力——行为可配置与可替换。
     */
    private static void testStrategySwitching() {
        System.out.println("\n========== 测试4：运行时切换策略 ==========");
        // 创建上下文，初始使用策略A
        Context context = new Context(new ConcreteStrategyA());
        
        System.out.println("初始策略（快速排序）:");
        context.executeStrategy();
        
        System.out.println("\n切换到策略B（冒泡排序）:");
        context.setStrategy(new ConcreteStrategyB());
        context.executeStrategy();
        
        System.out.println("\n切换到策略C（插入排序）:");
        context.setStrategy(new ConcreteStrategyC());
        context.executeStrategy();
        
        System.out.println("\n切换回策略A（快速排序）:");
        context.setStrategy(new ConcreteStrategyA());
        context.executeStrategy();
    }
}

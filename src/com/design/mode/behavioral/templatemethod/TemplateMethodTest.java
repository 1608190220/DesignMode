package com.design.mode.behavioral.templatemethod;

/**
 * 模板方法模式测试类
 * 演示模板方法模式（Template Method Pattern）的结构与运行效果
 *
 * 测试目标：
 * 1. 观察不同子类在同一模板流程中的行为差异。
 * 2. 验证模板方法对算法步骤顺序的约束能力。
 * 3. 展示默认步骤与可覆盖步骤的协作方式。
 *
 * 模板方法模式（Template Method Pattern）详解：
 *
 * 一、模式意图
 * 定义一个算法的骨架，而将一些步骤延迟到子类中。
 * 模板方法模式使得子类可以在不改变算法结构的情况下重定义算法的某些特定步骤。
 *
 * 二、模式结构
 * 1. 抽象类（Abstract Class）: 定义算法的骨架，包含模板方法和抽象方法
 * 2. 具体子类（Concrete Class）: 实现抽象类中的抽象方法，完成算法的具体步骤
 * 3. 模板方法（Template Method）: 定义算法的步骤顺序，通常被声明为final
 *
 * 三、适用场景
 * 1. 当多个类有相似的算法，但某些步骤的实现不同时
 * 2. 当需要控制子类的扩展时（只允许扩展特定的步骤）
 * 3. 当算法的结构相对稳定，但具体实现可能变化时
 *
 * 四、优缺点
 * 优点：
 * - 代码复用：将共同的代码放在父类中
 * - 控制扩展：子类只能在指定的点进行扩展
 * - 统一算法结构：确保所有子类遵循相同的算法步骤
 * 
 * 缺点：
 * - 增加了系统的抽象性和理解难度
 * - 可能导致类的数量增加
 */
public class TemplateMethodTest {
    
    /**
     * 程序入口
     *
     * @param args 命令行参数（本示例不使用）
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("                  模板方法模式测试                          ");
        System.out.println("==================================================");
        
        // 测试1：使用ConcreteClassA
        testConcreteClassA();
        
        // 测试2：使用ConcreteClassB
        testConcreteClassB();
        
        // 测试3：演示算法结构的一致性
        testAlgorithmStructure();
        
        System.out.println("==================================================");
        System.out.println("                  模式总结                            ");
        System.out.println("==================================================");
        System.out.println("  1. 模板方法模式定义了算法的骨架，而将一些步骤延迟到子类中");
        System.out.println("  2. 模板方法通常被声明为final，防止子类覆盖");
        System.out.println("  3. 抽象方法由子类实现，完成算法的具体步骤");
        System.out.println("  4. 具体方法由父类提供默认实现，子类可以选择性覆盖");
        System.out.println("  5. 模板方法模式确保了算法结构的一致性，同时允许子类定制特定步骤");
        System.out.println("==================================================");
    }
    
    /**
     * 场景1：测试 ConcreteClassA
     *
     * 重点观察：
     * - 子类A对抽象步骤的实现细节；
     * - 子类A对 cleanup 阶段的自定义覆盖行为。
     */
    private static void testConcreteClassA() {
        System.out.println("\n========== 测试1：使用ConcreteClassA ==========");
        AbstractClass concreteClassA = new ConcreteClassA();
        System.out.println("调用模板方法:");
        concreteClassA.templateMethod();
    }
    
    /**
     * 场景2：测试 ConcreteClassB
     *
     * 重点观察：
     * - 子类B使用不同业务逻辑实现抽象步骤；
     * - 子类B复用父类默认 cleanup 逻辑。
     */
    private static void testConcreteClassB() {
        System.out.println("\n========== 测试2：使用ConcreteClassB ==========");
        AbstractClass concreteClassB = new ConcreteClassB();
        System.out.println("调用模板方法:");
        concreteClassB.templateMethod();
    }
    
    /**
     * 场景3：演示算法结构一致性
     *
     * 结论：无论子类实现细节如何变化，算法步骤顺序始终由模板方法统一控制。
     */
    private static void testAlgorithmStructure() {
        System.out.println("\n========== 测试3：算法结构一致性 ==========");
        System.out.println("虽然ConcreteClassA和ConcreteClassB实现不同，但它们遵循相同的算法结构:");
        System.out.println("1. 初始化操作");
        System.out.println("2. 执行核心操作");
        System.out.println("3. 处理结果");
        System.out.println("4. 清理资源");
        System.out.println("\n这种一致性确保了所有子类都按照相同的流程执行，提高了代码的可维护性和可读性。");
    }
}

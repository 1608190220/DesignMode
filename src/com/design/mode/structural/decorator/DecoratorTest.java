package com.design.mode.structural.decorator;

/**
 * 装饰器模式测试类
 *
 * 装饰器模式（Decorator Pattern）详解：
 *
 * 一、模式意图
 * 动态地给一个对象添加额外的职责，提供比继承更有弹性的替代方案
 * 装饰器模式通过组合而非继承的方式，在运行时扩展对象的功能
 *
 * 二、模式结构
 * 1. 抽象组件（Component）: 定义对象的接口，可以被动态添加职责，这里是Coffee接口
 * 2. 具体组件（Concrete Component）: 被装饰的原始对象，可以给它添加额外的职责，这里是SimpleCoffee
 * 3. 抽象装饰器（Decorator）: 维持一个指向组件对象的引用，并实现与抽象组件相同的接口，这里是CoffeeDecorator
 * 4. 具体装饰器（Concrete Decorator）: 负责向组件添加新的职责，这里是各种配料装饰器
 *
 * 三、适用场景
 * 1. 在不影响其他对象的情况下，动态、透明地给单个对象添加职责
 * 2. 需要动态地给一个对象添加功能，这些功能也可以被动态撤销
 * 3. 当不能采用继承的方式对系统进行扩充时（如final类）
 * 4. 需要为对象添加很多不同的功能，且这些功能的排列组合产生很多子类时
 *
 * 四、优缺点
 * 优点：
 * - 比继承更灵活：动态地选择需要的功能，避免了继承带来的类爆炸问题
 * - 可以对一个对象进行多次装饰，创造出不同行为的组合
 * - 符合开闭原则：可以在不修改现有代码的情况下添加新的装饰器
 * - 装饰器和被装饰对象可以独立发展，不会相互耦合
 *
 * 缺点：
 * - 会产生很多小对象（装饰器），增加系统的复杂性
 * - 多层装饰比较复杂，调试困难
 * - 比继承更容易出错，特别是多层包装时
 *
 * 五、与继承的区别
 * 继承：
 * - 静态地在编译时确定行为
 * - 所有的子类都会继承相同的行为
 * - 可能会导致很多子类，引起类爆炸
 *
 * 装饰器：
 * - 动态地在运行时添加行为
 * - 可以混合和匹配多种行为
 * - 对象可以被多次装饰，创造灵活的组合
 *
 * 六、实际应用
 * 1. Java I/O流: InputStream、OutputStream、Reader、Writer等
 *    - FileInputStream是基础流（具体组件）
 *    - BufferedInputStream、DataInputStream是装饰器
 * 2. Java Collections: Collections.synchronizedList()、Collections.unmodifiableList()
 * 3. Spring框架: 各种BeanPostProcessor对Bean的装饰
 * 4. Servlet API: HttpServletRequestWrapper、HttpServletResponseWrapper
 *
 * 七、与代理模式的区别
 * 装饰器模式：
 * - 目的是为对象添加新功能
 * - 接口可以扩展（添加新方法）
 * - 装饰器可以嵌套
 *
 * 代理模式：
 * - 目的是控制对对象的访问
 * - 接口通常不扩展（保持接口一致）
 * - 代理通常不嵌套或嵌套较少
 */
public class DecoratorTest {

    /**
     * 主测试方法
     * 
     * 通过多个测试用例演示装饰器模式的工作原理和优势：
     * 1. 测试基础咖啡（无装饰）
     * 2. 测试单个装饰器（牛奶）
     * 3. 测试多个装饰器嵌套（牛奶+糖）
     * 4. 测试其他装饰器组合（香草+奶泡）
     * 5. 测试所有装饰器组合（豪华版）
     * 
     * 测试用例设计：
     * - 从简单到复杂，逐步展示装饰器的嵌套使用
     * - 对比不同组合的价格和描述
     * - 展示装饰器模式的灵活性和可扩展性
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  装饰器模式测试                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 测试1：基础咖啡，没有任何装饰器
        // 这是装饰器链的起点
        System.out.println("========== 测试1：基础咖啡 ==========");
        Coffee simpleCoffee = new SimpleCoffee();
        System.out.println("订单: " + simpleCoffee.getDescription());
        System.out.println("价格: ¥" + simpleCoffee.cost());
        System.out.println();

        // 测试2：基础咖啡 + 牛奶
        // 展示单个装饰器的使用
        System.out.println("========== 测试2：基础咖啡 + 牛奶 ==========");
        Coffee milkCoffee = new MilkDecorator(new SimpleCoffee());
        System.out.println("订单: " + milkCoffee.getDescription());
        System.out.println("价格: ¥" + milkCoffee.cost());
        System.out.println();

        // 测试3：基础咖啡 + 牛奶 + 糖
        // 展示多个装饰器的嵌套使用
        // 装饰器链：SugarDecorator -&gt; MilkDecorator -&gt; SimpleCoffee
        System.out.println("========== 测试3：基础咖啡 + 牛奶 + 糖 ==========");
        Coffee milkSugarCoffee = new SugarDecorator(new MilkDecorator(new SimpleCoffee()));
        System.out.println("订单: " + milkSugarCoffee.getDescription());
        System.out.println("价格: ¥" + milkSugarCoffee.cost());
        System.out.println();

        // 测试4：基础咖啡 + 香草 + 奶泡
        // 展示其他装饰器的组合
        // 装饰器链：WhipDecorator -&gt; VanillaDecorator -&gt; SimpleCoffee
        System.out.println("========== 测试4：基础咖啡 + 香草 + 奶泡 ==========");
        Coffee vanillaWhipCoffee = new WhipDecorator(new VanillaDecorator(new SimpleCoffee()));
        System.out.println("订单: " + vanillaWhipCoffee.getDescription());
        System.out.println("价格: ¥" + vanillaWhipCoffee.cost());
        System.out.println();

        // 测试5：豪华版 - 所有配料
        // 展示装饰器的强大组合能力
        // 装饰器链：WhipDecorator -&gt; VanillaDecorator -&gt; SugarDecorator -&gt; MilkDecorator -&gt; SimpleCoffee
        System.out.println("========== 测试5：豪华版 - 所有配料 ==========");
        Coffee deluxeCoffee = new WhipDecorator(
            new VanillaDecorator(
                new SugarDecorator(
                    new MilkDecorator(
                        new SimpleCoffee()
                    )
                )
            )
        );
        System.out.println("订单: " + deluxeCoffee.getDescription());
        System.out.println("价格: ¥" + deluxeCoffee.cost());
        System.out.println();

        // 模式总结
        System.out.println("═══════════════════════════════════════════════════════════");
        System.out.println("  代码结构说明：");
        System.out.println("  1. SimpleCoffee是最基础的具体组件，价格是￥10.0");
        System.out.println("  2. MilkDecorator在原有价格上加了￥2.0，总价格￥12.0");
        System.out.println("  3. 多个装饰器可以嵌套使用，形成灵活的组合");
        System.out.println("  4. 每个装饰器都在原有基础上添加自己的功能和价格");
        System.out.println("═══════════════════════════════════════════════════════════");
    }
}

package com.design.mode.structural.flyweight;

/**
 * 享元模式测试类
 *
 * 享元模式（Flyweight Pattern）详解：
 *
 * 一、模式意图
 * 运用共享技术有效地支持大量细粒度的对象。通过共享相同状态的对象，减少内存使用。
 *
 * 二、模式结构
 * 1. 享元（Flyweight）: 定义享元对象的接口，这里是Character类
 * 2. 具体享元（ConcreteFlyweight）: 实现享元接口，存储内部状态（不变、可共享）
 * 3. 非共享具体享元（UnsharedConcreteFlyweight）: 不需要共享的子类（可选）
 * 4. 享元工厂（FlyweightFactory）: 创建和管理享元对象，这里是CharacterFactory
 * 5. 客户端（Client）: 维护外部状态，并在需要时调用享元工厂获取享元对象
 *
 * 三、适用场景
 * 1. 一个应用程序使用了大量的对象
 * 2. 由于使用大量对象，造成很大的存储开销
 * 3. 对象的大多数状态都可以变为外部状态
 * 4. 删除对象的外部状态后，可以用相对较少的共享对象替代大量对象
 *
 * 四、优缺点
 * 优点：
 * - 大幅度减少内存中对象的数量
 * - 外部状态相对独立，不会影响内部状态，使得享元对象可以在不同环境共享
 *
 * 缺点：
 * - 系统变得复杂，需要分离内部状态和外部状态
 * - 享元模式需要维护一个享元池，增加了系统复杂度
 * - 读取外部状态使得运行时间稍微变长
 */
public class FlyweightTest {

    /**
     * 主测试方法
     * 
     * 通过多个测试用例演示享元模式的工作原理和优势：
     * 1. 对比传统方式与享元模式的对象创建
     * 2. 展示享元对象的重用机制
     * 3. 演示外部状态的独立使用
     * 4. 测试不同内部状态的区分
     * 5. 模拟真实场景（文本编辑器）
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  享元模式测试                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 获取享元工厂单例实例
        CharacterFactory factory = CharacterFactory.getInstance();

        System.out.println("========== 测试1：创建字符对象（无享元模式） ==========");
        System.out.println("传统方式：每个字符都创建新对象");
        // 模拟不使用享元模式的情况，每次都创建新对象
        Character[] charsWithoutFlyweight = new Character[10];
        for (int i = 0; i < 10; i++) {
            // 每次都创建新的Character对象，即使字符和字体都相同
            charsWithoutFlyweight[i] = new Character('A', "Arial");
        }
        System.out.println("创建了 " + charsWithoutFlyweight.length + " 个独立的'A'字符对象\n");

        System.out.println("========== 测试2：使用享元模式创建字符对象 ==========");
        System.out.println("享元模式：共享相同字符的对象");

        // 多次请求相同的字符和字体，观察对象重用
        Character a1 = factory.getCharacter('A', "Arial");
        Character a2 = factory.getCharacter('A', "Arial");  // 应该重用a1
        Character a3 = factory.getCharacter('A', "Arial");  // 应该重用a1
        Character b1 = factory.getCharacter('B', "Arial");  // 创建新对象
        Character b2 = factory.getCharacter('B', "Arial");  // 应该重用b1
        Character c1 = factory.getCharacter('C', "Times New Roman");  // 创建新对象
        Character c2 = factory.getCharacter('C', "Times New Roman");  // 应该重用c1
        Character d1 = factory.getCharacter('D', "Times New Roman");  // 创建新对象

        System.out.println("\n字符池大小: " + factory.getPoolSize() + " 个共享对象");
        System.out.println("但我们可以使用这些对象显示多个字符实例\n");

        System.out.println("========== 测试3：使用共享对象显示字符 ==========");
        // 使用相同的享元对象，但传入不同的外部状态（位置、大小、颜色）
        // 这展示了外部状态如何独立于享元对象存在
        a1.display(10, 20, 12, "黑色");
        a2.display(30, 20, 12, "黑色");   // 同一个享元对象，不同位置
        a3.display(50, 20, 12, "黑色");   // 同一个享元对象，不同位置
        b1.display(10, 40, 14, "红色");
        b2.display(30, 40, 14, "蓝色");   // 同一个享元对象，不同颜色
        c1.display(10, 60, 16, "绿色");
        c2.display(30, 60, 16, "紫色");   // 同一个享元对象，不同颜色
        d1.display(50, 60, 18, "橙色");

        System.out.println("\n========== 测试4：测试不同字体的相同字符 ==========");
        // 测试：相同字符但不同字体，应该创建不同的享元对象
        // 因为字体也是内部状态的一部分
        Character aArial = factory.getCharacter('A', "Arial");
        Character aTimes = factory.getCharacter('A', "Times New Roman");  // 新对象
        Character aCourier = factory.getCharacter('A', "Courier New");    // 新对象
        Character aArial2 = factory.getCharacter('A', "Arial");           // 重用aArial

        System.out.println("\n字符池大小: " + factory.getPoolSize() + " 个共享对象");
        System.out.println("'A'字符有3种字体，所以有3个不同的享元对象");
        System.out.println("相同字体的'A'字符会重用同一个对象\n");

        System.out.println("========== 测试5：模拟文本编辑器场景 ==========");
        System.out.println("模拟一个包含多个字符的文档...");

        // 清空之前的池，重新开始测试
        factory.clearPool();

        // 模拟文档内容
        char[] document = "DesignPatternsAreAwesome".toCharArray();
        // 可用字体列表
        String[] fonts = {"Arial", "Times New Roman", "Courier New"};
        // 显示位置初始值
        int x = 0, y = 0;

        System.out.println("\n文档内容: " + new String(document));
        System.out.println("处理文档中的每个字符...\n");

        // 遍历文档中的每个字符
        for (int i = 0; i < document.length; i++) {
            char ch = document[i];
            // 轮流使用不同字体
            String font = fonts[i % fonts.length];
            // 不同大小（10-14）
            int fontSize = 10 + (i % 5);
            // 交替颜色
            String color = i % 2 == 0 ? "黑色" : "蓝色";

            // 从工厂获取享元对象
            Character chObj = factory.getCharacter(ch, font);

            // 使用外部状态显示字符
            chObj.display(x, y, fontSize, color);
            x += 20; // 移动到下一个字符位置
        }

        // 统计享元模式的效果
        System.out.println("\n文档长度: " + document.length + " 个字符");
        System.out.println("实际创建的享元对象数量: " + factory.getPoolSize());
        System.out.println("内存节省: " + (document.length - factory.getPoolSize()) + " 个对象");

        // 模式总结
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  模式总结：                                                  ║");
        System.out.println("║  1. Character是享元类，内部状态是charCode和fontFamily         ║");
        System.out.println("║  2. CharacterFactory是享元工厂，管理共享的字符对象           ║");
        System.out.println("║  3. 外部状态（位置、大小、颜色）通过display方法参数传递       ║");
        System.out.println("║  4. 相同字符和字体的对象被共享，减少了内存使用               ║");
        System.out.println("║  5. 享元模式在需要大量相似对象的场景中特别有效               ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
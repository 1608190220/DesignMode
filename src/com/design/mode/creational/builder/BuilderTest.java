package com.design.mode.creational.builder;

import com.design.mode.creational.builder.product.Computer;

/**
 * 建造者模式测试类
 *
 * 本测试类用于演示 Builder Pattern 在“复杂对象构建”场景中的典型用法。
 * 通过构建办公机、游戏机、入门机三种配置，体现以下设计价值：
 * 1. 将“构建步骤”与“最终对象表示”解耦
 * 2. 通过链式调用提升可读性，减少重载构造器带来的参数混乱
 * 3. 用必选参数 + 可选参数组合，保证对象完整性与灵活性
 *
 * 说明：
 * - 本示例中的 Computer.Builder 采用静态内部类实现
 * - 必选项在 Builder 构造方法中传入，可选项通过 setXxx 逐步补充
 * - 最终通过 build() 触发校验并生成不可分步暴露的完整对象
 */
public class BuilderTest {

    /**
     * 建造者模式演示入口
     *
     * 演示流程：
     * 1. 构建办公电脑：偏向稳定与性价比
     * 2. 构建游戏电脑：增加显卡与散热等高性能组件
     * 3. 构建入门电脑：仅保留必选配置
     * 4. 展示链式调用风格与传统构造方式对比
     *
     * @param args 命令行参数（示例中未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                     建造者模式测试                        ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        System.out.println("========== 构建配置1：办公电脑 ==========");
        System.out.println("需求：适用于日常办公，无需独立显卡\n");

        // 先传入必选参数，再按需补充可选参数，最后 build 生成对象
        Computer officeComputer = new Computer.Builder(
                "Intel Core i5-12400",
                "16GB DDR4 3200MHz",
                "512GB NVMe SSD",
                "华硕 B660M 主板",
                "450W 80Plus 电源",
                "普通机箱"
        )
                .setWiFi(true)
                .setBluetooth(true)
                .build();

        officeComputer.showConfiguration();
        System.out.println();

        System.out.println("========== 构建配置2：游戏电脑 ==========");
        System.out.println("需求：高性能游戏配置，需要独立显卡和散热系统\n");

        // 游戏场景可在必选硬件外，附加显卡、散热等扩展项
        Computer gamingComputer = new Computer.Builder(
                "AMD Ryzen 7 5800X",
                "32GB DDR4 3600MHz",
                "1TB NVMe SSD + 2TB HDD",
                "微星 B550 电竞主板",
                "750W 金牌全模组电源",
                "RGB 游戏机箱"
        )
                .setGraphicsCard("NVIDIA RTX 3070 Ti 8GB")
                .setCoolingSystem("240mm 一体式水冷")
                .setWiFi(true)
                .setBluetooth(true)
                .build();

        gamingComputer.showConfiguration();
        System.out.println();

        System.out.println("========== 构建配置3：入门级电脑 ==========");
        System.out.println("需求：基础配置，满足基本使用\n");

        // 入门配置仅保留必选参数，不设置可选项也可安全构建
        Computer basicComputer = new Computer.Builder(
                "Intel Pentium Gold G6400",
                "4GB DDR4 2666MHz",
                "128GB SSD",
                "技嘉 H410M 主板",
                "300W 电源",
                "普通机箱"
        )
                .build();

        basicComputer.showConfiguration();
        System.out.println();

        System.out.println("========== 构建配置演示：链式调用 ==========");
        System.out.println("通过链式调用可以保证代码的可读性和使用体验\n");

        // 链式调用示例：每次 setXxx 返回 Builder 自身，便于连续配置
        new Computer.Builder("Intel Core i7-12700K", "16GB", "1TB SSD",
                        "Z690 主板", "650W 电源", "中塔机箱")
                .setGraphicsCard("RTX 3060");

        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│ 建造者模式的优点：                              │");
        System.out.println("│                                                 │");
        System.out.println("│ 1. 封装性好：将创建逻辑封装在 Builder 中       │");
        System.out.println("│ 2. 代码清晰：通过链式调用提高代码可读性         │");
        System.out.println("│ 3. 灵活性高：可以逐步构造对象，支持可选参数     │");
        System.out.println("│ 4. 好的封装：封装了对象的复杂创建过程           │");
        System.out.println("│ 5. 类型安全：每个步骤结束时返回 Builder 对象    │");
        System.out.println("│                                                 │");
        System.out.println("│ 适用场景：                                       │");
        System.out.println("│ 1. 创建复杂对象，参数很多且有可选参数            │");
        System.out.println("│ 2. 对象的创建过程独立于其表示                    │");
        System.out.println("│ 3. 需要创建不同配置的对象                        │");
        System.out.println("└─────────────────────────────────────────────────┘");

        System.out.println("\n========== 对比传统构造方式 ==========");
        System.out.println("传统方式：多个构造函数或很多参数\n");
        System.out.println("// 方式1：多个重载构造函数");
        System.out.println("Computer c1 = new Computer(CPU, Memory, Storage);");
        System.out.println("Computer c2 = new Computer(CPU, Memory, Storage, GraphicsCard);");
        System.out.println("Computer c3 = new Computer(CPU, Memory, Storage, GraphicsCard, WiFi);");
        System.out.println("// 问题：构造参数多时容易混淆，可选参数难以处理\n");

        System.out.println("// 方式2：JavaBean模式");
        System.out.println("Computer c = new Computer();");
        System.out.println("c.setCpu(CPU);");
        System.out.println("c.setMemory(Memory);");
        System.out.println("c.setStorage(Storage);");
        System.out.println("// 问题：对象状态不确定，可能创建出不完整的对象\n");

        System.out.println("// 建造者模式：");
        System.out.println("Computer c = new Computer.Builder(必选项...)");
        System.out.println("    .setXXX(可选值)");
        System.out.println("    .build();");
        System.out.println("// 优点：代码清晰，类型安全，确保对象的完整性\n");

        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║         建造者模式测试完成                                ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }
}

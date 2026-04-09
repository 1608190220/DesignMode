package com.design.mode.structural.facade;

/**
 * 外观模式测试类
 *
 * 外观模式（Facade Pattern）详解：
 *
 * 一、模式意图
 * 为子系统中的一组接口提供一个统一的接口。外观模式定义了一个高层接口，这个接口使得这一子系统更加容易使用。
 *
 * 二、模式结构
 * 1. 外观（Facade）: 提供一个统一的高层接口，这里是HomeTheaterFacade
 * 2. 子系统（Subsystem）: 实现子系统的功能，这里是Projector、SoundSystem、Lighting、DVDPlayer
 * 3. 客户端（Client）: 通过外观接口与子系统交互
 *
 * 三、适用场景
 * 1. 为复杂的子系统提供一个简单的接口
 * 2. 客户端与抽象类的实现部分之间存在很大的依赖性
 * 3. 需要构建一个层次结构的子系统，使用外观模式定义每层的入口点
 *
 * 四、优缺点
 * 优点：
 * - 减少系统相互依赖，提高子系统的独立性和可移植性
 * - 提高安全性，将客户端与复杂的子系统隔离
 * - 符合迪米特法则（最少知道原则）
 *
 * 缺点：
 * - 不能很好地限制客户端直接使用子系统类
 * - 增加新的子系统可能需要修改外观类，违背开闭原则
 */
public class FacadeTest {

    /**
     * 主测试方法
     * 
     * 通过多个测试用例演示外观模式的优势：
     * 1. 对比不使用外观模式的复杂操作
     * 2. 展示使用外观模式的简化操作
     * 3. 测试外观提供的各种场景模式
     * 
     * 测试用例设计：
     * - 测试1：手动操作所有子系统（复杂方式）
     * - 测试2：使用外观一键操作（简单方式）
     * - 测试3-5：测试外观提供的不同场景模式
     * - 测试6：对比手动关闭设备的复杂性
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  外观模式测试                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 创建所有子系统对象
        // 在实际应用中，这些对象可能通过依赖注入或工厂模式创建
        Projector projector = new Projector();
        SoundSystem soundSystem = new SoundSystem();
        Lighting lighting = new Lighting();
        DVDPlayer dvdPlayer = new DVDPlayer();

        // 创建外观对象，将所有子系统组合在一起
        HomeTheaterFacade homeTheater = new HomeTheaterFacade(
                projector, soundSystem, lighting, dvdPlayer);

        System.out.println("========== 测试1：复杂方式操作子系统（没有外观） ==========");
        System.out.println("准备观看电影（手动操作所有设备）：");
        // 不使用外观模式，需要手动调用每个子系统的方法
        // 客户端需要知道：
        // 1. 需要操作哪些设备
        // 2. 操作的顺序
        // 3. 每个设备的具体参数
        lighting.dim(10);
        projector.on();
        projector.wideScreenMode();
        soundSystem.on();
        soundSystem.setSurroundSound();
        soundSystem.setVolume(8);
        dvdPlayer.on();
        dvdPlayer.play("阿凡达");
        System.out.println();

        System.out.println("========== 测试2：简单方式操作（使用外观模式） ==========");
        System.out.println("准备观看电影（通过外观一键操作）：");
        // 使用外观模式，只需调用一个方法
        // 客户端无需了解子系统的内部细节
        homeTheater.watchMovie("流浪地球");

        System.out.println("========== 测试3：结束电影 ==========");
        // 使用外观一键结束观影
        homeTheater.endMovie();

        System.out.println("========== 测试4：听音乐模式 ==========");
        // 测试外观提供的其他场景模式
        homeTheater.listenToMusic("月光奏鸣曲");

        System.out.println("========== 测试5：电视模式 ==========");
        // 测试电视模式
        homeTheater.watchTV();

        System.out.println("========== 测试6：传统方式结束（没有外观） ==========");
        System.out.println("手动关闭所有设备：");
        // 再次对比不使用外观模式的复杂性
        dvdPlayer.stop();
        dvdPlayer.eject();
        dvdPlayer.off();
        projector.off();
        soundSystem.off();
        lighting.on();

        // 模式总结
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  模式总结：                                                  ║");
        System.out.println("║  1. Projector、SoundSystem、Lighting、DVDPlayer是子系统       ║");
        System.out.println("║  2. HomeTheaterFacade是外观类，提供统一的高级接口             ║");
        System.out.println("║  3. 客户端只需与外观类交互，无需了解子系统内部复杂逻辑         ║");
        System.out.println("║  4. 外观模式降低了系统的耦合度，提高了易用性                  ║");
        System.out.println("║  5. 子系统可以独立变化，只要外观接口不变，客户端代码不变       ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}
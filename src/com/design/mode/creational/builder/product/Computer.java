package com.design.mode.creational.builder.product;

/**
 * 复杂产品类：计算机
 *
 * 建造者模式（Builder Pattern）的定义：
 * 1. 将一个复杂对象的构建与它的表示分离
 * 2. 使得同样的构建过程可以创建不同的表示
 * 3. 主要用于创建一些复杂的对象，这些对象内部构建间的建造顺序通常是稳定的
 *
 * 适用场景：
 * 1. 当创建一个复杂对象的算法应该独立于该对象的组成部分以及它们的装配方式时
 * 2. 当构建过程必须允许被构造的对象有不同表示时
 * 3. 当对象的创建过程相当复杂，需要很多参数和步骤时
 *
 * 本实现的设计说明：
 * - Computer 只负责表达“最终产品状态”，不暴露复杂构建细节
 * - Builder 负责参数收集、校验、装配，避免客户端直接调用超长构造函数
 * - 必选参数通过 Builder 构造器约束，可选参数通过链式方法按需设置
 */
public class Computer {

    private String cpu;           // CPU
    private String memory;        // 内存
    private String storage;       // 存储
    private String graphicsCard;  // 显卡（可选）
    private String motherboard;   // 主板
    private String powerSupply;   // 电源
    private String chassis;       // 机箱
    private String coolingSystem; // 散热系统（可选）
    private boolean hasWiFi;      // 是否包含WiFi模块（可选）
    private boolean hasBluetooth; // 是否包含蓝牙模块（可选）

    /**
     * 私有化构造函数，防止直接创建对象
     * 通过建造者来创建对象
     *
     * 设计意图：
     * 1. 强制客户端走 Builder 流程，确保对象创建路径统一
     * 2. 避免出现“必选参数遗漏”导致的半初始化对象
     */
    private Computer(Builder builder) {
        this.cpu = builder.cpu;
        this.memory = builder.memory;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
        this.motherboard = builder.motherboard;
        this.powerSupply = builder.powerSupply;
        this.chassis = builder.chassis;
        this.coolingSystem = builder.coolingSystem;
        this.hasWiFi = builder.hasWiFi;
        this.hasBluetooth = builder.hasBluetooth;
    }

    /**
     * 展示计算机配置
     *
     * 该方法用于示例展示，生产环境中可替换为：
     * - toString() 序列化输出
     * - DTO 转换
     * - UI 渲染层展示
     */
    public void showConfiguration() {
        System.out.println("╔═════════════════════════════ 计算机配置 ═══════════════════════════════╗");
        System.out.println("║                                                                          ║");
        System.out.printf("║  [CPU]           : %-52s   ║\n", cpu);
        System.out.printf("║  [内存]          : %-52s   ║\n", memory);
        System.out.printf("║  [存储]          : %-52s   ║\n", storage);
        System.out.printf("║  [主板]          : %-52s   ║\n", motherboard);
        System.out.printf("║  [电源]          : %-52s   ║\n", powerSupply);
        System.out.printf("║  [机箱]          : %-52s   ║\n", chassis);

        if (graphicsCard != null && !graphicsCard.isEmpty()) {
            System.out.printf("║  [显卡]          : %-52s   ║\n", graphicsCard);
        }
        if (coolingSystem != null && !coolingSystem.isEmpty()) {
            System.out.printf("║  [散热系统]      : %-52s   ║\n", coolingSystem);
        }

        System.out.println("║                                                                          ║");
        System.out.printf("║  [WiFi模块]      : %-52s   ║\n", hasWiFi ? "包含" : "不包含");
        System.out.printf("║  [蓝牙模块]      : %-52s   ║\n", hasBluetooth ? "包含" : "不包含");
        System.out.println("║                                                                          ║");
        System.out.println("╚═════════════════════════════════════════════════════════════════════════╝");
    }

    // Getters
    public String getCpu() {
        return cpu;
    }

    public String getMemory() {
        return memory;
    }

    public String getStorage() {
        return storage;
    }

    public String getGraphicsCard() {
        return graphicsCard;
    }

    public String getMotherboard() {
        return motherboard;
    }

    public String getPowerSupply() {
        return powerSupply;
    }

    public String getChassis() {
        return chassis;
    }

    public String getCoolingSystem() {
        return coolingSystem;
    }

    public boolean hasWiFi() {
        return hasWiFi;
    }

    public boolean hasBluetooth() {
        return hasBluetooth;
    }

    /**
     * 静态内部类：建造者
     *
     * 建造者封装了产品的构建逻辑，通过链式调用设置参数，
     * 最后调用 build() 方法创建最终产品。
     *
     * 为什么使用静态内部类：
     * 1. 与外部产品类语义强绑定，便于理解与使用
     * 2. 不依赖外部类实例即可独立完成构建
     * 3. 可直接访问构建所需字段，减少样板代码
     */
    public static class Builder {
        // 必选的组件
        private String cpu;           // CPU - 必须
        private String memory;        // 内存 - 必须
        private String storage;       // 存储 - 必须
        private String motherboard;   // 主板 - 必须
        private String powerSupply;   // 电源 - 必须
        private String chassis;       // 机箱 - 必须

        // 可选的组件
        private String graphicsCard = "";   // 显卡 - 可选
        private String coolingSystem = "";  // 散热系统 - 可选
        private boolean hasWiFi = false;    // WiFi模块 - 可选，默认不包含
        private boolean hasBluetooth = false; // 蓝牙模块 - 可选，默认不包含

        /**
         * 构造函数，设置必选参数
         *
         * 这些参数是“创建一台可用计算机”的最小集合，
         * 在构建时必须提供，用于从源头保证对象完整性。
         *
         * @param cpu        CPU
         * @param memory     内存
         * @param storage    存储
         * @param motherboard 主板
         * @param powerSupply 电源
         * @param chassis    机箱
         */
        public Builder(String cpu, String memory, String storage,
                       String motherboard, String powerSupply, String chassis) {
            this.cpu = cpu;
            this.memory = memory;
            this.storage = storage;
            this.motherboard = motherboard;
            this.powerSupply = powerSupply;
            this.chassis = chassis;
        }

        /**
         * 设置显卡
         *
         * 可选参数，未设置时默认视为不带独立显卡。
         *
         * @param graphicsCard 显卡型号
         * @return Builder对象本身，支持链式调用
         */
        public Builder setGraphicsCard(String graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        /**
         * 设置散热系统
         *
         * 可选参数，适用于高性能或高负载场景。
         *
         * @param coolingSystem 散热系统
         * @return Builder对象本身，支持链式调用
         */
        public Builder setCoolingSystem(String coolingSystem) {
            this.coolingSystem = coolingSystem;
            return this;
        }

        /**
         * 设置是否包含WiFi模块
         *
         * 可选参数，默认为 false。
         *
         * @param hasWiFi 是否包含WiFi
         * @return Builder对象本身，支持链式调用
         */
        public Builder setWiFi(boolean hasWiFi) {
            this.hasWiFi = hasWiFi;
            return this;
        }

        /**
         * 设置是否包含蓝牙模块
         *
         * 可选参数，默认为 false。
         *
         * @param hasBluetooth 是否包含蓝牙
         * @return Builder对象本身，支持链式调用
         */
        public Builder setBluetooth(boolean hasBluetooth) {
            this.hasBluetooth = hasBluetooth;
            return this;
        }

        /**
         * 构建最终的 Computer 对象
         * 完成所有参数设置后，调用此方法创建最终产品
         *
         * 执行顺序：
         * 1. 调用 validate() 完成关键字段校验
         * 2. 通过私有构造器组装 Computer 实例
         * 3. 返回构建完成的产品对象
         *
         * @return Computer对象
         */
        public Computer build() {
            // 参数校验
            validate();
            return new Computer(this);
        }

        /**
         * 校验参数合法性
         * 可以在这里添加更多的验证逻辑
         *
         * 可扩展校验示例：
         * - 电源功率与显卡型号的匹配关系
         * - 主板芯片组与CPU代际兼容性
         * - 内存规格与主板支持范围校验
         */
        private void validate() {
            if (cpu == null || cpu.trim().isEmpty()) {
                throw new IllegalArgumentException("CPU不能为空");
            }
            if (memory == null || memory.trim().isEmpty()) {
                throw new IllegalArgumentException("内存不能为空");
            }
            if (storage == null || storage.trim().isEmpty()) {
                throw new IllegalArgumentException("存储不能为空");
            }
            if (motherboard == null || motherboard.trim().isEmpty()) {
                throw new IllegalArgumentException("主板不能为空");
            }
            if (powerSupply == null || powerSupply.trim().isEmpty()) {
                throw new IllegalArgumentException("电源不能为空");
            }
            if (chassis == null || chassis.trim().isEmpty()) {
                throw new IllegalArgumentException("机箱不能为空");
            }
        }
    }
}

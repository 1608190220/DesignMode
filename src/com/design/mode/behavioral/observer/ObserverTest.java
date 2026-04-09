package com.design.mode.behavioral.observer;

/**
 * 观察者模式测试类
 * 
 * 演示观察者模式的使用和观察者的反应。
 * 通过多次设置温度来观察观察者的不同反应。
 * 
 * 测试目标：
 * 1. 验证观察者模式的基本工作原理
 * 2. 展示观察者如何对主题变化做出反应
 * 3. 验证观察者的动态添加和移除
 * 4. 展示不同观察者的不同行为
 * 5. 验证只有当状态真正变化时才通知观察者
 * 
 * 测试场景：
 * 1. 添加两个观察者（TemperatureDisplay 和 TemperatureAlert）
 * 2. 设置温度为 25°C（正常范围）
 * 3. 设置温度为 35°C（超过阈值，触发警报）
 * 4. 移除 TemperatureDisplay 观察者
 * 5. 设置温度为 -5°C（低于零度，触发警报）
 * 6. 设置温度为相同值（不应通知）
 */
public class ObserverTest {
    
    /**
     * 程序入口
     * 
     * 通过多个测试场景演示观察者模式的工作原理：
     * 1. 添加两个观察者并观察它们的反应
     * 2. 设置不同的温度值，观察不同观察者的不同行为
     * 3. 移除一个观察者，观察只有剩余的观察者收到通知
     * 4. 设置相同的温度值，验证不发送通知的优化
     * 
     * 这展示了观察者模式的核心优势：
     * - 主题和观察者之间是抽象耦合的
     * - 支持广播通信
     * - 符合开闭原则，新增观察者简单
     * - 支持动态添加和移除观察者
     * 
     * @param args 命令行参数（本示例不使用）
     */
    public static void main(String[] args) {
        System.out.println("===== 观察者模式演示 =====");
        
        // 创建气象站（主题）
        WeatherStation weatherStation = new WeatherStation();
        
        // 创建观察者
        TemperatureDisplay temperatureDisplay = new TemperatureDisplay();
        TemperatureAlert temperatureAlert = new TemperatureAlert();
        
        System.out.println("\n1. 向气象站添加观察者：");
        // 向气象站添加观察者
        weatherStation.attach(temperatureDisplay);
        weatherStation.attach(temperatureAlert);
        
        System.out.println("\n2. 将温度更改为25°C：");
        // 更改温度 - 应通知所有观察者
        weatherStation.setTemperature(25.0f);
        
        System.out.println("\n3. 将温度更改为35°C（超过警报阈值）：");
        // 更改温度为高值 - 应触发警报
        weatherStation.setTemperature(35.0f);
        
        System.out.println("\n4. 移除TemperatureDisplay观察者：");
        // 移除一个观察者
        weatherStation.detach(temperatureDisplay);
        
        System.out.println("\n5. 将温度更改为-5°C（低于零度）：");
        // 更改温度为低值 - 应仅通知剩余的观察者
        weatherStation.setTemperature(-5.0f);
        
        System.out.println("\n6. 将温度更改为相同值（不应通知）：");
        // 更改温度为相同值 - 不应通知观察者
        weatherStation.setTemperature(-5.0f);
        
        System.out.println("\n===== 观察者模式演示结束 =====");
    }
}

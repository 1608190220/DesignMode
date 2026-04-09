package com.design.mode.behavioral.observer;

/**
 * 具体观察者类（Concrete Observer）
 * 
 * 观察者模式中的具体观察者角色，实现 Observer 接口。
 * 在屏幕上显示温度变化。
 * 
 * 具体观察者角色的职责：
 * - 实现 Observer 接口
 * - 保存对主题的引用（可选）
 * - 在 update() 方法中实现对主题变化的反应
 * - 可以通过主题引用获取更多信息
 * 
 * 温度显示器特点：
 * - 这是一个具体的观察者实现
 * - 用于在屏幕上显示温度
 * - 既使用推模型（message）也使用拉模型（getTemperature()）
 * - 展示了观察者如何对主题变化做出反应
 */
public class TemperatureDisplay implements Observer {
    
    /**
     * 当主题状态发生变化时调用的更新方法
     * 
     * 这是观察者模式的核心方法，当主题状态变化时，主题会调用这个方法。
     * 
     * 实现说明：
     * - 既使用推模型（通过 message 参数获取信息）
     * - 也使用拉模型（通过 subject.getTemperature() 获取更多信息）
     * - 展示了观察者如何对主题变化做出反应
     * 
     * 推模型 vs 拉模型结合使用：
     * - message 提供基本信息
     * - subject 提供更多详细信息
     * - 两者结合使用，既方便又灵活
     * 
     * @param subject 发生变化的主题，观察者可以通过它获取更多信息
     * @param message 来自主题的消息，推模型的一部分
     */
    @Override
    public void update(Subject subject, Object message) {
        if (subject instanceof WeatherStation) {
            WeatherStation weatherStation = (WeatherStation) subject;
            System.out.println("[Temperature Display] " + message);
            System.out.println("[Temperature Display] Current temperature: " + weatherStation.getTemperature() + "°C");
            System.out.println("[Temperature Display] Display updated with new temperature");
        }
    }
}

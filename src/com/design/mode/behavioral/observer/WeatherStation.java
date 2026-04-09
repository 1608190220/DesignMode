package com.design.mode.behavioral.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * 具体主题类（Concrete Subject）
 * 
 * 观察者模式中的具体主题角色，实现 Subject 接口并维护观察者列表。
 * 表示一个监测温度变化的气象站。
 * 
 * 具体主题角色的职责：
 * - 实现 Subject 接口
 * - 维护观察者列表
 * - 管理状态（temperature）
 * - 当状态变化时通知所有观察者
 * 
 * 气象站特点：
 * - 这是一个具体的主题实现
 * - 监测温度变化
 * - 当温度变化时通知所有观察者
 * - 支持运行时动态添加和移除观察者
 * - 只有当温度真正变化时才通知观察者（优化）
 */
public class WeatherStation implements Subject {
    
    /**
     * 观察者列表
     * 
     * 使用 List 存储观察者，这是观察者模式的关键设计。
     * 
     * 设计要点：
     * - 使用 List 来维护观察者
     * - 支持动态添加和移除观察者
     * - 通知时遍历列表，逐个调用 update()
     */
    private List<Observer> observers;
    
    /**
     * 当前温度
     * 
     * 这是主题的状态，当这个状态变化时，主题会通知所有观察者。
     */
    private float temperature;
    
    /**
     * 构造方法
     * 
     * 初始化观察者列表和初始温度。
     */
    public WeatherStation() {
        observers = new ArrayList<>();
        temperature = 0.0f;
    }
    
    /**
     * 向列表添加一个观察者
     * 
     * 这是观察者注册的方法，观察者通过调用这个方法来订阅主题。
     * 
     * 实现说明：
     * - 将观察者添加到 List 中
     * - 打印日志以便调试
     * 
     * @param observer 要添加的观察者
     */
    @Override
    public void attach(Observer observer) {
        observers.add(observer);
        System.out.println("Observer added: " + observer.getClass().getSimpleName());
    }
    
    /**
     * 从列表中移除一个观察者
     * 
     * 这是观察者取消注册的方法，观察者通过调用这个方法来取消订阅主题。
     * 
     * 实现说明：
     * - 将观察者从 List 中移除
     * - 打印日志以便调试
     * 
     * @param observer 要移除的观察者
     */
    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
        System.out.println("Observer removed: " + observer.getClass().getSimpleName());
    }
    
    /**
     * 通知所有观察者状态变化
     * 
     * 这是观察者模式的核心方法，当主题状态变化时调用。
     * 
     * 实现说明：
     * - 遍历观察者列表
     * - 逐个调用观察者的 update() 方法
     * - 使用推模型，传递主题引用和消息
     */
    @Override
    public void notifyObservers() {
        System.out.println("Notifying all observers about temperature change: " + temperature + "°C");
        for (Observer observer : observers) {
            observer.update(this, "Temperature changed to: " + temperature + "°C");
        }
    }
    
    /**
     * 设置温度并通知观察者
     * 
     * 这是主题的业务方法，用于设置温度并在温度变化时通知观察者。
     * 
     * 优化说明：
     * - 只有当温度真正变化时才通知观察者
     * - 避免不必要的通知
     * 
     * @param temperature 新的温度值
     */
    public void setTemperature(float temperature) {
        if (this.temperature != temperature) {
            this.temperature = temperature;
            notifyObservers();
        }
    }
    
    /**
     * 获取当前温度
     * 
     * 允许观察者拉取温度信息（拉模型）。
     * 
     * @return 当前温度
     */
    public float getTemperature() {
        return temperature;
    }
}

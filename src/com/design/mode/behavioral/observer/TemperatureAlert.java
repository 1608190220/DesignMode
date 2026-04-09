package com.design.mode.behavioral.observer;

/**
 * 具体观察者类（Concrete Observer）
 * 
 * 观察者模式中的具体观察者角色，实现 Observer 接口。
 * 当温度超过特定阈值时发送警报。
 * 
 * 具体观察者角色的职责：
 * - 实现 Observer 接口
 * - 保存对主题的引用（可选）
 * - 在 update() 方法中实现对主题变化的反应
 * - 可以根据主题状态做出不同的反应
 * 
 * 温度警报器特点：
 * - 这是一个具体的观察者实现
 * - 用于监测温度是否超过阈值
 * - 当温度过高或过低时发送警报
 * - 展示了观察者如何根据主题状态做出不同的反应
 */
public class TemperatureAlert implements Observer {
    
    /**
     * 警报的温度阈值
     * 
     * 当温度超过这个值时，会触发高温警报。
     * 使用 static final 修饰，确保不可变且全局共享。
     */
    private static final float ALERT_THRESHOLD = 30.0f;
    
    /**
     * 当主题状态发生变化时调用的更新方法
     * 
     * 这是观察者模式的核心方法，当主题状态变化时，主题会调用这个方法。
     * 
     * 实现说明：
     * - 获取主题的温度信息（拉模型）
     * - 根据温度值做出不同的反应
     * - 展示了观察者如何根据主题状态做出条件判断
     * 
     * 警报逻辑：
     * - 温度 &gt; 30°C：触发高温警报
     * - 温度 &lt; 0°C：触发低温警报
     * - 其他：温度正常
     * 
     * @param subject 发生变化的主题，观察者可以通过它获取更多信息
     * @param message 来自主题的消息
     */
    @Override
    public void update(Subject subject, Object message) {
        if (subject instanceof WeatherStation) {
            WeatherStation weatherStation = (WeatherStation) subject;
            float temperature = weatherStation.getTemperature();
            
            System.out.println("[Temperature Alert] " + message);
            
            // Check if temperature exceeds threshold
            if (temperature > ALERT_THRESHOLD) {
                System.out.println("[Temperature Alert] ALERT: Temperature too high! " + temperature + "°C");
                System.out.println("[Temperature Alert] Sending high temperature alert...");
            } else if (temperature < 0.0f) {
                System.out.println("[Temperature Alert] ALERT: Temperature too low! " + temperature + "°C");
                System.out.println("[Temperature Alert] Sending low temperature alert...");
            } else {
                System.out.println("[Temperature Alert] Temperature is within normal range");
            }
        }
    }
}

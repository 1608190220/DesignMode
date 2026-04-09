package com.design.mode.creational.singleton;

import java.io.Serializable;

/**
 * 单例模式 - 懒汉式（线程安全，同步方法）
 *
 * 特点：
 * 1. 解决了线程安全问题
 * 2. 效率太低：每个线程在获取实例时都需要进行同步
 * 3. 虽然线程安全，但是性能差
 * 4. 不推荐频繁获取实例的场景下使用
 */
public class SingletonLazySafe implements Serializable {

    private static final long serialVersionUID = 1L;

    private static SingletonLazySafe instance;

    private SingletonLazySafe() {}

    /**
     * 使用 synchronized 关键字同步方法，确保线程安全
     * 但这种方法会导致性能下降
     * 
     * 工作原理：
     * - synchronized 关键字确保同一时间只有一个线程能进入这个方法
     * - 从而避免了多个线程同时创建实例的问题
     * 
     * 优点：
     * - 线程安全：确保多线程环境下只有一个实例
     * - 懒加载：只有第一次使用时才创建实例
     * 
     * 缺点：
     * - 性能差：每次调用 getInstance() 都要进行同步
     * - 效率低：即使实例已经创建，仍然需要同步
     * 
     * @return 单例实例
     */
    public static synchronized SingletonLazySafe getInstance() {
        if (instance == null) {
            instance = new SingletonLazySafe();
        }
        return instance;
    }

    /**
     * 示例业务方法
     */
    public void doSomething() {
        System.out.println("懒汉式（线程安全-同步方法）单例正在执行任务...");
    }

    /**
     * 防止序列化破坏单例模式
     * 当对象被反序列化时，会调用此方法返回单例实例
     * 
     * @return 单例实例
     */
    private Object readResolve() {
        return getInstance();
    }
}

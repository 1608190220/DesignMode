package com.design.mode.creational.singleton;

import java.io.Serializable;

/**
 * 单例模式 - 懒汉式（线程不安全）
 *
 * 特点：
 * 1. 起到了懒加载效果，但只能在单线程下使用
 * 2. 在多线程环境下，如果多个线程同时进入 if (instance == null) 判断，
 *    会创建多个实例，违反单例原则
 * 3. 实际开发中不推荐使用这种方式
 */
public class SingletonLazy implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 私有静态变量，初始值为 null，不立即创建实例
     * 
     * 懒汉式的核心：延迟初始化，只有在第一次使用时才创建实例。
     * 
     * 优点：
     * - 懒加载：节省内存，如果从未使用则不创建实例
     * - 启动快：类加载时不创建实例
     * 
     * 缺点：
     * - 线程不安全：多线程环境下可能创建多个实例
     * - 不推荐在生产环境使用
     */
    private static SingletonLazy instance;

    /**
     * 私有化构造函数
     */
    private SingletonLazy() {}

    /**
     * 对外提供获取实例的方法
     * 只有当第一次调用时才创建实例
     * 
     * 线程安全问题分析：
     * 如果两个线程同时进入 if (instance == null) 判断，
     * 且都判断为 true，那么两个线程都会创建实例，
     * 从而违反单例原则。
     *
     * @return 单例实例
     */
    public static SingletonLazy getInstance() {
        // 如果实例不存在，则创建一个新的实例
        if (instance == null) {
            instance = new SingletonLazy();
        }
        return instance;
    }

    /**
     * 示例业务方法
     */
    public void doSomething() {
        System.out.println("懒汉式（线程不安全）单例正在执行任务...");
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

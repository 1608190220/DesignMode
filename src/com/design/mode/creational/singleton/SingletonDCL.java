package com.design.mode.creational.singleton;

import java.io.Serializable;

/**
 * 单例模式 - 双重检查锁（Double-Checked Locking，DCL）
 *
 * 特点：
 * 1. 延迟加载，保证线程安全，性能高
 * 2. 进行了两次 if (instance == null) 检查，保证了线程安全
 * 3. 实例化代码只执行一次，后续的访问直接返回实例
 * 4. 推荐使用，是常用的实现方式
 */
public class SingletonDCL implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * volatile 关键字的作用：
     * 1. 保证了可见性：当一个线程修改了 volatile 变量的值，新值对其他线程立即可见
     * 2. 禁止指令重排序：防止 JVM 优化导致 instance 被提前访问
     */
    private static volatile SingletonDCL instance;

    private SingletonDCL() {}

    /**
     * 双重检查锁机制：
     * 第一次检查：不加锁，提高性能，避免每次调用都加锁
     * 第二次检查：加锁后检查，确保线程安全
     * 
     * 为什么需要两次检查：
     * 1. 第一次检查（不加锁）：快速判断，提高性能
     * 2. 第二次检查（加锁后）：确保只有一个线程创建实例
     * 
     * 指令重排序问题：
     * instance = new SingletonDCL() 可以分为三步：
     * 1. 分配内存空间
     * 2. 初始化对象
     * 3. 将 instance 指向分配的内存地址
     * 
     * JVM 可能会进行指令重排序，变成 1-3-2，
     * 导致其他线程看到 instance 不为 null 但对象还未初始化。
     * 
     * volatile 关键字的作用：
     * - 禁止指令重排序
     * - 保证可见性
     * 
     * @return 单例实例
     */
    public static SingletonDCL getInstance() {
        // 第一次检查，不加锁，如果已经创建直接返回
        if (instance == null) {
            // 只有未创建时才加锁
            synchronized (SingletonDCL.class) {
                // 第二次检查，确保只有一个线程创建实例
                if (instance == null) {
                    instance = new SingletonDCL();
                }
            }
        }
        return instance;
    }

    /**
     * 示例业务方法
     */
    public void doSomething() {
        System.out.println("双重检查锁单例正在执行任务...");
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

package com.design.mode.creational.singleton;

import java.io.Serializable;

/**
 * 单例模式 - 静态内部类
 *
 * 特点：
 * 1. 采用类装载的机制来保证初始化实例时只有一个线程
 * 2. 静态内部类在外部类被装载时不会被立即实例化，而是在需要实例化时才会装载内部类
 * 3. 避免了线程不安全、延迟加载，效率高
 * 4. 推荐使用，是实现单例的最佳方式之一
 */
public class SingletonStaticInnerClass implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 私有化构造函数
     */
    private SingletonStaticInnerClass() {}

    /**
     * 静态内部类
     * 在外部类被加载时，内部类不会被加载
     * 只有当调用 getInstance() 时，内部类才会被加载并初始化 instance
     * 
     * 类加载机制：
     * - JVM 在加载外部类时，不会立即加载静态内部类
     * - 只有当首次访问静态内部类时，才会加载并初始化它
     * - 类初始化时由 JVM 保证线程安全
     * 
     * 优点：
     * - 线程安全：由 JVM 的类加载机制保证
     * - 懒加载：只有第一次使用时才加载内部类
     * - 性能好：没有同步开销
     * - 实现简洁：代码清晰
     * 
     * 这是实现单例的最佳方式之一，推荐使用。
     */
    private static class SingletonHolder {
        /**
         * 在内部类中创建单例实例
         * static final 保证全局唯一且不可修改
         */
        private static final SingletonStaticInnerClass INSTANCE = new SingletonStaticInnerClass();
    }

    /**
     * 对外提供获取实例的方法
     * 
     * 调用时才会加载 SingletonHolder 类，
     * 从而实现懒加载。
     *
     * @return 单例实例
     */
    public static SingletonStaticInnerClass getInstance() {
        return SingletonHolder.INSTANCE;
    }

    /**
     * 示例业务方法
     */
    public void doSomething() {
        System.out.println("静态内部类单例正在执行任务...");
    }

    /**
     * 防止序列化破坏单例模式
     * 当对象被反序列化时，会调用此方法返回单例实例
     */
    private Object readResolve() {
        return getInstance();
    }
}

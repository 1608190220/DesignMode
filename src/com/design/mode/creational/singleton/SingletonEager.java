package com.design.mode.creational.singleton;

import java.io.Serializable;

/**
 * 单例模式 - 饿汉式（静态常量）
 * 
 * 单例模式的核心思想：确保一个类只有一个实例，并提供一个全局访问点。
 * 
 * 饿汉式特点：
 * 1. 类加载时就完成实例化，避免了线程同步问题
 * 2. 由于没有达到懒加载效果，如果从未使用过这个实例，会造成内存浪费
 * 3. 基于 classloader 机制避免了多线程的同步问题
 * 4. 推荐使用场景：类加载时就可以确定会使用该单例实例
 * 
 * 单例模式的通用实现要点：
 * - 私有构造函数：防止外部直接创建实例
 * - 私有静态变量：持有单例实例
 * - 公共静态方法：提供全局访问点
 * - 防止反射攻击（可选）
 * - 防止序列化破坏（可选）
 * 
 * 适用场景：
 * - 资源共享的场景，如配置管理器、日志记录器
 * - 控制资源访问的场景，如数据库连接池
 * - 需要唯一实例的场景，如唯一序列号生成器
 */
public class SingletonEager implements Serializable {

    /**
     * 序列化版本号，用于序列化兼容性
     */
    private static final long serialVersionUID = 1L;

    /**
     * 私有化构造函数，防止外部直接创建实例
     * 
     * 这是单例模式的关键：私有构造函数确保外部无法通过 new 关键字创建实例。
     * 
     * 注意：虽然私有构造函数可以防止直接实例化，
     * 但仍可以通过反射机制绕过，需要额外的防护措施。
     */
    private SingletonEager() {}

    /**
     * 在类加载时就创建单例实例
     * 
     * 饿汉式的核心：
     * - static 关键字保证在全局只有一份，且在类加载时初始化
     * - final 关键字保证实例不可被修改
     * - 基于 classloader 机制，避免了多线程的同步问题
     * 
     * 优点：
     * - 线程安全：由 JVM 保证
     * - 实现简单：代码简洁
     * - 调用效率高：没有同步开销
     * 
     * 缺点：
     * - 没有懒加载：如果从未使用，会浪费内存
     * - 类加载时就创建：可能延长启动时间
     */
    private static final SingletonEager INSTANCE = new SingletonEager();

    /**
     * 对外提供获取单例实例的方法
     * 
     * 这是单例模式的全局访问点，客户端通过这个方法获取单例实例。
     * 
     * @return 单例实例，全局唯一
     */
    public static SingletonEager getInstance() {
        return INSTANCE;
    }

    /**
     * 示例业务方法
     * 
     * 单例类的业务方法，展示单例的使用方式。
     */
    public void doSomething() {
        System.out.println("饿汉式单例正在执行任务...");
    }

    /**
     * 防止序列化破坏单例模式
     * 
     * 当对象被反序列化时，会调用此方法返回单例实例，
     * 而不是创建新的实例。
     * 
     * 这是实现 Serializable 接口时必须添加的方法，
     * 以确保反序列化后仍然保持单例特性。
     * 
     * @return 单例实例
     */
    private Object readResolve() {
        return INSTANCE;
    }
}

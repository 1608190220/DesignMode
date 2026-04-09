package com.design.mode.creational.singleton;

/**
 * 单例模式测试类
 * 
 * 本类演示了各种单例模式实现方式的使用和对比，
 * 说明了它们各自的特点和适用场景。
 * 
 * 单例模式的核心验证：
 * - 通过 == 比较验证两个实例是否是同一个对象
 * - 如果返回 true，说明是同一个实例，符合单例模式要求
 * 
 * 各种实现方式的对比：
 * 1. 饿汉式：简单可靠，但没有懒加载
 * 2. 懒汉式（线程不安全）：有懒加载，但多线程不安全
 * 3. 懒汉式（同步方法）：线程安全，但性能差
 * 4. 双重检查锁（DCL）：推荐使用，兼顾性能和线程安全
 * 5. 静态内部类：推荐使用，利用 JVM 类加载机制
 * 6. 枚举方式：最推荐，JVM 原生支持，最安全
 */
public class SingletonTest {

    /**
     * 主测试方法
     * 
     * 测试各种单例实现，验证它们是否真的是单例。
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("========== 饿汉式单例测试 ==========");
        SingletonEager eager1 = SingletonEager.getInstance();
        SingletonEager eager2 = SingletonEager.getInstance();
        System.out.println("eager1 == eager2: " + (eager1 == eager2));
        eager1.doSomething();
        eager2.doSomething();

        System.out.println("\n========== 懒汉式（线程不安全）单例测试 ==========");
        SingletonLazy lazy1 = SingletonLazy.getInstance();
        SingletonLazy lazy2 = SingletonLazy.getInstance();
        System.out.println("lazy1 == lazy2: " + (lazy1 == lazy2));
        lazy1.doSomething();

        System.out.println("\n========== 懒汉式（线程安全-同步方法）单例测试 ==========");
        SingletonLazySafe safe1 = SingletonLazySafe.getInstance();
        SingletonLazySafe safe2 = SingletonLazySafe.getInstance();
        System.out.println("safe1 == safe2: " + (safe1 == safe2));
        safe1.doSomething();

        System.out.println("\n========== 双重检查锁单例测试 ==========");
        SingletonDCL dcl1 = SingletonDCL.getInstance();
        SingletonDCL dcl2 = SingletonDCL.getInstance();
        System.out.println("dcl1 == dcl2: " + (dcl1 == dcl2));
        dcl1.doSomething();

        System.out.println("\n========== 静态内部类单例测试 ==========");
        SingletonStaticInnerClass static1 = SingletonStaticInnerClass.getInstance();
        SingletonStaticInnerClass static2 = SingletonStaticInnerClass.getInstance();
        System.out.println("static1 == static2: " + (static1 == static2));
        static1.doSomething();

        System.out.println("\n========== 枚举单例测试 ==========");
        SingletonEnum enum1 = SingletonEnum.INSTANCE;
        SingletonEnum enum2 = SingletonEnum.INSTANCE;
        System.out.println("enum1 == enum2: " + (enum1 == enum2));
        enum1.doSomething();
        enum2.doSomething();

        System.out.println("\n========== 总结 ==========");
        System.out.println("1. 饿汉式：简单，但没有懒加载");
        System.out.println("2. 懒汉式（线程不安全）：不推荐在多线程环境下使用");
        System.out.println("3. 懒汉式（同步方法）：线程安全但性能差");
        System.out.println("4. 双重检查锁：推荐使用，延迟加载且线程安全");
        System.out.println("5. 静态内部类：推荐使用，利用类加载机制实现线程安全");
        System.out.println("6. 枚举方式：最推荐，JVM天生支持单例，最安全");
    }
}

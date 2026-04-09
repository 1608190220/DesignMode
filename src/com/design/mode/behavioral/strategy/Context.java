package com.design.mode.behavioral.strategy;

/**
 * 上下文类（Context）
 * 
 * 策略模式中的上下文角色，负责持有并调度策略对象，在运行期切换具体算法。
 * 这是策略模式的核心类，客户端通过这个类来使用策略。
 * 
 * 上下文角色的职责：
 * 1. 管理当前策略引用
 * 2. 对外暴露统一执行入口
 * 3. 隔离客户端与具体策略类的直接耦合
 * 4. 支持运行时动态切换策略
 * 
 * 上下文的设计原则：
 * - 组合优于继承：通过组合而非继承来使用策略
 * - 依赖倒置：依赖于抽象（Strategy）而非具体类
 * - 开闭原则：新增策略不需要修改 Context
 * 
 * 职责边界：
 * 1. 管理当前策略引用
 * 2. 对外暴露统一执行入口
 * 3. 隔离客户端与具体策略类的直接耦合
 */
public class Context {
    /** 
     * 当前策略对象引用
     * 
     * 使用组合而非继承的方式持有策略对象，
     * 这是策略模式的关键设计：组合优于继承。
     * 
     * 设计要点：
     * - 持有 Strategy 接口的引用，而非具体类
     * - 支持运行时动态切换策略
     * - 符合依赖倒置原则
     */
    private Strategy strategy;
    
    /**
     * 构造方法
     * 
     * 通过构造函数注入初始策略，这是依赖注入的一种形式。
     * 客户端在创建 Context 时可以指定初始策略。
     * 
     * @param strategy 初始策略
     */
    public Context(Strategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * 设置（切换）策略
     * 
     * 该方法支持在运行时替换算法实现，
     * 是策略模式动态行为切换的核心入口之一。
     * 
     * 动态切换策略的优势：
     * - 运行时灵活选择算法
     * - 无需重新创建 Context 对象
     * - 可以根据条件动态调整策略
     * 
     * @param strategy 新的策略
     */
    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
        System.out.println("策略已切换");
    }
    
    /**
     * 执行当前策略
     * 
     * 调用具体策略的算法入口，Context 本身不关心算法细节。
     * 这是策略模式的统一执行入口，客户端通过这个方法来执行策略。
     * 
     * 设计要点：
     * - 统一的方法签名，确保所有策略都有相同的调用方式
     * - Context 不关心算法细节，只负责调用
     * - 具体算法由 Strategy 实现类提供
     */
    public void executeStrategy() {
        strategy.execute();
    }
    
    /**
     * 获取当前策略
     * 
     * 允许客户端获取当前正在使用的策略对象。
     * 
     * @return 当前策略
     */
    public Strategy getStrategy() {
        return strategy;
    }
}

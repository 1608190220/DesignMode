package com.design.mode.behavioral.state;

/**
 * 上下文类（Context）
 * 
 * 状态模式中的上下文角色，维护当前状态并提供状态切换方法。
 * 这是状态模式的核心，负责与客户端交互，并将请求委托给当前状态对象。
 * 
 * 上下文角色的职责：
 * 1. 维护当前状态对象的引用
 * 2. 提供状态切换的方法
 * 3. 对外提供统一的请求入口
 * 4. 将请求委托给当前状态对象处理
 * 
 * 上下文的设计原则：
 * - 组合优于继承：通过组合而非继承来使用状态
 * - 依赖倒置：依赖于抽象（State）而非具体类
 * - 开闭原则：新增状态不需要修改 Context
 * 
 * 状态模式的核心思想：
 * - 上下文维护当前状态
 * - 客户端通过上下文的 request() 方法发起请求
 * - 上下文将请求委托给当前状态对象的 handle() 方法
 * - 状态对象在 handle() 方法中决定是否切换状态
 */
public class Context {
    
    /**
     * 当前状态
     * 
     * 使用组合而非继承的方式持有状态对象，
     * 这是状态模式的关键设计：组合优于继承。
     * 
     * 设计要点：
     * - 持有 State 接口的引用，而非具体类
     * - 支持运行时动态切换状态
     * - 符合依赖倒置原则
     */
    private State currentState;
    
    /**
     * 构造方法
     * 
     * 初始化上下文并设置初始状态。
     * 在创建 Context 时，将初始状态设置为 InitialState。
     */
    public Context() {
        // 初始状态为InitialState
        this.currentState = new InitialState();
        System.out.println("上下文已初始化，当前状态: " + currentState.getStateName());
    }
    
    /**
     * 设置当前状态
     * 
     * 这是状态切换的核心方法，状态类通过调用这个方法来切换状态。
     * 当状态类在 handle() 方法中决定切换状态时，会调用这个方法。
     * 
     * @param state 新状态
     */
    public void setState(State state) {
        this.currentState = state;
        System.out.println("状态已更新为: " + currentState.getStateName());
    }
    
    /**
     * 获取当前状态
     * 
     * 允许客户端或状态类获取当前正在使用的状态对象。
     * 
     * @return 当前状态
     */
    public State getState() {
        return currentState;
    }
    
    /**
     * 执行当前状态的行为
     * 
     * 这是上下文对外的统一请求入口，客户端通过这个方法发起请求。
     * 上下文将请求委托给当前状态对象的 handle() 方法处理。
     * 
     * 设计要点：
     * - 统一的方法签名，确保所有状态都有相同的调用方式
     * - Context 不关心状态细节，只负责委托
     * - 具体行为由 State 实现类提供
     */
    public void request() {
        currentState.handle(this);
    }
}

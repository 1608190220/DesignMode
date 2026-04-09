package com.design.mode.behavioral.state;

/**
 * 初始状态（Concrete State）
 * 
 * 状态模式中的具体状态角色，表示对象的初始状态。
 * 这是状态机的起始状态，对象被创建时首先进入此状态。
 * 
 * 具体状态角色的职责：
 * - 实现 State 接口
 * - 提供当前状态下的行为逻辑
 * - 根据条件决定是否需要切换到其他状态
 * - 通过 context.setState() 来切换状态
 * 
 * 初始状态特点：
 * - 这是状态机的起始状态
 * - 通常用于初始化和准备工作
 * - 通常会在完成准备后切换到运行状态
 * - 可以被重置状态多次回到这个状态
 */
public class InitialState implements State {
    
    /**
     * 处理初始状态下的行为
     * 
     * 在初始状态下，我们可以开始执行任务并切换到运行状态。
     * 这是状态模式的核心方法，每个具体状态类都需要实现这个方法。
     * 
     * 工作流程：
     * 1. 执行当前状态的业务逻辑（准备开始任务）
     * 2. 决定是否需要切换到其他状态
     * 3. 如果需要切换，通过 context.setState() 来切换状态
     * 
     * 状态转换：
     * 初始状态 -&gt; 运行状态
     * 
     * @param context 上下文对象，用于状态切换
     */
    @Override
    public void handle(Context context) {
        System.out.println("当前状态: " + getStateName());
        System.out.println("执行初始状态操作: 准备开始任务");
        
        // 状态转换：从初始状态到运行状态
        System.out.println("状态转换: 初始状态 -&gt; 运行状态");
        context.setState(new RunningState());
    }
    
    /**
     * 获取状态名称
     * 
     * 用于调试和日志输出，方便查看当前状态。
     * 
     * @return 状态名称 "初始状态"
     */
    @Override
    public String getStateName() {
        return "初始状态";  
    }
}

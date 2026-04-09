package com.design.mode.behavioral.state;

/**
 * 完成状态（Concrete State）
 * 
 * 状态模式中的具体状态角色，表示对象任务已完成的状态。
 * 这是状态机的结束状态，对象在此状态下完成所有任务。
 * 
 * 具体状态角色的职责：
 * - 实现 State 接口
 * - 提供当前状态下的行为逻辑
 * - 根据条件决定是否需要切换到其他状态
 * - 通过 context.setState() 来切换状态
 * 
 * 完成状态特点：
 * - 这是状态机的结束状态
 * - 通常用于清理和收尾工作
 * - 可以在完成后重置回初始状态
 * - 可以形成一个循环的状态机（初始 -&gt; 运行 -&gt; 完成 -&gt; 初始）
 */
public class CompletedState implements State {
    
    /**
     * 处理完成状态下的行为
     * 
     * 在完成状态下，任务已结束，我们可以重置状态回到初始状态。
     * 这是状态模式的核心方法，每个具体状态类都需要实现这个方法。
     * 
     * 工作流程：
     * 1. 执行当前状态的业务逻辑（任务已完成，准备重置）
     * 2. 决定是否需要切换到其他状态
     * 3. 如果需要切换，通过 context.setState() 来切换状态
     * 
     * 状态转换：
     * 完成状态 -&gt; 初始状态
     * 
     * @param context 上下文对象，用于状态切换
     */
    @Override
    public void handle(Context context) {
        System.out.println("当前状态: " + getStateName());
        System.out.println("执行完成状态操作: 任务已完成，准备重置");
        
        // 状态转换：从完成状态到初始状态
        System.out.println("状态转换: 完成状态 -&gt; 初始状态");
        context.setState(new InitialState());
    }
    
    /**
     * 获取状态名称
     * 
     * 用于调试和日志输出，方便查看当前状态。
     * 
     * @return 状态名称 "完成状态"
     */
    @Override
    public String getStateName() {
        return "完成状态";  
    }
}

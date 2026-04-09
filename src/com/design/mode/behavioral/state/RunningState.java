package com.design.mode.behavioral.state;

/**
 * 运行状态（Concrete State）
 * 
 * 状态模式中的具体状态角色，表示对象正在执行任务的状态。
 * 这是状态机的中间状态，对象在此状态下执行主要的业务逻辑。
 * 
 * 具体状态角色的职责：
 * - 实现 State 接口
 * - 提供当前状态下的行为逻辑
 * - 根据条件决定是否需要切换到其他状态
 * - 通过 context.setState() 来切换状态
 * 
 * 运行状态特点：
 * - 这是状态机的核心工作状态
 * - 通常包含主要的业务逻辑
 * - 可以在完成后切换到完成状态
 * - 可以模拟任务执行的延迟
 */
public class RunningState implements State {
    
    /**
     * 处理运行状态下的行为
     * 
     * 在运行状态下，我们可以继续执行任务或完成任务并切换到完成状态。
     * 这是状态模式的核心方法，每个具体状态类都需要实现这个方法。
     * 
     * 工作流程：
     * 1. 执行当前状态的业务逻辑（正在处理任务...）
     * 2. 模拟任务执行（使用 Thread.sleep() 模拟延迟）
     * 3. 决定是否需要切换到其他状态
     * 4. 如果需要切换，通过 context.setState() 来切换状态
     * 
     * 状态转换：
     * 运行状态 -&gt; 完成状态
     * 
     * @param context 上下文对象，用于状态切换
     */
    @Override
    public void handle(Context context) {
        System.out.println("当前状态: " + getStateName());
        System.out.println("执行运行状态操作: 正在处理任务...");
        
        // 模拟任务执行
        try {
            Thread.sleep(1000); // 模拟任务执行时间
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 状态转换：从运行状态到完成状态
        System.out.println("状态转换: 运行状态 -&gt; 完成状态");
        context.setState(new CompletedState());
    }
    
    /**
     * 获取状态名称
     * 
     * 用于调试和日志输出，方便查看当前状态。
     * 
     * @return 状态名称 "运行状态"
     */
    @Override
    public String getStateName() {
        return "运行状态";  
    }
}

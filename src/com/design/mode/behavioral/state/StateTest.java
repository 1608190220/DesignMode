package com.design.mode.behavioral.state;

/**
 * 状态模式测试类
 * 
 * 演示状态模式的使用和状态切换过程。
 * 通过多次调用 context.request() 来观察状态的自动切换。
 * 
 * 测试目标：
 * 1. 验证状态模式的基本工作原理
 * 2. 展示状态之间的自动切换
 * 3. 验证状态机的循环行为（初始 -&gt; 运行 -&gt; 完成 -&gt; 初始）
 * 4. 说明如何避免大量的条件判断语句
 * 
 * 状态机流程：
 * 初始状态 -&gt; 运行状态 -&gt; 完成状态 -&gt; 初始状态 -&gt; ...
 * 
 * 每次调用 context.request() 都会触发一次状态切换。
 */
public class StateTest {
    
    /**
     * 程序入口
     * 
     * 通过多次调用 context.request() 来演示状态模式的工作原理：
     * 1. 第一次调用：初始状态 -&gt; 运行状态
     * 2. 第二次调用：运行状态 -&gt; 完成状态
     * 3. 第三次调用：完成状态 -&gt; 初始状态
     * 4. 第四次调用：初始状态 -&gt; 运行状态（循环开始）
     * 
     * 这展示了状态模式的核心优势：
     * - 状态的转换逻辑清晰，易于理解和维护
     * - 避免了大量的 if-else 或 switch-case 语句
     * - 新增状态简单，符合开闭原则
     * 
     * @param args 命令行参数（本示例不使用）
     */
    public static void main(String[] args) {
        System.out.println("===== 状态模式演示 =====");
        
        // 创建上下文对象
        Context context = new Context();
        
        System.out.println("\n1. 第一次调用request():");
        // 第一次调用request()，从初始状态切换到运行状态
        context.request();
        
        System.out.println("\n2. 第二次调用request():");
        // 第二次调用request()，从运行状态切换到完成状态
        context.request();
        
        System.out.println("\n3. 第三次调用request():");
        // 第三次调用request()，从完成状态切换到初始状态
        context.request();
        
        System.out.println("\n4. 第四次调用request():");
        // 第四次调用request()，从初始状态切换到运行状态
        context.request();
        
        System.out.println("\n===== 状态模式演示结束 =====");
    }
}

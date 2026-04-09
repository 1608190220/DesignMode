package com.design.mode.behavioral.templatemethod;

/**
 * 抽象类（Abstract Class）
 * 定义算法骨架，并将可变步骤延迟到子类实现
 *
 * 设计意图：
 * 1. 在父类中固定流程顺序，保证算法结构稳定。
 * 2. 在子类中实现具体步骤，满足不同业务场景差异。
 * 3. 通过模板方法控制扩展点，避免子类破坏整体执行流程。
 */
public abstract class AbstractClass {
    
    /**
     * 模板方法（Template Method）
     * 定义完整算法执行顺序，并声明为 final 防止子类覆盖流程
     *
     * 固定执行步骤：
     * 1. 初始化
     * 2. 核心处理
     * 3. 结果处理
     * 4. 资源清理
     */
    public final void templateMethod() {
        // 步骤1：初始化
        initialize();
        
        // 步骤2：执行核心操作
        coreOperation();
        
        // 步骤3：处理结果
        processResult();
        
        // 步骤4：清理资源
        cleanup();
    }
    
    /**
     * 初始化步骤
     *
     * 用于准备算法运行所需的上下文、配置或资源。
     * 由子类按具体场景实现。
     */
    protected abstract void initialize();
    
    /**
     * 核心业务步骤
     *
     * 该步骤通常承载主要业务逻辑。
     * 由子类实现具体处理策略。
     */
    protected abstract void coreOperation();
    
    /**
     * 结果处理步骤
     *
     * 用于对核心处理结果进行格式化、持久化或后置处理。
     * 由子类实现具体行为。
     */
    protected abstract void processResult();
    
    /**
     * 清理资源步骤（Hook 方法）
     *
     * 父类提供默认实现，子类可按需覆盖：
     * - 不覆盖时：复用默认清理逻辑；
     * - 覆盖时：执行特定资源回收策略。
     */
    protected void cleanup() {
        System.out.println("执行默认的清理操作");
    }
}

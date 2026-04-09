package com.design.mode.behavioral.strategy;

/**
 * 具体策略A（Concrete Strategy A）
 * 
 * 策略模式中的具体策略角色，提供"快速排序"场景的策略实现。
 * 这是策略模式的具体实现类，实现了 Strategy 接口。
 * 
 * 具体策略角色的职责：
 * - 实现 Strategy 接口
 * - 提供具体的算法实现
 * - 可以被 Context 持有和调用
 * 
 * 快速排序算法特点：
 * - 平均时间复杂度：O(n log n)
 * - 最坏时间复杂度：O(n²)
 * - 空间复杂度：O(log n)
 * - 不稳定排序
 * - 分治算法思想
 * 
 * 说明：
 * 该类用于演示一个高效排序策略在统一策略接口下的接入方式。
 * 重点体现"策略可替换"而非具体排序代码细节。
 */
public class ConcreteStrategyA implements Strategy {
    /**
     * 执行快速排序策略（示意版）
     * 
     * 通过输出关键步骤描述算法流程：
     * 1. 选择基准
     * 2. 分区
     * 3. 递归处理子区间
     * 
     * 这是策略模式的核心方法，提供具体的算法实现。
     * Context 会调用这个方法来执行具体的排序策略。
     * 
     * 快速排序算法流程说明：
     * 1. 选择基准元素（pivot）
     * 2. 分区操作：将小于基准的放左边，大于基准的放右边
     * 3. 递归排序左右子数组
     * 4. 合并结果
     */
    @Override
    public void execute() {
        System.out.println("执行策略A：使用快速排序算法");
        // 以下为流程示意，重点体现"策略可替换"而非具体排序代码细节
        System.out.println("步骤1: 选择基准元素");
        System.out.println("步骤2: 分区操作");
        System.out.println("步骤3: 递归排序子数组");
        System.out.println("快速排序完成，时间复杂度 O(n log n)");
    }
}

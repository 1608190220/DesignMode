package com.design.mode.behavioral.strategy;

/**
 * 具体策略B（Concrete Strategy B）
 * 
 * 策略模式中的具体策略角色，提供"冒泡排序"场景的策略实现。
 * 这是策略模式的具体实现类，实现了 Strategy 接口。
 * 
 * 具体策略角色的职责：
 * - 实现 Strategy 接口
 * - 提供具体的算法实现
 * - 可以被 Context 持有和调用
 * 
 * 冒泡排序算法特点：
 * - 时间复杂度：O(n²)
 * - 空间复杂度：O(1)
 * - 稳定排序
 * - 简单易懂，适合小规模数据
 * - 适合教学演示
 * 
 * 说明：
 * 该类用于展示另一种可替换算法，便于与策略A/策略C进行横向对比。
 * 重点体现不同策略的差异和可替换性。
 */
public class ConcreteStrategyB implements Strategy {
    /**
     * 执行冒泡排序策略（示意版）
     * 
     * 通过输出关键步骤描述算法流程：
     * 1. 比较相邻元素
     * 2. 交换逆序元素
     * 3. 多轮遍历直到有序
     * 
     * 这是策略模式的核心方法，提供具体的算法实现。
     * Context 会调用这个方法来执行具体的排序策略。
     * 
     * 冒泡排序算法流程说明：
     * 1. 从第一个元素开始，比较相邻的元素
     * 2. 如果前一个比后一个大，就交换它们
     * 3. 对每一对相邻元素重复步骤1-2
     * 4. 重复以上步骤，直到排序完成
     */
    @Override
    public void execute() {
        System.out.println("执行策略B：使用冒泡排序算法");
        // 以下为流程示意，用于体现不同策略的差异
        System.out.println("步骤1: 比较相邻元素");
        System.out.println("步骤2: 交换顺序错误的元素");
        System.out.println("步骤3: 重复直到排序完成");
        System.out.println("冒泡排序完成，时间复杂度 O(n²)");
    }
}

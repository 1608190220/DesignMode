package com.design.mode.behavioral.visitor;

/**
 * 具体元素（Concrete Element）- 经理
 *
 * 该类封装经理数据，并通过访问者机制支持多维业务处理：
 * 例如薪资统计、绩效评估、组织管理分析等。
 */
public class Manager implements Employee {
    /** 经理姓名 */
    private String name;
    /** 经理薪资 */
    private double salary;
    /** 经理绩效分 */
    private int performance;
    /** 经理特有指标：团队规模 */
    private int teamSize;
    
    /**
     * 构造经理对象
     *
     * @param name 姓名
     * @param salary 薪资
     * @param performance 绩效分
     * @param teamSize 团队规模
     */
    public Manager(String name, double salary, int performance, int teamSize) {
        this.name = name;
        this.salary = salary;
        this.performance = performance;
        this.teamSize = teamSize;
    }
    
    @Override
    public void accept(Visitor visitor) {
        // 双分派：把当前具体类型（Manager）交由访问者执行对应逻辑
        visitor.visit(this);
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    @Override
    public double getSalary() {
        return salary;
    }
    
    @Override
    public int getPerformance() {
        return performance;
    }
    
    /**
     * 获取团队规模
     *
     * @return 团队规模，可用于管理维度分析
     */
    public int getTeamSize() {
        return teamSize;
    }
    
    /**
     * 设置团队规模
     *
     * @param teamSize 团队规模
     */
    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
}

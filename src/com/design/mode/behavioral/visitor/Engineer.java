package com.design.mode.behavioral.visitor;

/**
 * 具体元素（Concrete Element）- 工程师
 *
 * 该类承载工程师数据，并通过 {@link #accept(Visitor)} 将自身交给访问者处理。
 * 与访问者配合后，可在不修改该类业务结构的前提下扩展新操作（如新增报表类型）。
 */
public class Engineer implements Employee {
    /** 工程师姓名 */
    private String name;
    /** 工程师薪资 */
    private double salary;
    /** 工程师绩效分 */
    private int performance;
    /** 工程师特有指标：代码行数（lines of code） */
    private int linesOfCode;
    
    /**
     * 构造工程师对象
     *
     * @param name 姓名
     * @param salary 薪资
     * @param performance 绩效分
     * @param linesOfCode 代码行数
     */
    public Engineer(String name, double salary, int performance, int linesOfCode) {
        this.name = name;
        this.salary = salary;
        this.performance = performance;
        this.linesOfCode = linesOfCode;
    }
    
    @Override
    public void accept(Visitor visitor) {
        // 双分派：将当前运行时类型（Engineer）交给访问者进行匹配处理
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
     * 获取代码行数
     *
     * @return 代码行数，可用于产出相关分析
     */
    public int getLinesOfCode() {
        return linesOfCode;
    }
    
    /**
     * 设置代码行数
     *
     * @param linesOfCode 代码行数
     */
    public void setLinesOfCode(int linesOfCode) {
        this.linesOfCode = linesOfCode;
    }
}

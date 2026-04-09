package com.design.mode.behavioral.visitor;

/**
 * 元素接口（Element）- 员工抽象
 *
 * 在访问者模式中，元素接口负责定义“被访问”的统一入口。
 * 所有具体员工类型（如工程师、经理）都通过 {@link #accept(Visitor)} 与访问者交互，
 * 从而把“数据结构（员工对象）”与“业务操作（报表、统计、评估）”解耦。
 *
 * 设计要点：
 * 1. 保持元素接口稳定，便于新增访问者扩展功能。
 * 2. 对外暴露最小但足够的读取能力，便于访问者执行横切业务逻辑。
 */
public interface Employee {
    
    /**
     * 接受访问者访问（双分派入口）
     *
     * 具体元素实现中通常会调用 {@code visitor.visit(this)}，
     * 使运行时类型信息参与方法分派，确保访问者执行正确的重载逻辑。
     *
     * @param visitor 访问者对象，封装了对当前员工的业务处理逻辑
     */
    void accept(Visitor visitor);
    
    /**
     * 获取员工姓名
     *
     * @return 员工姓名，用于报表展示、日志输出等场景
     */
    String getName();
    
    /**
     * 获取员工薪资
     *
     * @return 当前员工薪资，用于薪资统计与分析
     */
    double getSalary();
    
    /**
     * 获取员工绩效分
     *
     * @return 绩效分值，通常用于绩效分层、评级和汇总分析
     */
    int getPerformance();
}

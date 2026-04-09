package com.design.mode.behavioral.visitor;

/**
 * 具体访问者（Concrete Visitor）- 绩效评估访问者
 *
 * 该访问者负责遍历员工对象并聚合绩效数据，输出个人与整体绩效结果。
 * 相比把绩效逻辑写入员工类中，此实现将“操作”独立出来，提升扩展性与可维护性。
 */
public class PerformanceVisitor implements Visitor {
    /** 累计绩效总分 */
    private int totalPerformance = 0;
    /** 已统计员工人数 */
    private int employeeCount = 0;
    
    @Override
    public void visit(Engineer engineer) {
        // 处理工程师绩效并纳入汇总
        int performance = engineer.getPerformance();
        totalPerformance += performance;
        employeeCount++;
        
        String level = getPerformanceLevel(performance);
        System.out.println("工程师: " + engineer.getName() + ", 绩效: " + performance + " (" + level + "), 代码行数: " + engineer.getLinesOfCode());
    }
    
    @Override
    public void visit(Manager manager) {
        // 处理经理绩效并纳入汇总
        int performance = manager.getPerformance();
        totalPerformance += performance;
        employeeCount++;
        
        String level = getPerformanceLevel(performance);
        System.out.println("经理: " + manager.getName() + ", 绩效: " + performance + " (" + level + "), 团队规模: " + manager.getTeamSize());
    }
    
    /**
     * 根据绩效分数获取绩效等级
     *
     * @param performance 绩效分数
     * @return 绩效等级
     */
    private String getPerformanceLevel(int performance) {
        if (performance >= 90) {
            return "优秀";
        } else if (performance >= 80) {
            return "良好";
        } else if (performance >= 70) {
            return "合格";
        } else {
            return "待改进";
        }
    }
    
    /**
     * 生成绩效报告
     *
     * 输出内容包括：
     * 1. 员工总数
     * 2. 绩效总分
     * 3. 平均绩效与整体评级
     */
    public void generateReport() {
        System.out.println("\n========== 绩效报告 ==========");
        System.out.println("员工总数: " + employeeCount);
        System.out.println("总绩效分: " + totalPerformance);
        if (employeeCount > 0) {
            // 避免整除丢失精度，先转为 double 再计算平均值
            double avgPerformance = (double) totalPerformance / employeeCount;
            System.out.println("平均绩效: " + avgPerformance);
            System.out.println("整体绩效水平: " + getPerformanceLevel((int) avgPerformance));
        }
        System.out.println("============================");
    }
    
    /**
     * 获取总绩效分
     * 
     * @return 总绩效分
     */
    public int getTotalPerformance() {
        return totalPerformance;
    }
    
    /**
     * 获取员工数量
     * 
     * @return 员工数量
     */
    public int getEmployeeCount() {
        return employeeCount;
    }
}

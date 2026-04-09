package com.design.mode.behavioral.visitor;

/**
 * 具体访问者（Concrete Visitor）- 薪资报告访问者
 *
 * 该访问者负责收集不同员工类型的薪资数据并输出统计报告，
 * 体现“同一对象结构、不同访问者执行不同业务”的访问者模式特征。
 */
public class SalaryReportVisitor implements Visitor {
    /** 累计薪资总额 */
    private double totalSalary = 0;
    /** 已统计员工人数 */
    private int employeeCount = 0;
    
    @Override
    public void visit(Engineer engineer) {
        // 处理工程师薪资并纳入汇总
        double salary = engineer.getSalary();
        totalSalary += salary;
        employeeCount++;
        
        System.out.println("工程师: " + engineer.getName() + ", 薪资: " + salary + ", 代码行数: " + engineer.getLinesOfCode());
    }
    
    @Override
    public void visit(Manager manager) {
        // 处理经理薪资并纳入汇总
        double salary = manager.getSalary();
        totalSalary += salary;
        employeeCount++;
        
        System.out.println("经理: " + manager.getName() + ", 薪资: " + salary + ", 团队规模: " + manager.getTeamSize());
    }
    
    /**
     * 生成薪资报告
     *
     * 输出内容包括：
     * 1. 员工总数
     * 2. 薪资总额
     * 3. 平均薪资
     */
    public void generateReport() {
        System.out.println("\n========== 薪资报告 ==========");
        System.out.println("员工总数: " + employeeCount);
        System.out.println("总薪资: " + totalSalary);
        if (employeeCount > 0) {
            // 仅在有样本时计算平均值，避免除零异常
            System.out.println("平均薪资: " + (totalSalary / employeeCount));
        }
        System.out.println("============================");
    }
    
    /**
     * 获取总薪资
     * 
     * @return 总薪资
     */
    public double getTotalSalary() {
        return totalSalary;
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

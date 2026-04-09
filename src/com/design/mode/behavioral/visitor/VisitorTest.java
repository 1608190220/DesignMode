package com.design.mode.behavioral.visitor;

/**
 * 访问者模式测试类
 *
 * 本类通过“员工管理系统”场景演示访问者模式的协作流程与扩展价值。
 * 核心目标是展示：在不修改员工类结构的前提下，如何通过新增访问者扩展新功能。
 *
 * 访问者模式的核心概念：
 * 1. 元素（Element）：定义接受访问者访问的方法
 * 2. 具体元素（Concrete Element）：实现元素接口的具体类
 * 3. 访问者（Visitor）：定义访问不同元素的方法
 * 4. 具体访问者（Concrete Visitor）：实现访问者接口的具体类
 * 5. 对象结构（Object Structure）：管理元素集合，提供接受访问者的方法
 * 
 * 访问者模式的优点：
 * 1. 分离关注点：将数据结构与操作分离
 * 2. 扩展性好：新增操作只需添加新的访问者，无需修改元素类
 * 3. 灵活性高：可以对不同类型的元素执行不同的操作
 * 4. 符合开闭原则：对扩展开放，对修改关闭
 * 
 * 访问者模式的适用场景：
 * 1. 当需要对一个对象结构中的元素执行多种不同且不相关的操作时
 * 2. 当需要在不修改元素类的情况下，为元素添加新的操作时
 * 3. 当对象结构稳定，但操作经常变化时
 */
public class VisitorTest {

    /**
     * 程序入口
     *
     * 演示步骤：
     * 1. 构建对象结构（员工列表）
     * 2. 使用薪资访问者生成薪资报表
     * 3. 使用绩效访问者生成绩效报表
     * 4. 动态新增员工后再次生成报表，验证结构与操作分离带来的灵活性
     *
     * @param args 命令行参数（示例中未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   访问者模式测试                              ║");
        System.out.println("║                   —— 员工管理系统                              ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝\n");

        // 创建员工列表（对象结构）
        EmployeeList employeeList = new EmployeeList();

        // 添加工程师（具体元素）
        employeeList.addEmployee(new Engineer("张三", 15000, 92, 12000));
        employeeList.addEmployee(new Engineer("李四", 12000, 85, 9500));
        employeeList.addEmployee(new Engineer("王五", 10000, 78, 8000));

        // 添加经理（具体元素）
        employeeList.addEmployee(new Manager("赵总", 30000, 95, 10));
        employeeList.addEmployee(new Manager("钱经理", 25000, 88, 8));

        // 场景1：同一对象结构 + 薪资访问者，执行薪资维度统计
        System.out.println("========== 场景1：生成薪资报告 ==========");
        // 创建薪资报告访问者（具体访问者）
        SalaryReportVisitor salaryVisitor = new SalaryReportVisitor();
        // 让员工列表接受访问者访问
        employeeList.accept(salaryVisitor);
        // 生成薪资报告
        salaryVisitor.generateReport();

        // 场景2：同一对象结构 + 绩效访问者，执行绩效维度评估
        System.out.println("\n========== 场景2：生成绩效评估报告 ==========");
        // 创建绩效评估访问者（具体访问者）
        PerformanceVisitor performanceVisitor = new PerformanceVisitor();
        // 让员工列表接受访问者访问
        employeeList.accept(performanceVisitor);
        // 生成绩效报告
        performanceVisitor.generateReport();

        // 场景3：对象结构变化后复用访问者思路，重新输出结果
        System.out.println("\n========== 场景3：添加新员工后重新生成报告 ==========");
        // 添加新员工
        employeeList.addEmployee(new Engineer("孙六", 11000, 82, 8500));
        employeeList.addEmployee(new Manager("周总监", 28000, 90, 15));

        // 重新生成薪资报告
        System.out.println("\n--- 新的薪资报告 ---");
        SalaryReportVisitor newSalaryVisitor = new SalaryReportVisitor();
        employeeList.accept(newSalaryVisitor);
        newSalaryVisitor.generateReport();

        // 重新生成绩效报告
        System.out.println("\n--- 新的绩效报告 ---");
        PerformanceVisitor newPerformanceVisitor = new PerformanceVisitor();
        employeeList.accept(newPerformanceVisitor);
        newPerformanceVisitor.generateReport();

        System.out.println("\n╔═══════════════════════════════════════════════════════════════╗");
        System.out.println("║                   访问者模式总结                              ║");
        System.out.println("╚═══════════════════════════════════════════════════════════════╝");
        System.out.println("1. 元素（Employee）：定义了accept方法，接受访问者访问");
        System.out.println("2. 具体元素（Engineer、Manager）：实现了accept方法，调用访问者的对应方法");
        System.out.println("3. 访问者（Visitor）：定义了访问不同元素的方法");
        System.out.println("4. 具体访问者（SalaryReportVisitor、PerformanceVisitor）：实现了访问方法");
        System.out.println("5. 对象结构（EmployeeList）：管理元素集合，提供accept方法");
        System.out.println("\n优点：");
        System.out.println("- 分离了数据结构与操作，使代码更清晰");
        System.out.println("- 新增操作只需添加新的访问者，无需修改元素类");
        System.out.println("- 可以对不同类型的元素执行不同的操作");
        System.out.println("- 符合开闭原则：对扩展开放，对修改关闭");
        System.out.println("\n适用场景：");
        System.out.println("- 当需要对对象结构中的元素执行多种不同操作时");
        System.out.println("- 当需要在不修改元素类的情况下添加新操作时");
        System.out.println("- 当对象结构稳定，但操作经常变化时");
    }
}

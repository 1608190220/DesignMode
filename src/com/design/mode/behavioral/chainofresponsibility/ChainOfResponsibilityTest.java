package com.design.mode.behavioral.chainofresponsibility;

/**
 * 责任链模式测试类
 * 演示“请假审批流程”在责任链模式下的完整运行轨迹。
 *
 * 责任链模式（Chain of Responsibility Pattern）详解：
 *
 * 一、模式意图
 * 使多个对象都有机会处理请求，从而避免请求的发送者和接收者之间的耦合关系。
 * 将这些对象连成一条链，并沿着这条链传递请求，直到有一个对象处理它为止。
 *
 * 二、模式结构
 * 1. 处理器（Handler）: 定义处理请求的接口，这里是Approver
 * 2. 具体处理器（Concrete Handler）: 处理它负责的请求，可以访问它的后继者，
 *    如果可以处理请求就处理，否则将请求转发给后继者，这里是TeamLeader、DepartmentManager、GeneralManager
 * 3. 客户端（Client）: 向链上的具体处理器对象提交请求
 *
 * 三、适用场景
 * 1. 有多个对象可以处理一个请求，哪个对象处理该请求运行时自动确定
 * 2. 想在不明确指定接收者的情况下，向多个对象中的一个提交请求
 * 3. 可动态指定一组对象处理请求
 *
 * 四、优缺点
 * 优点：
 * - 降低耦合度：将请求的发送者和接收者解耦
 * - 简化对象：对象不需要知道链的结构
 * - 增强灵活性：可以动态地增加或修改处理链
 * - 方便扩展：增加新的具体处理器很容易
 *
 * 缺点：
 * - 请求可能得不到处理：请求可能到达链的末端都得不到处理
 * - 不易调试：请求可能经过多个处理器，调试时较复杂
 * - 性能问题：如果链太长，可能会影响性能
 *
 * 五、实际应用
 * - Java Servlet中的Filter链
 * - Spring Security中的过滤器链
 * - 审批流程、日志记录链、异常处理链
 *
 * 本测试重点验证：
 * 1. 请求能够按审批权限自动找到合适处理者
 * 2. 当当前节点无法处理时，请求可沿链向后传递
 * 3. 当链尾仍无法处理时，系统给出明确兜底反馈
 * 4. 责任链支持在运行时动态重组
 */
public class ChainOfResponsibilityTest {

    /**
     * 运行责任链模式示例。
     *
     * @param args 命令行参数（本示例未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  责任链模式测试                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 第一步：创建各审批节点对象（仅创建节点，不代表已形成链路）
        System.out.println("========== 创建审批链 ==========");
        TeamLeader teamLeader = new TeamLeader("张组长");
        DepartmentManager deptManager = new DepartmentManager("李经理");
        GeneralManager generalManager = new GeneralManager("王总");

        // 第二步：按业务顺序建立链路（链头为teamLeader）
        teamLeader.setNextApprover(deptManager);
        deptManager.setNextApprover(generalManager);

        System.out.println("审批链已建立：组长 -> 部门经理 -> 总经理");
        System.out.println("审批权限：组长(3天) -> 部门经理(7天) -> 总经理(30天)\n");

        // 场景1：短假，命中组长权限，期望在链头完成处理
        System.out.println("========== 测试1：2天请假申请（应由组长审批） ==========");
        LeaveRequest request1 = new LeaveRequest("张三", 2, "感冒需要休息");
        System.out.println("提交请假申请：" + request1);
        teamLeader.processLeaveRequest(request1);
        System.out.println();

        // 场景2：超过组长权限，向后转发到部门经理处理
        System.out.println("========== 测试2：5天请假申请（应由部门经理审批） ==========");
        LeaveRequest request2 = new LeaveRequest("李四", 5, "回家探亲");
        System.out.println("提交请假申请：" + request2);
        teamLeader.processLeaveRequest(request2);
        System.out.println();

        // 场景3：超过组长与部门经理权限，最终由总经理处理
        System.out.println("========== 测试3：10天请假申请（应由总经理审批） ==========");
        LeaveRequest request3 = new LeaveRequest("王五", 10, "婚假");
        System.out.println("提交请假申请：" + request3);
        teamLeader.processLeaveRequest(request3);
        System.out.println();

        // 场景4：再次验证链头可重复处理符合权限的请求
        System.out.println("========== 测试4：1天请假申请（应由组长审批） ==========");
        LeaveRequest request4 = new LeaveRequest("赵六", 1, "办理证件");
        System.out.println("提交请假申请：" + request4);
        teamLeader.processLeaveRequest(request4);
        System.out.println();

        // 场景5：较长请假，验证链尾节点的扩展提示信息
        System.out.println("========== 测试5：20天请假申请（应由总经理审批） ==========");
        LeaveRequest request5 = new LeaveRequest("钱七", 20, "产假");
        System.out.println("提交请假申请：" + request5);
        teamLeader.processLeaveRequest(request5);
        System.out.println();

        // 场景6：超出所有节点权限，触发链尾“无法处理”兜底输出
        System.out.println("========== 测试6：35天请假申请（超出所有审批者权限） ==========");
        LeaveRequest request6 = new LeaveRequest("孙八", 35, "长期病假");
        System.out.println("提交请假申请：" + request6);
        teamLeader.processLeaveRequest(request6);
        System.out.println();

        // 场景7：运行时重组责任链，验证流程具备动态可配置能力
        System.out.println("========== 测试7：动态修改责任链 ==========");
        System.out.println("原审批链：组长 -> 部门经理 -> 总经理");
        System.out.println("修改为：组长 -> 总经理（跳过部门经理）");
        teamLeader.setNextApprover(generalManager); // 跳过部门经理

        LeaveRequest request7 = new LeaveRequest("周九", 5, "培训学习");
        System.out.println("提交请假申请：" + request7);
        System.out.println("注意：5天请假原本应由部门经理审批，现在直接由总经理审批");
        teamLeader.processLeaveRequest(request7);
        System.out.println();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  模式总结：                                                  ║");
        System.out.println("║  1. Approver是处理器接口，定义了处理请求的方法              ║");
        System.out.println("║  2. AbstractApprover是抽象处理器，实现了公共链式逻辑        ║");
        System.out.println("║  3. TeamLeader、DepartmentManager、GeneralManager是具体处理器║");
        System.out.println("║  4. 每个处理器有自己的审批权限，超出权限则传递给下一个      ║");
        System.out.println("║  5. 客户端只需向链的第一个处理器提交请求，无需知道具体处理者║");
        System.out.println("║  6. 责任链可以动态修改，增加灵活性                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}

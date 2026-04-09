package com.design.mode.behavioral.chainofresponsibility;

/**
 * 抽象审批者类
 *
 * 该类封装责任链中“所有审批者共有的行为”，避免在具体审批者中重复编写模板逻辑。
 * 具体职责包括：
 * 1. 保存审批者基础信息（姓名、审批上限）
 * 2. 保存责任链后继节点引用
 * 3. 提供统一的“权限判断 + 向后传递 + 兜底处理”流程
 * 4. 通过抽象方法将具体审批意见延迟到子类实现
 *
 * 这是一种典型的“模板方法 + 责任链”组合写法：
 * - processLeaveRequest 固定流程骨架
 * - approve 由子类提供差异化实现
 */
public abstract class AbstractApprover implements Approver {
    protected String name;           // 审批者姓名，用于输出审批日志与定位处理节点
    protected int approvalLimit;     // 审批权限上限（单位：天）
    protected Approver nextApprover; // 链中的后继审批者，当前节点无法处理时转交给它

    /**
     * 构造审批者基类。
     *
     * @param name 审批者显示名称
     * @param approvalLimit 当前节点可处理的最大请假天数
     */
    public AbstractApprover(String name, int approvalLimit) {
        this.name = name;
        this.approvalLimit = approvalLimit;
    }

    /**
     * 建立责任链后继关系。
     *
     * 该方法通常由客户端按业务规则进行装配，
     * 例如先装配线性链，后续也可以动态替换节点顺序。
     */
    @Override
    public void setNextApprover(Approver nextApprover) {
        this.nextApprover = nextApprover;
    }

    @Override
    public void processLeaveRequest(LeaveRequest request) {
        // 第一步：判断当前节点是否有权限直接处理
        if (request.getLeaveDays() <= approvalLimit) {
            // 在审批权限内，进入子类具体审批分支
            approve(request);
        } else if (nextApprover != null) {
            // 第二步：权限不足但链未结束，请求继续沿链向后传递
            System.out.println(name + " 无法审批 " + request.getLeaveDays() + " 天请假，转交给上级处理");
            nextApprover.processLeaveRequest(request);
        } else {
            // 第三步：权限不足且无后继节点，触发链尾兜底处理
            System.out.println("请假申请无法处理：没有更高权限的审批者");
            System.out.println("申请详情：" + request);
        }
    }

    /**
     * 具体审批逻辑，由子类实现。
     *
     * 不同角色（组长、经理、总经理）可以在此输出不同审批意见，
     * 也可以附加该角色特有的业务提示。
     *
     * @param request 请假申请
     */
    protected abstract void approve(LeaveRequest request);

    /**
     * 输出统一格式的审批结果主信息。
     *
     * 将通用展示逻辑上提到基类，确保不同审批角色的输出格式保持一致，
     * 子类只需关注补充个性化意见即可。
     *
     * @param request 请假申请
     */
    protected void displayApproval(LeaveRequest request) {
        System.out.println(name + " 审批了 " + request.getEmployeeName() +
                         " 的 " + request.getLeaveDays() + " 天请假申请");
        System.out.println("请假原因：" + request.getReason());
    }
}

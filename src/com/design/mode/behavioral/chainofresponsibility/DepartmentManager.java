package com.design.mode.behavioral.chainofresponsibility;

/**
 * 部门经理审批者
 *
 * 角色定位：
 * - 责任链中的中间审批节点
 * - 承接组长无法处理的中等时长请假申请
 *
 * 审批规则：
 * - 可审批 7 天及以内的请假
 * - 对超过 5 天的申请增加额外交接提醒
 */
public class DepartmentManager extends AbstractApprover {

    /**
     * 创建部门经理审批者。
     *
     * @param name 部门经理姓名（用于审批日志输出）
     */
    public DepartmentManager(String name) {
        super(name, 7); // 部门经理可以审批7天以内的请假
    }

    /**
     * 部门经理审批通过后的反馈内容。
     *
     * @param request 请假申请
     */
    @Override
    protected void approve(LeaveRequest request) {
        displayApproval(request);
        System.out.println("部门经理意见：同意，请做好工作安排");
        if (request.getLeaveDays() > 5) {
            System.out.println("温馨提示：长期请假请提前做好工作交接");
        }
    }
}

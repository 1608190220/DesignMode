package com.design.mode.behavioral.chainofresponsibility;

/**
 * 总经理审批者
 *
 * 角色定位：
 * - 责任链中的高层审批节点（通常是链尾）
 * - 处理跨团队协调成本较高的长期请假申请
 *
 * 审批规则：
 * - 可审批 30 天及以内的请假
 * - 对超过 15 天的申请补充与 HR 协调提示
 */
public class GeneralManager extends AbstractApprover {

    /**
     * 创建总经理审批者。
     *
     * @param name 总经理姓名（用于审批日志输出）
     */
    public GeneralManager(String name) {
        super(name, 30); // 总经理可以审批30天以内的请假
    }

    /**
     * 总经理审批通过后的反馈内容。
     *
     * @param request 请假申请
     */
    @Override
    protected void approve(LeaveRequest request) {
        displayApproval(request);
        System.out.println("总经理意见：同意，祝您假期愉快！");
        if (request.getLeaveDays() > 15) {
            System.out.println("重要提醒：长期请假需与HR部门协调相关事宜");
        }
    }
}

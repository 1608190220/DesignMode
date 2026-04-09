package com.design.mode.behavioral.chainofresponsibility;

/**
 * 组长审批者
 *
 * 角色定位：
 * - 责任链中的第一层审批节点
 * - 主要处理短期请假请求，减少上级审批负担
 *
 * 审批规则：
 * - 可审批 3 天及以内的请假
 * - 超过权限时由基类逻辑自动转交后继节点
 */
public class TeamLeader extends AbstractApprover {

    /**
     * 创建组长审批者。
     *
     * @param name 组长姓名（用于审批日志输出）
     */
    public TeamLeader(String name) {
        super(name, 3); // 组长可以审批3天以内的请假
    }

    /**
     * 组长审批通过后的反馈内容。
     *
     * @param request 请假申请
     */
    @Override
    protected void approve(LeaveRequest request) {
        displayApproval(request);
        System.out.println("组长意见：同意，请合理安排工作交接");
    }
}

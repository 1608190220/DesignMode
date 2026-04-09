package com.design.mode.behavioral.chainofresponsibility;

/**
 * 请假申请实体类
 *
 * 该类是责任链中被传递的“请求对象”，用于承载审批所需的最小信息集合。
 * 设计上采用不可变语义的使用方式（仅构造赋值 + 只读访问），
 * 便于在多个审批节点之间安全传递与打印。
 *
 * 字段说明：
 * - employeeName：申请人姓名
 * - leaveDays：请假时长（单位：天），作为审批权限判断的核心依据
 * - reason：请假事由，供审批者阅读并输出到审批日志
 */
public class LeaveRequest {
    private String employeeName; // 申请人姓名
    private int leaveDays;       // 申请请假的天数
    private String reason;       // 请假原因描述

    /**
     * 构造请假申请对象。
     *
     * @param employeeName 申请人姓名
     * @param leaveDays 请假天数
     * @param reason 请假原因
     */
    public LeaveRequest(String employeeName, int leaveDays, String reason) {
        this.employeeName = employeeName;
        this.leaveDays = leaveDays;
        this.reason = reason;
    }

    /**
     * 获取申请人姓名。
     *
     * @return 申请人姓名
     */
    public String getEmployeeName() {
        return employeeName;
    }

    /**
     * 获取请假天数。
     *
     * @return 请假天数
     */
    public int getLeaveDays() {
        return leaveDays;
    }

    /**
     * 获取请假原因。
     *
     * @return 请假原因文本
     */
    public String getReason() {
        return reason;
    }

    /**
     * 以可读格式输出申请信息，便于控制台日志与调试排查。
     *
     * @return 请假申请字符串表示
     */
    @Override
    public String toString() {
        return "请假申请{" +
                "员工='" + employeeName + '\'' +
                ", 天数=" + leaveDays +
                ", 原因='" + reason + '\'' +
                '}';
    }
}

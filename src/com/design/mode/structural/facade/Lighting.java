package com.design.mode.structural.facade;

/**
 * 灯光子系统（Subsystem）
 * 
 * 外观模式中的子系统角色之一，负责提供灯光控制的相关功能。
 * 
 * 子系统特点：
 * - 提供具体的业务功能实现
 * - 不知道外观的存在，独立工作
 * - 可以被外观调用，也可以被客户端直接调用
 * 
 * 主要功能：
 * - 打开/关闭灯光
 * - 调节灯光亮度
 */
public class Lighting {
    
    /**
     * 调节灯光亮度
     * 
     * @param level 亮度百分比，范围0-100，0表示完全关闭，100表示最亮
     */
    public void dim(int level) {
        System.out.println("灯光：调暗到 " + level + "%");
    }

    /**
     * 打开灯光
     * 
     * 将灯光亮度设置为100%（最亮）
     */
    public void on() {
        System.out.println("灯光：打开");
    }

    /**
     * 关闭灯光
     * 
     * 将灯光亮度设置为0%（完全关闭）
     */
    public void off() {
        System.out.println("灯光：关闭");
    }
}
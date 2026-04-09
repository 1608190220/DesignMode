package com.design.mode.structural.facade;

/**
 * 投影仪子系统（Subsystem）
 * 
 * 外观模式中的子系统角色之一，负责提供投影仪控制的相关功能。
 * 
 * 子系统特点：
 * - 提供具体的业务功能实现
 * - 不知道外观的存在，独立工作
 * - 可以被外观调用，也可以被客户端直接调用
 * 
 * 主要功能：
 * - 打开/关闭投影仪
 * - 切换显示模式（宽屏模式、TV模式）
 */
public class Projector {
    
    /**
     * 打开投影仪
     * 
     * 启动投影仪设备，预热并准备投影
     */
    public void on() {
        System.out.println("投影仪：打开");
    }

    /**
     * 关闭投影仪
     * 
     * 关闭投影仪设备，节省电源
     */
    public void off() {
        System.out.println("投影仪：关闭");
    }

    /**
     * 切换到宽屏模式
     * 
     * 将投影仪设置为宽屏模式（16:9比例），适合观看电影
     */
    public void wideScreenMode() {
        System.out.println("投影仪：切换到宽屏模式");
    }

    /**
     * 切换到TV模式
     * 
     * 将投影仪设置为TV模式（4:3比例），适合观看电视节目
     */
    public void tvMode() {
        System.out.println("投影仪：切换到TV模式");
    }
}
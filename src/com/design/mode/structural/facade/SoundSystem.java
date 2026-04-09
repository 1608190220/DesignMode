package com.design.mode.structural.facade;

/**
 * 音响子系统（Subsystem）
 * 
 * 外观模式中的子系统角色之一，负责提供音响控制的相关功能。
 * 
 * 子系统特点：
 * - 提供具体的业务功能实现
 * - 不知道外观的存在，独立工作
 * - 可以被外观调用，也可以被客户端直接调用
 * 
 * 主要功能：
 * - 打开/关闭音响
 * - 调节音量
 * - 切换音效模式（环绕声、立体声）
 */
public class SoundSystem {
    
    /**
     * 打开音响系统
     * 
     * 启动音响设备，使其进入待机状态
     */
    public void on() {
        System.out.println("音响系统：打开");
    }

    /**
     * 关闭音响系统
     * 
     * 关闭音响设备，节省电源
     */
    public void off() {
        System.out.println("音响系统：关闭");
    }

    /**
     * 设置音量
     * 
     * @param level 音量级别，通常范围0-10，0表示静音，10表示最大音量
     */
    public void setVolume(int level) {
        System.out.println("音响系统：设置音量到 " + level);
    }

    /**
     * 设置环绕声模式
     * 
     * 将音响设置为环绕声模式，适合观看电影，提供沉浸式音效体验
     */
    public void setSurroundSound() {
        System.out.println("音响系统：设置环绕声");
    }

    /**
     * 设置立体声模式
     * 
     * 将音响设置为立体声模式，适合听音乐，提供清晰的左右声道分离
     */
    public void setStereoSound() {
        System.out.println("音响系统：设置立体声");
    }
}
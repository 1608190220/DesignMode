package com.design.mode.structural.facade;

/**
 * DVD播放器子系统（Subsystem）
 * 
 * 外观模式中的子系统角色之一，负责提供DVD播放的相关功能。
 * 
 * 子系统特点：
 * - 提供具体的业务功能实现
 * - 不知道外观的存在，独立工作
 * - 可以被外观调用，也可以被客户端直接调用
 * 
 * 主要功能：
 * - 打开/关闭DVD播放器
 * - 播放/停止电影
 * - 弹出光盘
 */
public class DVDPlayer {
    
    /**
     * 打开DVD播放器
     * 
     * 启动DVD播放器设备，使其进入待机状态
     */
    public void on() {
        System.out.println("DVD播放器：打开");
    }

    /**
     * 关闭DVD播放器
     * 
     * 关闭DVD播放器设备，节省电源
     */
    public void off() {
        System.out.println("DVD播放器：关闭");
    }

    /**
     * 播放电影
     * 
     * @param movie 电影名称，如"阿凡达"
     */
    public void play(String movie) {
        System.out.println("DVD播放器：播放电影《" + movie + "》");
    }

    /**
     * 停止播放
     * 
     * 停止当前正在播放的内容
     */
    public void stop() {
        System.out.println("DVD播放器：停止");
    }

    /**
     * 弹出光盘
     * 
     * 弹出DVD播放器中的光盘
     */
    public void eject() {
        System.out.println("DVD播放器：弹出光盘");
    }
}
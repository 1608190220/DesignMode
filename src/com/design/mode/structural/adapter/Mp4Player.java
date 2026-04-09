package com.design.mode.structural.adapter;

/**
 * MP4播放器类（ConcreteAdaptee）- 具体被适配者
 * 
 * 适配器模式中的具体被适配者，实现了 AdvancedMediaPlayer 接口，专门用于播放MP4格式的文件。
 * 
 * 具体被适配者特点：
 * - 实现 Adaptee 接口（AdvancedMediaPlayer）
 * - 提供具体的业务逻辑实现
 * - 只支持特定的功能（MP4格式）
 * - 与目标接口（MediaPlayer）不兼容
 * 
 * 在本示例中：
 * - Mp4Player 是 MP4 格式的具体被适配者
 * - VlcPlayer 是 VLC 格式的具体被适配者
 * - 它们各自只支持自己的格式
 */
public class Mp4Player implements AdvancedMediaPlayer {
    
    /**
     * 播放VLC格式文件
     * 
     * Mp4Player 不支持 VLC 格式，所以这个方法是空实现。
     * 这是接口隔离原则的体现：类不应该依赖它不需要的接口。
     * 
     * @param fileName VLC 文件名（未使用）
     */
    @Override
    public void playVlc(String fileName) {
        // 不做任何操作，MP4播放器不支持VLC格式
    }
    
    /**
     * 播放MP4格式文件
     * 
     * 这是 Mp4Player 的核心方法，专门用于播放 MP4 格式的文件。
     * 这是它支持的格式，所以提供了完整的实现。
     * 
     * @param fileName 要播放的 MP4 文件名
     */
    @Override
    public void playMp4(String fileName) {
        System.out.println("MP4播放器正在播放文件: " + fileName);
    }
}

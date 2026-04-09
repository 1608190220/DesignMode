package com.design.mode.structural.adapter;

/**
 * 高级媒体播放器接口（Adaptee）- 被适配者
 * 
 * 适配器模式中的被适配者接口，定义了现有的、需要被适配的接口。
 * 这个接口与目标接口（MediaPlayer）不兼容，需要通过适配器进行适配。
 * 
 * 被适配者特点：
 * - 定义了现有的接口
 * - 与目标接口不兼容
 * - 有具体的实现类（VlcPlayer、Mp4Player）
 * - 适配器会包装这个接口的实现
 * 
 * 在本示例中：
 * - AdvancedMediaPlayer 是被适配者接口
 * - 它有两个具体实现：VlcPlayer 和 Mp4Player
 * - 这个接口与 MediaPlayer 不兼容
 * - 需要通过 MediaAdapter 进行适配
 */
public interface AdvancedMediaPlayer {
    
    /**
     * 播放VLC格式文件
     * 
     * 这是被适配者接口的方法之一，用于播放VLC格式的文件。
     * 这个方法与目标接口的 play() 方法不兼容。
     * 
     * @param fileName 要播放的VLC文件名
     */
    void playVlc(String fileName);
    
    /**
     * 播放MP4格式文件
     * 
     * 这是被适配者接口的方法之一，用于播放MP4格式的文件。
     * 这个方法与目标接口的 play() 方法不兼容。
     * 
     * @param fileName 要播放的MP4文件名
     */
    void playMp4(String fileName);
}

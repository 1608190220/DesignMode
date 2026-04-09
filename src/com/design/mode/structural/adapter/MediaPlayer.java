package com.design.mode.structural.adapter;

/**
 * 目标接口（Target）
 * 
 * 适配器模式中的目标接口角色，定义了客户端期望使用的接口。
 * 这是客户端直接调用的接口，定义了播放媒体文件的标准方法。
 * 
 * 适配器模式的核心目标：
 * - 让不兼容的接口能够协同工作
 * - 将一个类的接口转换成客户希望的另一个接口
 * - 使原本由于接口不兼容而不能一起工作的那些类可以一起工作
 * 
 * 目标接口特点：
 * - 定义客户端期望的接口
 * - 是适配器需要实现的接口
 * - 客户端通过这个接口与适配器交互
 * 
 * 在本示例中：
 * - MediaPlayer 是目标接口
 * - 客户端（AudioPlayer）使用这个接口
 * - 适配器（MediaAdapter）实现这个接口
 */
public interface MediaPlayer {

    /**
     * 播放音频文件
     * 
     * 这是目标接口的核心方法，客户端通过调用这个方法来播放媒体文件。
     * 适配器需要实现这个方法，将调用转换为对被适配接口的调用。
     * 
     * @param audioType 音频文件类型，如"mp3"、"mp4"、"vlc"等
     * @param fileName 要播放的文件名
     */
    void play(String audioType, String fileName);
}

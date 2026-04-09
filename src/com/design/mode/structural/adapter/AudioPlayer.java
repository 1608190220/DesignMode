package com.design.mode.structural.adapter;

/**
 * 音频播放器类（Client）- 客户端
 * 
 * 适配器模式中的客户端角色，实现了目标接口（MediaPlayer），是直接与客户端交互的类。
 * 这个类本身支持播放MP3格式，对于不支持的格式（MP4、VLC），使用适配器来适配。
 * 
 * 客户端特点：
 * - 实现目标接口（MediaPlayer）
 * - 是客户端直接调用的类
 * - 对于支持的功能，直接实现
 * - 对于不支持的功能，使用适配器
 * 
 * 在本示例中：
 * - AudioPlayer 是客户端类
 * - 它本身支持播放MP3格式
 * - 对于MP4和VLC格式，使用 MediaAdapter 来适配
 */
public class AudioPlayer implements MediaPlayer {
    
    /**
     * 媒体适配器引用
     * 
     * 用于在需要时创建和使用适配器。
     * 对于不支持的格式，会创建适配器来处理。
     */
    MediaAdapter mediaAdapter;
    
    /**
     * 播放音频文件
     * 
     * 这是客户端的核心方法，根据不同的音频类型进行处理：
     * 1. 对于MP3格式，直接播放（本身支持）
     * 2. 对于MP4和VLC格式，使用适配器来适配
     * 3. 对于其他格式，提示不支持
     * 
     * 这展示了适配器模式的使用场景：当需要扩展功能时，
     * 不修改原有代码，而是使用适配器来适配新功能。
     * 
     * @param audioType 音频类型，如"mp3"、"mp4"、"vlc"
     * @param fileName 要播放的文件名
     */
    @Override
    public void play(String audioType, String fileName) {
        // 支持MP3格式的音频文件 - 直接播放，不需要适配器
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("MP3播放器正在播放文件: " + fileName);
        }
        // 对于MP4和VLC格式，使用对象适配器 - 这是适配器模式的应用
        else if (audioType.equalsIgnoreCase("mp4") || audioType.equalsIgnoreCase("vlc")) {
            mediaAdapter = new MediaAdapter(audioType);
            mediaAdapter.play(audioType, fileName);
        }
        // 不支持的格式 - 提示用户
        else {
            System.out.println("不支持 " + audioType + " 格式的文件，无法播放: " + fileName);
        }
    }
}

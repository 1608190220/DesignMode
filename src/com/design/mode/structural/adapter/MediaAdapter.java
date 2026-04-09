package com.design.mode.structural.adapter;

/**
 * 媒体适配器类（Adapter）- 适配器
 * 
 * 适配器模式中的适配器角色，这是适配器模式的核心类。
 * 实现了目标接口（MediaPlayer），并在内部包装了一个被适配对象（AdvancedMediaPlayer）。
 * 
 * 适配器模式的类型：
 * - 本示例使用的是对象适配器（Object Adapter）
 * - 通过组合而非继承来适配
 * - 持有被适配对象的引用
 * 
 * 适配器的核心职责：
 * - 实现目标接口（MediaPlayer）
 * - 持有被适配对象的引用（AdvancedMediaPlayer）
 * - 将目标接口的调用转换为对被适配接口的调用
 * 
 * 在本示例中：
 * - MediaAdapter 实现了 MediaPlayer 接口
 * - 持有 AdvancedMediaPlayer 的引用
 * - 将 play() 调用转换为 playVlc() 或 playMp4() 调用
 */
public class MediaAdapter implements MediaPlayer {
    
    /**
     * 持有一个高级媒体播放器的引用，这是被适配的对象
     * 
     * 这是对象适配器的关键：通过组合而非继承来持有被适配对象。
     * 使用组合的好处：
     * - 可以适配 Adaptee 的所有子类
     * - 更加灵活
     * - 符合组合优于继承的原则
     */
    AdvancedMediaPlayer advancedMediaPlayer;
    
    /**
     * 构造方法，根据音频类型创建对应的高级媒体播放器
     * 
     * 根据传入的音频类型，创建对应的具体被适配者。
     * 这是一个简单的工厂方法，用于选择合适的被适配者。
     * 
     * @param audioType 音频类型，如"vlc"或"mp4"
     */
    public MediaAdapter(String audioType) {
        if (audioType.equalsIgnoreCase("vlc")) {
            // 如果是vlc格式，创建VLC播放器
            advancedMediaPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            // 如果是mp4格式，创建MP4播放器
            advancedMediaPlayer = new Mp4Player();
        }
    }
    
    /**
     * 根据文件类型调用对应的高级媒体播放器的播放方法
     * 
     * 这是适配器的核心方法，实现了接口转换。
     * 将目标接口的 play(audioType, fileName) 调用，
     * 转换为对被适配接口的 playVlc(fileName) 或 playMp4(fileName) 调用。
     * 
     * 这是适配器模式的核心：接口转换。
     * 
     * @param audioType 音频类型，如"vlc"或"mp4"
     * @param fileName 要播放的文件名
     */
    @Override
    public void play(String audioType, String fileName) {
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer.playVlc(fileName);
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer.playMp4(fileName);
        }
    }
}

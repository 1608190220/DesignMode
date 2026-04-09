package com.design.mode.structural.adapter;

/**
 * 适配器模式测试类
 * 演示适配器模式的使用
 * 
 * 优点：
 * - 复用性和扩展性好，符合开闭原则
 * - 可以使两个没有关系的类一起工作
 * 
 * 缺点：
 * - 一次只能适配一个类，不能适配多个类的不同接口
 * 
 * 四个应用场景：
 * 1. 系统迁移：当用新系统替换旧系统时，使用适配器保持兼容性
 * 2. 第三方集成：当集成具有不同接口的第三方库时
 * 3. 接口统一：当统一多个相似但不同的接口时
 * 4. 遗留代码复用：当复用遗留代码而不修改它时
 */
public class AdapterTest {
    
    /**
     * 主测试方法
     * 
     * 通过多个测试用例演示适配器模式的工作原理和优势：
     * 1. 测试播放MP3格式（直接支持，不需要适配器）
     * 2. 测试播放MP4格式（通过适配器适配）
     * 3. 测试播放VLC格式（通过适配器适配）
     * 4. 测试不支持的格式
     * 
     * 测试用例设计：
     * - MP3：AudioPlayer 本身支持，直接播放
     * - MP4：使用 MediaAdapter 适配到 Mp4Player
     * - VLC：使用 MediaAdapter 适配到 VlcPlayer
     * - AVI：不支持的格式，提示错误
     * 
     * 适配器模式的优势展示：
     * - 不需要修改 AudioPlayer 的原有代码
     * - 通过适配器添加了对新格式的支持
     * - 符合开闭原则：对扩展开放，对修改关闭
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("                  适配器模式测试                            ");
        System.out.println("==================================================");
        
        // 创建音频播放器对象（客户端）
        // 这是客户端直接使用的类
        AudioPlayer audioPlayer = new AudioPlayer();
        
        // 测试1：播放MP3格式
        // AudioPlayer 本身支持MP3格式，不需要适配器
        System.out.println("\n========== 测试1：播放MP3格式 ==========");
        audioPlayer.play("mp3", "lovely_day.mp3");
        
        // 测试2：播放MP4格式（通过适配器）
        // AudioPlayer 不支持MP4格式，使用 MediaAdapter 适配
        System.out.println("\n========== 测试2：播放MP4格式（通过适配器） ==========");
        audioPlayer.play("mp4", "movie_trailer.mp4");
        
        // 测试3：播放VLC格式（通过适配器）
        // AudioPlayer 不支持VLC格式，使用 MediaAdapter 适配
        System.out.println("\n========== 测试3：播放VLC格式（通过适配器） ==========");
        audioPlayer.play("vlc", "concert.vlc");
        
        // 测试4：测试不支持的格式
        // AudioPlayer 不支持AVI格式，提示不支持
        System.out.println("\n========== 测试4：测试不支持的格式 ==========");
        audioPlayer.play("avi", "sample.avi");
        
        // 模式总结
        System.out.println("\n==================================================");
        System.out.println("                  模式总结                            ");
        System.out.println("==================================================");
        System.out.println("  1. MediaPlayer是目标接口，AudioPlayer实现了这个接口");
        System.out.println("  2. AdvancedMediaPlayer是被适配接口，有VlcPlayer和");
        System.out.println("     Mp4Player作为实现类");
        System.out.println("  3. MediaAdapter是适配器，实现了MediaPlayer接口并在内部");
        System.out.println("     包装了一个AdvancedMediaPlayer对象");
        System.out.println("  4. 客户端只需要调用AudioPlayer的play方法，不需要关心内部适配");
        System.out.println("     逻辑，适配器模式使不兼容的接口一起工作");
        System.out.println("==================================================");
    }
}

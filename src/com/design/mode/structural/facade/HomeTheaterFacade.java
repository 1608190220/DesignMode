package com.design.mode.structural.facade;

/**
 * 家庭影院外观类（Facade）
 * 
 * 外观模式中的外观角色，为复杂的家庭影院子系统提供一个统一的高层接口。
 * 
 * 外观模式的核心思想：
 * - 为子系统中的一组接口提供一个统一的接口
 * - 定义一个高层接口，使得子系统更加容易使用
 * - 符合迪米特法则（最少知道原则）
 * 
 * 主要职责：
 * 1. 封装子系统的复杂性
 * 2. 提供简化的使用接口
 * 3. 协调多个子系统的工作
 * 
 * 设计特点：
 * - 持有所有子系统对象的引用
 * - 提供预设的场景模式（看电影、听音乐、看电视等）
 * - 客户端只需调用外观方法，无需了解子系统内部细节
 */
public class HomeTheaterFacade {
    
    /**
     * 投影仪子系统引用
     */
    private Projector projector;
    
    /**
     * 音响子系统引用
     */
    private SoundSystem soundSystem;
    
    /**
     * 灯光子系统引用
     */
    private Lighting lighting;
    
    /**
     * DVD播放器子系统引用
     */
    private DVDPlayer dvdPlayer;

    /**
     * 构造函数，初始化家庭影院外观
     * 
     * 通过依赖注入的方式接收所有子系统对象
     * 这样可以灵活地配置不同的子系统实现
     * 
     * @param projector 投影仪子系统对象
     * @param soundSystem 音响子系统对象
     * @param lighting 灯光子系统对象
     * @param dvdPlayer DVD播放器子系统对象
     */
    public HomeTheaterFacade(Projector projector, SoundSystem soundSystem,
                             Lighting lighting, DVDPlayer dvdPlayer) {
        this.projector = projector;
        this.soundSystem = soundSystem;
        this.lighting = lighting;
        this.dvdPlayer = dvdPlayer;
    }

    /**
     * 看电影模式：一键准备所有设备
     * 
     * 这是外观模式的典型应用，将多个子系统的操作封装成一个简单的方法。
     * 客户端只需调用这一个方法，无需了解：
     * - 需要按什么顺序操作设备
     * - 每个设备需要设置哪些参数
     * - 设备之间的依赖关系
     * 
     * 执行步骤：
     * 1. 调暗灯光（10%）营造影院氛围
     * 2. 打开投影仪并设置为宽屏模式
     * 3. 打开音响并设置为环绕声模式，音量调至8
     * 4. 打开DVD播放器并开始播放电影
     * 
     * @param movie 电影名称
     */
    public void watchMovie(String movie) {
        System.out.println("\n准备观看电影《" + movie + "》...");
        lighting.dim(10);           // 调暗灯光
        projector.on();             // 打开投影仪
        projector.wideScreenMode(); // 设置宽屏模式
        soundSystem.on();           // 打开音响
        soundSystem.setSurroundSound(); // 设置环绕声
        soundSystem.setVolume(8);   // 设置音量
        dvdPlayer.on();             // 打开DVD播放器
        dvdPlayer.play(movie);      // 播放电影
        System.out.println("电影开始！\n");
    }

    /**
     * 结束电影模式：一键关闭所有设备
     * 
     * 与watchMovie()方法配合使用，提供完整的观影体验。
     * 按照合理的顺序关闭所有设备，避免设备损坏。
     * 
     * 执行步骤：
     * 1. 停止DVD播放并弹出光盘
     * 2. 关闭DVD播放器
     * 3. 关闭投影仪
     * 4. 关闭音响
     * 5. 打开灯光，恢复正常照明
     */
    public void endMovie() {
        System.out.println("\n结束观影...");
        dvdPlayer.stop();           // 停止播放
        dvdPlayer.eject();          // 弹出光盘
        dvdPlayer.off();            // 关闭DVD
        projector.off();            // 关闭投影仪
        soundSystem.off();          // 关闭音响
        lighting.on();              // 打开灯光
        System.out.println("所有设备已关闭，观影结束！\n");
    }

    /**
     * 听音乐模式
     * 
     * 提供适合听音乐的设备配置。
     * 
     * 执行步骤：
     * 1. 稍微调暗灯光（30%）营造放松氛围
     * 2. 打开音响并设置为立体声模式，音量调至6
     * 3. 打开DVD播放器并开始播放音乐
     * 
     * @param music 音乐名称
     */
    public void listenToMusic(String music) {
        System.out.println("\n准备听音乐《" + music + "》...");
        lighting.dim(30);           // 稍微调暗灯光
        soundSystem.on();           // 打开音响
        soundSystem.setStereoSound(); // 设置立体声
        soundSystem.setVolume(6);   // 设置音量
        dvdPlayer.on();             // 打开DVD播放器
        dvdPlayer.play(music);      // 播放音乐
        System.out.println("音乐播放开始！\n");
    }

    /**
     * 电视模式
     * 
     * 提供适合看电视的设备配置。
     * 
     * 执行步骤：
     * 1. 打开灯光，保持明亮
     * 2. 打开投影仪并设置为TV模式
     * 3. 打开音响，音量调至5
     */
    public void watchTV() {
        System.out.println("\n准备看电视...");
        lighting.on();              // 打开灯光
        projector.on();             // 打开投影仪
        projector.tvMode();         // 设置TV模式
        soundSystem.on();           // 打开音响
        soundSystem.setVolume(5);   // 设置音量
        System.out.println("电视模式已就绪！\n");
    }
}
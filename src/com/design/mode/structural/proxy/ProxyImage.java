package com.design.mode.structural.proxy;

/**
 * 图片代理类
 * 控制对真实图片的访问，实现虚拟代理和缓存功能
 * 
 * 代理模式的核心类，它包含以下功能：
 * 1. 延迟加载（虚拟代理）：只有在第一次显示图片时才加载真实图片
 * 2. 缓存机制：图片加载后会被缓存，后续访问直接使用缓存
 * 3. 预加载功能：可以提前加载图片而不显示
 * 4. 状态跟踪：记录图片是否已加载的状态
 * 
 * 这是代理模式的典型应用：虚拟代理
 */
public class ProxyImage implements Image {
    
    /**
     * 图片文件名
     * 用于标识图片，也是传递给真实对象的参数
     */
    private String fileName;
    
    /**
     * 对真实对象的引用
     * 懒加载：只有在需要时才创建真实对象
     */
    private RealImage realImage;
    
    /**
     * 图片加载状态标志
     * true表示图片已加载完成，false表示尚未加载
     * 用于实现缓存机制，避免重复加载
     */
    private boolean isLoaded = false;

    /**
     * 构造方法
     * 创建代理对象时不会立即加载真实图片
     * 这就是延迟加载的体现
     * 
     * @param fileName 图片文件名
     */
    public ProxyImage(String fileName) {
        this.fileName = fileName;
        // 注意：这里没有创建RealImage对象，实现了延迟加载
    }

    /**
     * 显示图片
     * 这是代理模式的核心方法，包含了代理的所有逻辑：
     * 1. 检查图片是否已加载
     * 2. 如果未加载，先显示占位图，然后加载真实图片
     * 3. 如果已加载，直接从缓存显示
     * 
     * 通过这种方式，客户端无需知道代理的存在，可以透明使用
     */
    @Override
    public void display() {
        if (!isLoaded) {
            // 第一次显示时加载真实图片
            System.out.println("代理: 正在准备显示图片 " + fileName);
            System.out.println("代理: 显示占位图...");

            // 在后台线程中加载真实图片
            // 这里模拟异步加载，实际应用中可以使用真正的异步处理
            Thread loadingThread = new Thread(() -> {
                realImage = new RealImage(fileName);
                isLoaded = true;
                System.out.println("代理: 真实图片已加载完成，现在显示真实图片:");
                realImage.display();
            });
            loadingThread.start();

            // 等待加载完成（实际应用中可能是异步的）
            // 这里为了演示方便，使用join()等待线程完成
            try {
                loadingThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else {
            // 已经加载过，直接显示缓存的图片
            // 这就是缓存机制，避免了重复的耗时操作
            System.out.println("代理: 图片已缓存，直接显示:");
            realImage.display();
        }
    }

    /**
     * 获取文件名
     * @return 图片的文件名
     */
    @Override
    public String getFileName() {
        return fileName;
    }

    /**
     * 预加载图片（不显示）
     * 这个方法允许提前加载图片而不显示
     * 可以在用户空闲时预加载，提升用户体验
     * 这也是代理模式的一个应用场景：智能引用代理
     */
    public void preload() {
        if (!isLoaded) {
            System.out.println("代理: 预加载图片 " + fileName);
            realImage = new RealImage(fileName);
            isLoaded = true;
            System.out.println("代理: 预加载完成");
        }
    }

    /**
     * 检查图片是否已加载
     * @return true表示图片已加载，false表示尚未加载
     * 这是代理提供的额外功能，用于查询对象状态
     */
    public boolean isLoaded() {
        return isLoaded;
    }
}
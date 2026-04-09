package com.design.mode.structural.proxy;

/**
 * 真实图片类
 * 实现 Image 接口，负责实际的图片加载和显示
 * 
 * 在代理模式中，这是真实的业务对象
 * 它包含了核心的业务逻辑（加载和显示图片）
 * 通常这个操作比较耗时，所以需要代理来控制访问
 */
public class RealImage implements Image {
    
    /**
     * 图片文件名
     * 用于标识和加载具体的图片
     */
    private String fileName;
    
    /**
     * 构造方法
     * 创建真实图片对象时会立即从磁盘加载图片
     * 这是一个耗时操作，也是代理模式要解决的问题
     * 
     * @param fileName 图片文件名
     */
    public RealImage(String fileName) {
        this.fileName = fileName;
        loadFromDisk();
    }
    
    /**
     * 从磁盘加载图片
     * 模拟耗时的IO操作
     * 在真实场景中，这里可能会从网络、数据库或文件系统加载图片
     */
    private void loadFromDisk() {
        System.out.println("真实图片: 正在从磁盘加载图片 " + fileName);
        // 模拟加载延迟，让用户能看到加载过程
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("真实图片: 图片 " + fileName + " 加载完成");
    }
    
    /**
     * 显示图片
     * 这是真实对象的核心业务方法
     * 在代理模式中，代理最终会调用这个方法来完成实际的显示
     */
    @Override
    public void display() {
        System.out.println("真实图片: 显示图片 " + fileName);
    }
    
    /**
     * 获取文件名
     * @return 图片的文件名
     */
    @Override
    public String getFileName() {
        return fileName;
    }
}
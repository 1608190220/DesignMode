package com.design.mode.structural.proxy;

/**
 * 图片接口
 * 定义了图片的基本行为，包括显示图片和获取文件名
 * 代理模式的核心：代理和真实对象都实现相同的接口，让客户端可以透明地使用
 */
public interface Image {
    
    /**
     * 显示图片
     * 这是核心方法，无论是真实对象还是代理对象都需要实现
     * 代理对象可以在这个方法前后添加额外逻辑
     */
    void display();
    
    /**
     * 获取文件名
     * @return 图片的文件名
     */
    String getFileName();
}
package com.design.mode.structural.composite;

/**
 * 文件类（Leaf）- 叶子节点
 * 
 * 组合模式中的叶子节点角色，表示组合中的叶子节点对象，没有子节点。
 * 叶子节点是树形结构的最底层，不包含其他组件。
 * 
 * 叶子节点特点：
 * - 没有子节点
 * - 实现 Component 接口的所有方法
 * - 定义组合中原始对象的行为
 * - 不提供管理子组件的方法（add、remove等）
 * 
 * 设计原则：
 * - 单一职责原则：只负责表示文件，不负责管理子组件
 * - 接口隔离原则：不实现不需要的子组件管理方法
 * 
 * 在组合模式中的作用：
 * - 作为树形结构的叶子节点
 * - 提供基本的业务功能
 * - 被复合节点（Directory）包含和管理
 */
public class File implements FileSystemComponent {
    
    /**
     * 文件名
     */
    private String name;
    
    /**
     * 文件大小，单位为字节
     */
    private long size;
    
    /**
     * 构造方法
     * 
     * @param name 文件名
     * @param size 文件大小，单位为字节
     */
    public File(String name, long size) {
        this.name = name;
        this.size = size;
    }
    
    /**
     * 显示文件信息
     * 
     * 叶子节点的实现：直接显示文件本身的信息，不需要递归。
     * 根据深度参数进行缩进，展示树形结构。
     * 
     * @param depth 深度，用于缩进显示，表示当前文件在树形结构中的层级
     */
    @Override
    public void display(int depth) {
        // 根据深度进行缩进，每一层缩进两个空格
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        // 显示文件名和大小（转换为KB显示）
        System.out.println("文件: " + name + " (" + (size / 1024) + " KB)");
    }
    
    /**
     * 获取文件大小
     * 
     * 叶子节点的实现：直接返回文件本身的大小。
     * 
     * @return 文件大小，单位为字节
     */
    @Override
    public long getSize() {
        return size;
    }
    
    /**
     * 处理文件
     * 
     * 叶子节点的实现：直接处理文件本身，不需要递归。
     * 这是组合模式一致性处理的体现。
     */
    @Override
    public void process() {
        System.out.println("处理文件: " + name);
    }
}

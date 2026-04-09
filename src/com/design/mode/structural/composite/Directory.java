package com.design.mode.structural.composite;

import java.util.ArrayList;
import java.util.List;

/**
 * 目录类（Composite）- 复合组件
 * 
 * 组合模式中的复合组件角色，表示组合中的分支节点，有子节点。
 * 复合节点可以包含其他组件（可以是叶子节点，也可以是其他复合节点）。
 * 
 * 复合节点特点：
 * - 有子节点（子组件）
 * - 实现 Component 接口的所有方法
 * - 提供管理子组件的方法（add、remove等）
 * - 存储子组件
 * - 将请求委托给子组件处理
 * 
 * 设计原则：
 * - 组合优于继承：通过组合而非继承来管理子组件
 * - 开闭原则：可以方便地添加新的子组件类型
 * 
 * 在组合模式中的作用：
 * - 作为树形结构的分支节点
 * - 管理和存储子组件
 * - 递归处理子组件
 */
public class Directory implements FileSystemComponent {
    
    /**
     * 目录名
     */
    private String name;
    
    /**
     * 子组件列表
     * 使用 List 存储子组件，可以是 File 或 Directory
     * 这是组合模式的关键：通过组合来管理子组件
     */
    private List<FileSystemComponent> components;
    
    /**
     * 构造方法
     * 
     * @param name 目录名
     */
    public Directory(String name) {
        this.name = name;
        this.components = new ArrayList<>();
    }
    
    /**
     * 添加组件
     * 
     * 这是复合组件特有的方法，用于管理子组件。
     * 可以添加 File 或 Directory 类型的组件。
     * 
     * @param component 文件系统组件，可以是 File 或 Directory
     */
    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }
    
    /**
     * 移除组件
     * 
     * 这是复合组件特有的方法，用于管理子组件。
     * 
     * @param component 要移除的文件系统组件
     */
    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }
    
    /**
     * 显示目录信息
     * 
     * 复合节点的实现：先显示目录本身的信息，再递归显示所有子组件。
     * 这是组合模式的核心：递归处理子组件。
     * 
     * @param depth 深度，用于缩进显示，表示当前目录在树形结构中的层级
     */
    @Override
    public void display(int depth) {
        // 根据深度进行缩进，每一层缩进两个空格
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("目录: " + name);
        
        // 递归显示所有子组件，深度加1
        for (FileSystemComponent component : components) {
            component.display(depth + 1);
        }
    }
    
    /**
     * 获取目录大小
     * 
     * 复合节点的实现：递归计算所有子组件的大小之和。
     * 这是组合模式的另一个核心：递归聚合子组件的结果。
     * 
     * @return 目录大小，单位为字节（所有子组件大小之和）
     */
    @Override
    public long getSize() {
        long totalSize = 0;
        // 递归累加所有子组件的大小
        for (FileSystemComponent component : components) {
            totalSize += component.getSize();
        }
        return totalSize;
    }
    
    /**
     * 处理目录
     * 
     * 复合节点的实现：先处理目录本身，再递归处理所有子组件。
     * 这是组合模式一致性处理的体现。
     */
    @Override
    public void process() {
        System.out.println("处理目录: " + name);
        // 递归处理所有子组件
        for (FileSystemComponent component : components) {
            component.process();
        }
    }
}

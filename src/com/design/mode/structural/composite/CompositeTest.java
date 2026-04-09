package com.design.mode.structural.composite;

/**
 * 组合模式测试类
 * 演示组合模式的使用
 * 
 * 一、模式概念
 * 将对象组合成树形结构以表示"部分-整体"的层次结构。组合模式使得用户对单个对象和组合对象的使用具有一致性。
 * 
 * 二、模式结构
 * 1. 组件（Component）: 定义组合中所有对象的通用接口，这里是FileSystemComponent
 * 2. 叶子（Leaf）: 表示组合中的叶子节点对象，没有子节点，这里是File
 * 3. 复合组件（Composite）: 表示组合中的分支节点，有子节点，这里是Directory
 * 4. 客户端（Client）: 通过组件接口操作组合中的对象
 * 
 * 三、应用场景
 * 1. 需要表示对象的"部分-整体"层次结构（如树形菜单、文件系统）
 * 2. 希望用户忽略组合对象与单个对象的不同，统一地使用组合结构中的所有对象
 * 3. 系统需要处理树形结构的数据
 * 
 * 四、优缺点
 * 优点：
 * - 统一了组合对象和叶子对象的处理方式
 * - 符合开闭原则，添加新的组件类型容易
 * 
 * 缺点：
 * - 设计较复杂，需要区分出组合和叶子节点的不同点
 * - 不容易限制组合中的组件类型
 */
public class CompositeTest {
    
    /**
     * 主测试方法
     * 
     * 通过多个测试用例演示组合模式的工作原理和优势：
     * 1. 创建文件和目录，构建树形结构
     * 2. 显示目录结构，展示递归显示
     * 3. 计算目录大小，展示递归聚合
     * 4. 统一处理所有组件，展示一致性处理
     * 
     * 测试用例设计：
     * - 创建一个包含文件和目录的树形文件系统
     * - 根目录包含文档目录和媒体目录
     * - 文档目录包含简历和文档
     * - 媒体目录包含照片、音乐和视频
     * - 演示组合模式的各种操作
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("                  组合模式测试                            ");
        System.out.println("==================================================");
        
        // 创建叶子节点（File）
        // 这些是树形结构的最底层，没有子节点
        File file1 = new File("简历.pdf", 1024 * 1024); // 1MB
        File file2 = new File("照片.jpg", 2 * 1024 * 1024); // 2MB
        File file3 = new File("文档.docx", 512 * 1024); // 512KB
        File file4 = new File("音乐.mp3", 3 * 1024 * 1024); // 3MB
        File file5 = new File("视频.mp4", 10 * 1024 * 1024); // 10MB
        
        // 创建复合节点（Directory）
        // 这些是树形结构的分支节点，可以包含子节点
        Directory documentsDir = new Directory("文档");
        Directory mediaDir = new Directory("媒体");
        Directory rootDir = new Directory("根目录");
        
        // 构建树形目录结构
        // 使用 addComponent() 方法将子组件添加到父组件中
        documentsDir.addComponent(file1);
        documentsDir.addComponent(file3);
        
        mediaDir.addComponent(file2);
        mediaDir.addComponent(file4);
        mediaDir.addComponent(file5);
        
        rootDir.addComponent(documentsDir);
        rootDir.addComponent(mediaDir);
        
        // 测试1：显示目录结构
        // 展示组合模式的递归显示功能
        System.out.println("\n========== 测试1：显示目录结构 ==========");
        rootDir.display(0);
        
        // 测试2：显示整个文件系统
        // 再次展示树形结构，与测试1重复，用于对比
        System.out.println("\n========== 测试2：显示整个文件系统 ==========");
        System.out.println("文件系统结构：");
        rootDir.display(0);
        
        // 测试3：计算目录大小
        // 展示组合模式的递归聚合功能
        // 客户端无需知道是单个文件还是目录，统一调用 getSize()
        System.out.println("\n========== 测试3：计算目录大小 ==========");
        System.out.println("根目录总大小: " + rootDir.getSize() / 1024 + " KB");
        System.out.println("文档目录大小: " + documentsDir.getSize() / 1024 + " KB");
        System.out.println("媒体目录大小: " + mediaDir.getSize() / 1024 + " KB");
        
        // 测试4：统一处理所有文件
        // 展示组合模式的一致性处理
        // 客户端无需区分是文件还是目录，统一调用 process()
        System.out.println("\n========== 测试4：统一处理所有文件 ==========");
        System.out.println("所有文件系统组件:");
        rootDir.process();
        
        // 模式总结
        System.out.println("\n==================================================");
        System.out.println("                  模式总结                            ");
        System.out.println("==================================================");
        System.out.println("  1. FileSystemComponent是组件接口，定义了通用方法");
        System.out.println("  2. File是叶子节点，没有子节点，实现具体方法");
        System.out.println("  3. Directory是复合节点，有子节点，实现具体方法并管理子组件");
        System.out.println("  4. 客户端可以统一处理File和Directory，无需区分是单个文件还是目录");
        System.out.println("  5. 组合模式使得树形结构的操作更加简单和统一");
        System.out.println("==================================================");
    }
}

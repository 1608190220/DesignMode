package com.design.mode.structural.bridge;

/**
 * 桥接模式测试类
 * 演示桥接模式的使用
 * 
 * 优点：
 * - 分离抽象和实现，使它们可以独立变化
 * - 提高系统的可扩展性
 * - 符合开闭原则
 * 
 * 缺点：
 * - 需要正确识别系统中两个独立变化的维度
 */
public class BridgeTest {
    
    /**
     * 主测试方法
     * 
     * 通过多个测试用例演示桥接模式的工作原理和优势：
     * 1. 创建实现部分（颜色）：Red、Blue
     * 2. 创建抽象部分（形状）并桥接实现部分：Circle、Square
     * 3. 测试不同的形状和颜色组合
     * 
     * 测试用例设计：
     * - 红色圆形：Circle + Red
     * - 蓝色圆形：Circle + Blue
     * - 红色正方形：Square + Red
     * - 蓝色正方形：Square + Blue
     * - 展示形状和颜色可以独立变化
     * 
     * 桥接模式的优势：
     * - 如果使用继承，需要 2（颜色）× 2（形状）= 4 个子类
     * - 如果添加新颜色（如绿色），只需要添加1个新的实现类，不影响形状
     * - 如果添加新形状（如三角形），只需要添加1个新的抽象类，不影响颜色
     * - 避免了类爆炸问题
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("                  桥接模式测试                            ");
        System.out.println("==================================================");
        
        // 创建具体实现部分（Implementor）：颜色
        // 这些是实现部分的具体类，彼此独立变化
        Color red = new Red();
        Color blue = new Blue();
        
        // 测试1：红色圆形
        // 将抽象部分（Circle）与实现部分（Red）桥接
        System.out.println("\n========== 测试1：红色圆形 ==========");
        Shape redCircle = new Circle(red);
        System.out.println(redCircle.draw());
        
        // 测试2：蓝色圆形
        // 将抽象部分（Circle）与实现部分（Blue）桥接
        // 同一个抽象部分可以桥接不同的实现部分
        System.out.println("\n========== 测试2：蓝色圆形 ==========");
        Shape blueCircle = new Circle(blue);
        System.out.println(blueCircle.draw());
        
        // 测试3：红色正方形
        // 将抽象部分（Square）与实现部分（Red）桥接
        // 同一个实现部分可以桥接不同的抽象部分
        System.out.println("\n========== 测试3：红色正方形 ==========");
        Shape redSquare = new Square(red);
        System.out.println(redSquare.draw());
        
        // 测试4：蓝色正方形
        // 将抽象部分（Square）与实现部分（Blue）桥接
        // 展示两个维度的任意组合
        System.out.println("\n========== 测试4：蓝色正方形 ==========");
        Shape blueSquare = new Square(blue);
        System.out.println(blueSquare.draw());
        
        // 模式总结
        System.out.println("\n==================================================");
        System.out.println("                  模式总结                            ");
        System.out.println("==================================================");
        System.out.println("  1. Shape是抽象部分，定义了draw()方法，并持有Color引用");
        System.out.println("  2. Circle和Square是具体抽象部分，实现具体的draw逻辑");
        System.out.println("  3. Color是实现部分接口，定义了applyColor()方法");
        System.out.println("  4. Red和Blue是具体实现部分，实现具体的颜色逻辑");
        System.out.println("  5. 形状和颜色可以独立变化，添加新形状或颜色都不影响对方");
        System.out.println("  6. 通过组合而非继承的方式连接抽象和实现，更加灵活");
        System.out.println("==================================================");
    }
}

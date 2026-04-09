package com.design.mode.creational.factorymethod;

import com.design.mode.creational.factorymethod.factory.ConcreteFactoryA;
import com.design.mode.creational.factorymethod.factory.ConcreteFactoryB;
import com.design.mode.creational.factorymethod.factory.Factory;

/**
 * 工厂方法模式测试类
 *
 * 本测试类通过“产品A/产品B + 对应工厂”演示工厂方法模式的核心思想：
 * 1. 客户端面向 Factory 与 Product 抽象编程
 * 2. 具体产品由具体工厂决定并创建
 * 3. 业务流程中不直接依赖具体产品构造细节
 *
 * 工厂方法模式的主要优点：
 * 1. 符合开闭原则：新增产品时通常只需新增“产品类 + 工厂类”
 * 2. 符合单一职责原则：每个工厂聚焦一种产品创建规则
 * 3. 解耦创建与使用：客户端无需知道对象具体创建过程
 * 4. 便于测试与替换：可在不同工厂实现之间快速切换
 *
 * 适用场景：
 * 1. 当一个类不知道它所必须创建的对象的类的时候
 * 2. 当一个类希望由它的子类来指定创建对象的时候
 * 3. 当类将创建对象的职责委托给多个帮助子类中的某一个时，
 *    并且你希望将哪一个帮助子类是代理者这一信息局部化的时候
 */
public class FactoryMethodTest {

    /**
     * 入口方法：顺序演示工厂方法模式的典型使用方式
     *
     * 演示内容：
     * 1. 使用具体工厂A创建并使用产品A
     * 2. 使用具体工厂B创建并使用产品B
     * 3. 说明扩展新产品时对既有代码影响最小
     * 4. 对比工厂方法与简单工厂在扩展性方面的差异
     * 5. 模拟根据配置动态选择工厂
     *
     * @param args 命令行参数（本示例未使用）
     */
    public static void main(String[] args) {
        System.out.println("========== 使用工厂A创建产品 ==========");
        // 创建具体工厂A：负责生产产品A
        Factory factoryA = new ConcreteFactoryA();
        // 执行模板方法：内部会调用工厂方法创建产品并执行业务
        factoryA.doSomething();

        System.out.println("\n========== 使用工厂B创建产品 ==========");
        // 创建具体工厂B：负责生产产品B
        Factory factoryB = new ConcreteFactoryB();
        // 通过统一入口执行“创建 + 使用”流程
        factoryB.doSomething();

        System.out.println("\n========== 演示优点：新增产品无需修改原有代码 ==========");
        // 假设后续新增产品C：
        // 1. 新增 ConcreteProductC 实现 Product
        // 2. 新增 ConcreteFactoryC 继承 Factory
        // 3. 客户端按需切换到 FactoryC 即可
        // 既有工厂与产品类通常无需修改，体现“对扩展开放、对修改关闭”

        System.out.println("\n========== 工厂方法 VS 简单工厂 ==========");
        System.out.println("简单工厂：");
        System.out.println("- 一个工厂类，根据参数创建不同类型的产品");
        System.out.println("- 违反开闭原则：新增产品需要修改工厂类的代码");
        System.out.println("\n工厂方法：");
        System.out.println("- 一个产品对应一个工厂，每个工厂只负责创建一种产品");
        System.out.println("- 符合开闭原则：新增产品只需新增对应的工厂类");
        System.out.println("- 符合单一职责原则");

        System.out.println("\n========== 使用示例：根据配置文件创建不同工厂 ==========");
        // 这里用字符串模拟配置中心/配置文件中的工厂类型
        String factoryType = "A";
        Factory factory;

        // 通过配置决定使用哪个具体工厂，避免在业务代码中散落 new 具体产品
        if ("A".equals(factoryType)) {
            factory = new ConcreteFactoryA();
        } else {
            factory = new ConcreteFactoryB();
        }

        // 无论工厂类型如何，客户端都只依赖抽象Factory调用统一流程
        factory.doSomething();
    }
}

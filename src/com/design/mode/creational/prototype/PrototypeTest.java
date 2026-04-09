package com.design.mode.creational.prototype;

/**
 * 原型模式测试类
 *
 * 本类演示了原型模式的三种主要实现方式：
 * 1. 浅拷贝：使用 Object.clone() 方法
 * 2. 深拷贝：使用序列化和反序列化
 * 3. 深拷贝：引用类型也实现Cloneable，手动克隆
 *
 * 原型模式的优点：
 * 1. 性能优良：Java的clone方法是本地方法，基于内存二进制流复制，比直接new性能好
 * 2. 逃避构造函数的约束：不会触发构造函数的执行
 * 3. 简化创建：可以在运行时动态获取对象的类型
 *
 * 原型模式的缺点：
 * 1. 需要配备克隆方法，对于已有类可能需要修改源代码
 * 2. 实现深拷贝需要比较复杂的代码：每个引用类型都需要实现Cloneable
 * 3. 必须实现 Cloneable 接口，否则会抛出 CloneNotSupportedException
 * 4. 逃避类构造函数：可能导致对象的初始化不完整
 *
 * 使用场景：
 * 1. 资源优化场景：对象创建代价较大时
 * 2. 性能和安全要求的场景：避免重复初始化
 * 3. 一个对象需要提供给其他对象访问，而且各个调用者可能都需要修改其值时
 * 4. 原型模式很少单独出现，通常是和工厂方法模式一起出现
 */
public class PrototypeTest {

    /**
     * 主测试方法
     * 
     * 通过多个测试用例演示原型模式的三种主要实现方式：
     * 1. 浅拷贝：使用 Object.clone() 方法
     * 2. 深拷贝：使用序列化和反序列化
     * 3. 深拷贝：引用类型也实现Cloneable，手动克隆
     * 
     * 测试用例设计：
     * - 测试1：浅拷贝演示，展示引用共享问题
     * - 测试2：深拷贝（序列化方式），展示完全独立
     * - 测试3：深拷贝（手动克隆方式），展示手动控制
     * 
     * @param args 命令行参数（未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║                    原型模式测试                           ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝\n");

        System.out.println("========== 测试1：浅拷贝演示 ==========");
        System.out.println("说明：Person对象使用 Object.clone() 进行浅拷贝\n");

        // 创建原始对象
        Address address = new Address("北京", "海淀区", "100000");
        Person person1 = new Person("张三", 25, address);
        System.out.println("原始对象: " + person1);

        // 克隆对象（浅拷贝）
        Person person2 = person1.clone();
        System.out.println("克隆对象: " + person2);
        System.out.println("person1 == person2: " + (person1 == person2));
        System.out.println("person1.getAddress() == person2.getAddress(): " +
                (person1.getAddress() == person2.getAddress()));
        System.out.println("结论：person1和person2的address引用指向同一个对象\n");

        System.out.println("========== 修改引用类型字段的影响 ==========");
        // 修改克隆对象的地址信息
        person2.setName("李四");  // 修改基本类型
        person2.getAddress().setStreet("朝阳区");  // 修改引用类型的属性
        System.out.println("\n修改后：");
        System.out.println("person1: " + person1);
        System.out.println("person2: " + person2);
        System.out.println("结论：由于浅拷贝共享引用类型，person1的地址也被改变了！\n");

        System.out.println("========== 测试2：深拷贝（序列化方式） ==========");
        System.out.println("说明：Student对象使用序列化实现深拷贝\n");

        // 使用序列化方式实现深拷贝
        Address address2 = new Address("上海", "浦东新区", "200000");
        Student student1 = new Student("王五", 20, address2, "S001", "计算机科学");
        System.out.println("原始对象: " + student1);

        // 克隆对象（深拷贝）
        Student student2 = student1.clone();
        System.out.println("克隆对象: " + student2);
        System.out.println("student1 == student2: " + (student1 == student2));
        System.out.println("student1.getAddress() == student2.getAddress(): " +
                (student1.getAddress() == student2.getAddress()));
        System.out.println("结论：student1和student2的address引用指向不同的对象\n");

        System.out.println("========== 修改地址信息的影响 ==========");
        student2.setName("赵六");
        student2.getAddress().setCity("杭州");
        student2.getAddress().setStreet("西湖区");
        System.out.println("\n修改后：");
        System.out.println("student1: " + student1);
        System.out.println("student2: " + student2);
        System.out.println("结论：由于深拷贝，修改克隆对象不会影响原始对象！\n");

        System.out.println("========== 测试3：深拷贝（手动克隆方式） ==========");
        System.out.println("说明：Report对象手动克隆每个引用类型字段\n");

        // 创建包含多个引用类型的对象
        Document document = new Document(

                "年度财务报告",
                "这是2024年的财务报告，包含收入、支出、利润等关键财务指标。",
                "财务部"
        );
        Report report1 = new Report("R2024001", "2024年度报告", document,

                "2024-12-31", "张三");
        System.out.println("原始报告: \n" + report1);

        // 克隆报告对象
        Report report2 = report1.clone();
        System.out.println("\n克隆报告: \n" + report2);
        System.out.println("report1 == report2: " + (report1 == report2));
        System.out.println("report1.getDocument() == report2.getDocument(): " +
                (report1.getDocument() == report2.getDocument()));
        System.out.println("结论：Document对象也被深拷贝了\n");

        System.out.println("========== 修改文档内容 ==========");
        // 修改克隆对象的文档
        report2.setReportName("2024年度报告（修改版）");
        report2.getDocument().setTitle("2024年财务报告（修订版）");
        report2.getDocument().setAuthor("李四");
        System.out.println("\n修改后：");
        System.out.println("report1: " + report1.getReportName() + ", " +
                "文档：" + report1.getDocument().getTitle() + ", 作者：" +
                report1.getDocument().getAuthor());
        System.out.println("report2: " + report2.getReportName() + ", " +
                "文档：" + report2.getDocument().getTitle() + ", 作者：" +
                report2.getDocument().getAuthor());
        System.out.println("结论：两个报告对象完全独立，互不影响！\n");

        System.out.println("========== 原型模式的实际应用场景 ==========");
        System.out.println("1. 数据库连接池 - 创建初始连接后，其他连接通过克隆获得");
        System.out.println("2. 游戏开发 - 复制游戏角色、道具等对象");
        System.out.println("3. CAD软件 - 复制图形、组件等复杂对象");
        System.out.println("4. 保护性拷贝 - 返回对象的深拷贝，防止外部修改内部状态");
        System.out.println("5. 缓存框架 - 从缓存中获取对象的拷贝返回");
        System.out.println();

        System.out.println("========== 三种深拷贝方式对比 ==========");
        System.out.println("1. 序列化方式：");
        System.out.println("   - 优点：实现简单，不需要为每个引用类型写克隆方法");
        System.out.println("   - 缺点：性能较差，所有对象必须实现 Serializable");
        System.out.println("   - 适用：对象结构简单或嵌套不深的情况\n");

        System.out.println("2. 手动克隆方式：");
        System.out.println("   - 优点：性能好，控制精细");
        System.out.println("   - 缺点：代码繁琐，每个类都需要实现克隆逻辑");
        System.out.println("   - 适用：复杂对象，对性能要求高的情况\n");

        System.out.println("3. JSON序列化方式（如Jackson、Gson）：");
        System.out.println("   - 优点：实现简单，不需要实现 Serializable");
        System.out.println("   - 缺点：性能比Java序列化好，但仍不如手动克隆");
        System.out.println("   - 适用：前后端交互对象，配置对象等\n");

        System.out.println("╔═══════════════════════════════════════════════════════════╗");
        System.out.println("║        原型模式测试完成                                   ║");
        System.out.println("╚═══════════════════════════════════════════════════════════╝");
    }
}

package com.design.mode.behavioral.interpreter;

/**
 * 解释器模式测试入口。
 *
 * 本类通过构建不同复杂度的表达式树，演示解释器模式在算术场景下的基本工作方式：
 * 1. 使用终结符表达式表示常量或变量
 * 2. 使用非终结符表达式递归组合运算规则
 * 3. 使用上下文对象承载变量值并驱动动态求值
 *
 * 解释器模式（Interpreter Pattern）概要：
 *
 * 一、模式意图
 * 给定一个语言，定义它的文法的一种表示，并定义一个解释器，这个解释器使用该表示来解释语言中的句子。
 *
 * 二、模式结构
 * 1. 抽象表达式（Abstract Expression）: 声明一个抽象的解释操作，这个接口为抽象语法树中所有的节点所共享，这里是Expression
 * 2. 终结符表达式（Terminal Expression）: 实现与文法中的终结符相关联的解释操作，
 *    这里是NumberExpression、VariableExpression
 * 3. 非终结符表达式（Nonterminal Expression）: 为文法中的非终结符实现解释操作，
 *    这里是AddExpression、SubtractExpression、MultiplyExpression
 * 4. 上下文（Context）: 包含解释器之外的一些全局信息
 * 5. 客户端（Client）: 构建表示该文法定义的语言中一个特定句子的抽象语法树，调用解释操作
 *
 * 三、适用场景
 * 1. 当有一个语言需要解释执行，并且可以将该语言中的句子表示为一个抽象语法树时
 * 2. 该文法简单（对于复杂的文法，文法的类层次变得庞大而无法管理）
 * 3. 效率不是关键问题（最高效的解释器通常不是通过直接解释语法分析树实现的）
 *
 * 四、优缺点
 * 优点：
 * - 可扩展性比较好，灵活
 * - 增加了新的解释表达式的方式
 * - 易于实现简单文法
 *
 * 缺点：
 * - 可使用场景比较少
 * - 对于复杂的文法比较难维护
 * - 解释器模式会引起类膨胀
 * - 解释器模式采用递归调用方法，导致调试复杂
 *
 * 五、实际应用
 * - 正则表达式
 * - SQL解析
 * - 数学表达式计算器
 * - 编译器、解释器
 */
public class InterpreterTest {

    /**
     * 按场景顺序演示解释器模式求值过程。
     *
     * @param args 启动参数（本示例未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  解释器模式测试                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 初始化上下文：为后续变量表达式提供求值环境。
        Context context = new Context();
        System.out.println("========== 初始化上下文 ==========");
        context.setVariable("x", 10);
        context.setVariable("y", 5);
        context.setVariable("z", 2);
        context.displayVariables();
        System.out.println();

        // 场景1：终结符中的常量表达式，验证“叶子节点可直接返回值”。
        System.out.println("========== 测试1：简单数字表达式 ==========");
        Expression numExpr = new NumberExpression(42);
        System.out.println("表达式: " + numExpr);
        System.out.println("计算结果: " + numExpr.interpret(context));
        System.out.println();

        // 场景2：终结符中的变量表达式，验证“值从上下文读取”。
        System.out.println("========== 测试2：变量表达式 ==========");
        Expression varExpr = new VariableExpression("x");
        System.out.println("表达式: " + varExpr);
        System.out.println("计算结果: " + varExpr.interpret(context));
        System.out.println();

        // 场景3：非终结符中的加法表达式，验证“组合两个子节点进行递归求值”。
        System.out.println("========== 测试3：简单加法表达式 ==========");
        Expression addExpr = new AddExpression(
            new NumberExpression(10),
            new NumberExpression(20)
        );
        System.out.println("表达式: " + addExpr);
        System.out.println("计算结果: " + addExpr.interpret(context));
        System.out.println();

        // 场景4：变量参与运算，验证表达式树与上下文解耦。
        System.out.println("========== 测试4：变量加法表达式 ==========");
        Expression varAddExpr = new AddExpression(
            new VariableExpression("x"),
            new VariableExpression("y")
        );
        System.out.println("表达式: " + varAddExpr);
        System.out.println("计算结果: " + varAddExpr.interpret(context));
        System.out.println();

        // 场景5：组合多个非终结符，形成两层表达式树：x + y - z。
        System.out.println("========== 测试5：复杂表达式：x + y - z ==========");
        Expression complexExpr1 = new SubtractExpression(
            new AddExpression(
                new VariableExpression("x"),
                new VariableExpression("y")
            ),
            new VariableExpression("z")
        );
        System.out.println("表达式: " + complexExpr1);
        System.out.println("计算结果: " + complexExpr1.interpret(context));
        System.out.println();

        // 场景6：同时包含乘法与加法，观察不同子树独立解释后再汇总。
        System.out.println("========== 测试6：更复杂表达式：(x * y) + (z * 3) ==========");
        Expression complexExpr2 = new AddExpression(
            new MultiplyExpression(
                new VariableExpression("x"),
                new VariableExpression("y")
            ),
            new MultiplyExpression(
                new VariableExpression("z"),
                new NumberExpression(3)
            )
        );
        System.out.println("表达式: " + complexExpr2);
        System.out.println("计算结果: " + complexExpr2.interpret(context));
        System.out.println();

        // 场景7：更深层嵌套，演示解释器天然支持递归结构。
        System.out.println("========== 测试7：嵌套表达式：((x + 5) * (y - 2)) + z ==========");
        Expression nestedExpr = new AddExpression(
            new MultiplyExpression(
                new AddExpression(
                    new VariableExpression("x"),
                    new NumberExpression(5)
                ),
                new SubtractExpression(
                    new VariableExpression("y"),
                    new NumberExpression(2)
                )
            ),
            new VariableExpression("z")
        );
        System.out.println("表达式: " + nestedExpr);
        System.out.println("计算结果: " + nestedExpr.interpret(context));
        System.out.println();

        // 场景8：不改表达式树，仅修改上下文数据，验证结果会随环境变化。
        System.out.println("========== 测试8：动态修改上下文变量 ==========");
        System.out.println("修改变量值: x = 100, y = 20, z = 5");
        context.setVariable("x", 100);
        context.setVariable("y", 20);
        context.setVariable("z", 5);
        context.displayVariables();

        System.out.println("重新计算表达式: " + complexExpr1);
        System.out.println("新的计算结果: " + complexExpr1.interpret(context));
        System.out.println();

        // 场景9：模拟“解析字符串 -> 构建表达式树 -> 执行解释”的完整链路。
        System.out.println("========== 测试9：构建表达式解析器（模拟） ==========");
        System.out.println("模拟解析表达式字符串: \"a + b * c\"");

        // 创建独立上下文，避免与前面场景变量值互相影响。
        Context newContext = new Context();
        newContext.setVariable("a", 3);
        newContext.setVariable("b", 4);
        newContext.setVariable("c", 5);
        newContext.displayVariables();

        // 手动构建语法树：a + (b * c)，体现运算优先级由树结构决定而非字符串。
        Expression parsedExpr = new AddExpression(
            new VariableExpression("a"),
            new MultiplyExpression(
                new VariableExpression("b"),
                new VariableExpression("c")
            )
        );

        System.out.println("构建的表达式树: " + parsedExpr);
        System.out.println("计算结果: " + parsedExpr.interpret(newContext));
        System.out.println("注意：实际解释器需要实现词法分析和语法分析");
        System.out.println();

        // 场景10：批量展示多种表达式，便于横向对比不同节点类型的行为。
        System.out.println("========== 测试10：表达式求值展示 ==========");
        System.out.println("展示多个表达式的求值过程:");

        Expression[] expressions = {
            new NumberExpression(7),
            new VariableExpression("x"),
            new AddExpression(new NumberExpression(3), new NumberExpression(4)),
            new SubtractExpression(new VariableExpression("x"), new NumberExpression(2)),
            new MultiplyExpression(
                new AddExpression(new VariableExpression("x"), new NumberExpression(1)),
                new SubtractExpression(new VariableExpression("y"), new NumberExpression(1))
            )
        };

        String[] descriptions = {
            "常量 7",
            "变量 x",
            "3 + 4",
            "x - 2",
            "(x + 1) * (y - 1)"
        };

        for (int i = 0; i < expressions.length; i++) {
            System.out.println((i + 1) + ". " + descriptions[i] + " = " +
                             expressions[i] + " = " + expressions[i].interpret(context));
        }
        System.out.println();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  模式总结：                                                  ║");
        System.out.println("║  1. Expression是抽象表达式接口，定义interpret()方法         ║");
        System.out.println("║  2. NumberExpression和VariableExpression是终结符表达式      ║");
        System.out.println("║  3. AddExpression、SubtractExpression等是非终结符表达式    ║");
        System.out.println("║  4. Context是上下文，存储变量值等信息                      ║");
        System.out.println("║  5. 客户端构建表达式树，通过解释器解释执行                  ║");
        System.out.println("║  6. 解释器模式适合简单的文法，复杂文法会导致类膨胀          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}

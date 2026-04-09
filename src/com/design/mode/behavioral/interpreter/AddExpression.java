package com.design.mode.behavioral.interpreter;

/**
 * 加法表达式（非终结符表达式）。
 *
 * 该类通过组合左右两个子表达式，形成一个新的语法树父节点。
 * 求值时会先递归解释左右子树，再将结果相加。
 */
public class AddExpression implements Expression {
    private Expression left;
    private Expression right;

    /**
     * 构造加法节点。
     *
     * @param left 左操作数表达式
     * @param right 右操作数表达式
     */
    public AddExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    /**
     * 解释并计算 left + right。
     *
     * @param context 上下文，提供变量求值所需的数据
     * @return 左右子表达式结果之和
     */
    public int interpret(Context context) {
        return left.interpret(context) + right.interpret(context);
    }

    @Override
    /**
     * 返回加法节点的中缀字符串形式。
     *
     * @return 形如 "(left + right)" 的文本
     */
    public String toString() {
        return "(" + left + " + " + right + ")";
    }
}

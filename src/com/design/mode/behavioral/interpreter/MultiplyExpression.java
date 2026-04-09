package com.design.mode.behavioral.interpreter;

/**
 * 乘法表达式（非终结符表达式）。
 *
 * 该类用于组合两个子表达式并表示乘法关系，
 * 通常出现在构建更复杂算术表达式树的中间节点。
 */
public class MultiplyExpression implements Expression {
    private Expression left;
    private Expression right;

    /**
     * 构造乘法节点。
     *
     * @param left 左操作数表达式
     * @param right 右操作数表达式
     */
    public MultiplyExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    /**
     * 解释并计算 left * right。
     *
     * @param context 上下文，供子表达式访问变量值
     * @return 左右子表达式结果的乘积
     */
    public int interpret(Context context) {
        return left.interpret(context) * right.interpret(context);
    }

    @Override
    /**
     * 返回乘法节点的中缀字符串形式。
     *
     * @return 形如 "(left * right)" 的文本
     */
    public String toString() {
        return "(" + left + " * " + right + ")";
    }
}

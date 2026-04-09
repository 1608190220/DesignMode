package com.design.mode.behavioral.interpreter;

/**
 * 减法表达式（非终结符表达式）。
 *
 * 该节点用于描述两个子表达式之间的减法关系，
 * 解释时按照“左值减右值”的顺序执行计算。
 */
public class SubtractExpression implements Expression {
    private Expression left;
    private Expression right;

    /**
     * 构造减法节点。
     *
     * @param left 被减数表达式
     * @param right 减数表达式
     */
    public SubtractExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    @Override
    /**
     * 解释并计算 left - right。
     *
     * @param context 上下文，供子表达式读取变量值
     * @return 左子表达式结果减去右子表达式结果
     */
    public int interpret(Context context) {
        return left.interpret(context) - right.interpret(context);
    }

    @Override
    /**
     * 返回减法节点的中缀字符串形式。
     *
     * @return 形如 "(left - right)" 的文本
     */
    public String toString() {
        return "(" + left + " - " + right + ")";
    }
}

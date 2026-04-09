package com.design.mode.behavioral.interpreter;

/**
 * 变量表达式（终结符表达式）。
 *
 * 与数字常量不同，变量表达式的值需要在运行时通过上下文查询，
 * 因而可随着上下文数据变化而得到不同计算结果。
 */
public class VariableExpression implements Expression {
    private String variableName;

    /**
     * 创建一个变量节点。
     *
     * @param variableName 变量名称，例如 x、y、total
     */
    public VariableExpression(String variableName) {
        this.variableName = variableName;
    }

    @Override
    /**
     * 根据变量名从上下文中读取值并返回。
     *
     * @param context 上下文，保存变量映射关系
     * @return 变量当前值；若变量不存在则由上下文策略决定返回值
     */
    public int interpret(Context context) {
        return context.getVariable(variableName);
    }

    @Override
    /**
     * 返回变量名称作为表达式文本表示。
     *
     * @return 变量名
     */
    public String toString() {
        return variableName;
    }
}

package com.design.mode.behavioral.interpreter;

/**
 * 数字常量表达式（终结符表达式）。
 *
 * 该类在抽象语法树中表示一个固定整数，不依赖上下文变量即可直接得到结果，
 * 因此它是解释过程中的“叶子节点”之一。
 */
public class NumberExpression implements Expression {
    private int number;

    /**
     * 构造一个常量表达式节点。
     *
     * @param number 常量值
     */
    public NumberExpression(int number) {
        this.number = number;
    }

    @Override
    /**
     * 解释数字常量并返回其自身。
     *
     * @param context 上下文对象（该实现不读取上下文，仅保持接口统一）
     * @return 常量值
     */
    public int interpret(Context context) {
        return number;
    }

    @Override
    /**
     * 返回表达式的文本表示，便于打印表达式树结构。
     *
     * @return 常量的字符串形式
     */
    public String toString() {
        return String.valueOf(number);
    }
}

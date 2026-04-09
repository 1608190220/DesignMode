package com.design.mode.behavioral.interpreter;

import java.util.HashMap;
import java.util.Map;

/**
 * 解释执行过程中的上下文对象，负责维护变量名与变量值之间的映射关系。
 *
 * 在解释器模式中，上下文用于承载“表达式本身以外”的外部信息，
 * 本示例中该信息主要是变量求值所需的数据环境（例如 x=10、y=5）。
 * 各类表达式在解释时通过该对象读取或更新变量值，从而实现动态计算。
 */
public class Context {
    private Map<String, Integer> variables;

    /**
     * 初始化空的变量表。
     * 新建上下文后，如果未设置任何变量，变量查询将走默认值逻辑。
     */
    public Context() {
        variables = new HashMap<>();
    }

    /**
     * 向上下文写入变量值；若变量已存在则覆盖旧值。
     *
     * @param var 变量名
     * @param value 变量对应的整数值
     */
    public void setVariable(String var, int value) {
        variables.put(var, value);
    }

    /**
     * 从上下文读取变量值。
     *
     * 当变量不存在时返回0，避免解释阶段因空值触发异常。
     * 该策略简化了示例演示，但在真实业务中可按需改为抛异常或返回Optional。
     *
     * @param var 变量名
     * @return 变量值；若未定义则返回0
     */
    public int getVariable(String var) {
        return variables.getOrDefault(var, 0);
    }

    /**
     * 打印当前上下文中的全部变量，便于观察表达式求值前后的环境变化。
     */
    public void displayVariables() {
        System.out.println("当前上下文变量:");
        for (Map.Entry<String, Integer> entry : variables.entrySet()) {
            System.out.println("  " + entry.getKey() + " = " + entry.getValue());
        }
    }
}

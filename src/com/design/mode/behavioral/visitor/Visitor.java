package com.design.mode.behavioral.visitor;

/**
 * 访问者接口（Visitor）
 *
 * 该接口定义了对不同具体元素的访问契约。
 * 每新增一种元素类型，通常需要在此接口中新增对应的 visit 方法；
 * 每新增一种业务操作，则通过新增具体访问者实现该接口即可。
 */
public interface Visitor {
    
    /**
     * 访问工程师元素
     *
     * @param engineer 工程师对象，包含工程师维度的数据
     */
    void visit(Engineer engineer);
    
    /**
     * 访问经理元素
     *
     * @param manager 经理对象，包含管理维度的数据
     */
    void visit(Manager manager);
}

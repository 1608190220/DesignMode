package com.design.mode.behavioral.iterator;

/**
 * 聚合接口
 *
 * 该接口对应迭代器模式中的 Aggregate 角色，
 * 负责抽象“容器对象如何创建迭代器”与“如何访问元素”的基础能力。
 * 客户端通过该抽象即可完成遍历，不必依赖具体容器实现细节。
 *
 * 迭代器模式（Iterator Pattern）详解：
 *
 * 一、模式意图
 * 提供一种方法顺序访问一个聚合对象中的各个元素，而又不暴露该对象的内部表示。
 *
 * 二、模式结构
 * 1. 迭代器（Iterator）: 定义访问和遍历元素的接口，这里是Iterator
 * 2. 具体迭代器（Concrete Iterator）: 实现迭代器接口，跟踪遍历中的当前位置，这里是BookShelfIterator
 * 3. 聚合（Aggregate）: 定义创建相应迭代器对象的接口，这里是Aggregate
 * 4. 具体聚合（Concrete Aggregate）: 实现创建相应迭代器的接口，返回具体迭代器的实例，这里是BookShelf
 *
 * 三、适用场景
 * 1. 需要访问一个聚合对象的内容而无需暴露它的内部表示
 * 2. 需要支持对聚合对象的多种遍历方式
 * 3. 需要为遍历不同的聚合结构提供一个统一的接口
 *
 * 四、优缺点
 * 优点：
 * - 支持以不同的方式遍历一个聚合对象
 * - 迭代器简化了聚合类的接口
 * - 在同一个聚合上可以有多个遍历
 * - 在迭代器模式中，增加新的聚合类和迭代器类都很方便，无需修改原有代码
 *
 * 缺点：
 * - 由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，
 *   类的个数成对增加，这在一定程度上增加了系统的复杂性
 *
 * 五、实际应用
 * - Java Collection框架中的Iterator
 * - JDBC中的ResultSet
 * - 文件系统遍历
 * - XML文档遍历
 */
public interface Aggregate {
    /**
     * 创建迭代器
     *
     * 每次调用可返回一个独立的遍历器实例，
     * 以支持同一聚合对象上的并行遍历。
     *
     * @return 迭代器对象
     */
    Iterator createIterator();

    /**
     * 获取聚合大小
     *
     * @return 聚合中元素的数量
     */
    int size();

    /**
     * 获取指定位置的元素
     *
     * @param index 索引位置
     * @return 元素
     */
    Object get(int index);

    /**
     * 添加元素
     *
     * @param obj 要添加的元素
     */
    void add(Object obj);
}

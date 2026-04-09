package com.design.mode.behavioral.iterator;

/**
 * 迭代器接口（Iterator）
 *
 * 定义遍历聚合对象时的统一访问协议，
 * 使客户端无需感知底层容器结构即可顺序读取元素。
 */
public interface Iterator {
    /**
     * 判断是否还有下一个元素
     *
     * @return 如果有下一个元素返回true，否则返回false
     */
    boolean hasNext();

    /**
     * 返回下一个元素
     *
     * 通常会移动内部游标到下一位置。
     *
     * @return 下一个元素
     */
    Object next();

    /**
     * 获取当前元素的索引
     *
     * 该索引通常表示“下一次 next() 调用将访问的位置”。
     *
     * @return 当前索引
     */
    int getCurrentIndex();

    /**
     * 重置迭代器到起始位置
     *
     * 调用后可重新遍历同一聚合对象。
     */
    void reset();
}

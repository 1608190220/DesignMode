package com.design.mode.behavioral.iterator;

/**
 * 书架迭代器 - 具体迭代器（Concrete Iterator）
 *
 * 该类维护遍历游标 index，并按照从前到后的顺序访问书架元素。
 */
public class BookShelfIterator implements Iterator {
    /** 要遍历的书架对象 */
    private BookShelf bookShelf;
    /** 当前游标位置 */
    private int index;

    /**
     * 创建迭代器
     *
     * @param bookShelf 目标书架
     */
    public BookShelfIterator(BookShelf bookShelf) {
        this.bookShelf = bookShelf;
        this.index = 0;
    }

    /**
     * 判断是否还能继续迭代
     *
     * @return 有剩余元素返回 true
     */
    @Override
    public boolean hasNext() {
        return index < bookShelf.size();
    }

    /**
     * 获取下一个书籍元素并前移游标
     *
     * @return 书籍对象；无元素时返回 null
     */
    @Override
    public Object next() {
        if (hasNext()) {
            Book book = (Book) bookShelf.get(index);
            index++;
            return book;
        }
        return null;
    }

    /**
     * 获取当前游标位置
     *
     * @return 当前索引
     */
    @Override
    public int getCurrentIndex() {
        return index;
    }

    /**
     * 重置迭代器
     *
     * 将游标恢复到起始位置，支持重新遍历。
     */
    @Override
    public void reset() {
        index = 0;
        System.out.println("迭代器已重置到起始位置");
    }

    /**
     * 获取当前书籍（不移动指针）
     *
     * @return 当前书籍
     */
    public Book current() {
        if (index >= 0 && index < bookShelf.size()) {
            return (Book) bookShelf.get(index);
        }
        return null;
    }

    /**
     * 跳过指定数量的书籍
     *
     * @param count 要跳过的数量
     */
    public void skip(int count) {
        if (count > 0) {
            index = Math.min(index + count, bookShelf.size());
            System.out.println("跳过了 " + count + " 本书");
        }
    }
}

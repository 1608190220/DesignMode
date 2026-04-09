package com.design.mode.behavioral.iterator;

import java.util.ArrayList;
import java.util.List;

/**
 * 书架类 - 具体聚合（Concrete Aggregate）
 *
 * 该类负责管理书籍集合，并提供迭代器创建入口。
 * 客户端既可通过迭代器统一遍历，也可使用书架自身提供的业务方法。
 */
public class BookShelf implements Aggregate {
    /** 书籍集合 */
    private List<Book> books;

    /**
     * 创建空书架
     */
    public BookShelf() {
        books = new ArrayList<>();
    }

    /**
     * 创建书架迭代器
     *
     * @return 具体迭代器实例
     */
    @Override
    public Iterator createIterator() {
        return new BookShelfIterator(this);
    }

    /**
     * 获取书架中书籍数量
     *
     * @return 数量
     */
    @Override
    public int size() {
        return books.size();
    }

    /**
     * 获取指定位置的书籍
     *
     * @param index 索引位置
     * @return 书籍对象；越界时返回 null
     */
    @Override
    public Object get(int index) {
        if (index >= 0 && index < books.size()) {
            return books.get(index);
        }
        return null;
    }

    /**
     * 通过聚合接口添加元素
     *
     * 仅当参数为 Book 类型时才会加入集合。
     *
     * @param obj 待添加对象
     */
    @Override
    public void add(Object obj) {
        if (obj instanceof Book) {
            books.add((Book) obj);
        }
    }

    /**
     * 添加书籍
     *
     * @param book 书籍对象
     */
    public void addBook(Book book) {
        books.add(book);
    }

    /**
     * 移除书籍
     *
     * @param index 索引位置
     * @return 被移除的书籍
     */
    public Book removeBook(int index) {
        if (index >= 0 && index < books.size()) {
            return books.remove(index);
        }
        return null;
    }

    /**
     * 根据书名查找书籍
     *
     * @param title 书名
     * @return 书籍对象，如果没找到返回null
     */
    public Book findBookByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    /**
     * 显示所有书籍
     *
     * 用于示例演示，按顺序输出书架内容。
     */
    public void displayBooks() {
        System.out.println("书架上有 " + books.size() + " 本书:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println((i + 1) + ". " + books.get(i));
        }
    }
}

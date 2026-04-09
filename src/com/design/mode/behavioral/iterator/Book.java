package com.design.mode.behavioral.iterator;

/**
 * 书籍实体类
 *
 * 该类表示聚合中的单个元素对象（Element），
 * 在示例中作为书架容器的基础数据单元。
 */
public class Book {
    /** 书名 */
    private String title;
    /** 作者 */
    private String author;
    /** 出版年份 */
    private int year;

    /**
     * 创建书籍对象
     *
     * @param title 书名
     * @param author 作者
     * @param year 出版年份
     */
    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    /**
     * 获取书名
     *
     * @return 书名
     */
    public String getTitle() {
        return title;
    }

    /**
     * 获取作者
     *
     * @return 作者
     */
    public String getAuthor() {
        return author;
    }

    /**
     * 获取出版年份
     *
     * @return 出版年份
     */
    public int getYear() {
        return year;
    }

    /**
     * 返回书籍可读描述
     *
     * @return 格式化字符串
     */
    @Override
    public String toString() {
        return "《" + title + "》 - " + author + " (" + year + ")";
    }
}

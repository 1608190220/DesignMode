package com.design.mode.creational.prototype;

/**
 * 文档类 - 演示深拷贝的另一种方式（让引用类型也实现Cloneable）
 * 
 * 这是一个文档类，用于演示手动实现深拷贝的方式。
 * 它实现了Cloneable接口，可以被克隆。
 * 
 * 手动克隆方式的使用方法：
 * 1. 引用类型（Document）也实现 Cloneable 接口
 * 2. 在 clone() 方法中，手动对引用类型进行clone
 * 3. 这种方式比序列化性能更好，但需要手动实现
 * 
 * 手动克隆方式的优点：
 * - 性能好：直接内存复制，不涉及IO操作
 * - 控制精细：可以选择性地克隆某些字段
 * - 不需要Serializable接口
 * 
 * 手动克隆方式的缺点：
 * - 代码繁琐：每个类都需要实现clone()方法
 * - 容易出错：需要手动处理所有引用类型字段
 * - 维护成本高：新增字段时需要更新clone()方法
 */
public class Document implements Cloneable {

    private String title;      // 文档标题
    private String content;    // 文档内容
    private String author;     // 作者

    public Document(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    /**
     * 实现深拷贝
     * 通过调用super.clone()实现浅拷贝
     * 然后对引用类型字段手动处理（本类中没有引用类型字段）
     *
     * @return 深拷贝的文档对象
     */
    @Override
    public Document clone() {
        try {
            // 调用 Object 的 clone() 方法
            Document cloned = (Document) super.clone();

            // 如果 Document 中有引用类型字段，需要手动克隆它们
            // 例如：
            // if (this.someReferenceField != null) {
            //     cloned.someReferenceField = this.someReferenceField.clone();
            // }

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("文档克隆失败", e);
        }
    }

    @Override
    public String toString() {
        return "Document{title='" + title + "', author='" + author + "', " +
                "content='" + (content.length() > 50 ? content.substring(0, 50) + "..." : content) + "'}";
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}

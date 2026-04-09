package com.design.mode.behavioral.iterator;

/**
 * 迭代器模式测试类
 *
 * 本测试围绕“书架遍历”场景，系统展示迭代器模式的核心价值：
 * 1. 遍历逻辑与聚合结构解耦
 * 2. 多迭代器并行独立遍历
 * 3. 迭代过程的重置、跳过、搜索等控制能力
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
public class IteratorTest {

    /**
     * 程序入口
     *
     * 演示内容包括：
     * - 标准遍历
     * - 多迭代器并行
     * - 手动迭代控制
     * - 遍历中修改集合
     * - 反向遍历思路
     *
     * @param args 命令行参数（本示例未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  迭代器模式测试                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 场景准备：创建具体聚合对象
        BookShelf bookShelf = new BookShelf();
        System.out.println("========== 初始化书架 ==========");

        // 向书架添加测试数据
        bookShelf.addBook(new Book("设计模式", "GoF", 1994));
        bookShelf.addBook(new Book("Java编程思想", "Bruce Eckel", 2006));
        bookShelf.addBook(new Book("Effective Java", "Joshua Bloch", 2018));
        bookShelf.addBook(new Book("重构", "Martin Fowler", 2019));
        bookShelf.addBook(new Book("代码整洁之道", "Robert C. Martin", 2008));
        bookShelf.addBook(new Book("算法导论", "Thomas H. Cormen", 2009));

        bookShelf.displayBooks();
        System.out.println();

        // 场景1：标准顺序遍历
        System.out.println("========== 测试1：使用迭代器遍历书架 ==========");
        Iterator iterator = bookShelf.createIterator();
        System.out.println("开始遍历书架:");

        int count = 1;
        while (iterator.hasNext()) {
            Book book = (Book) iterator.next();
            System.out.println(count + ". " + book);
            count++;
        }
        System.out.println("遍历完成，共 " + (count - 1) + " 本书");
        System.out.println();

        // 场景2：重置迭代器后复用遍历器
        System.out.println("========== 测试2：重置迭代器并重新遍历 ==========");
        iterator.reset();
        System.out.println("重置迭代器后重新遍历前3本书:");

        for (int i = 0; i < 3 && iterator.hasNext(); i++) {
            Book book = (Book) iterator.next();
            System.out.println((i + 1) + ". " + book);
        }
        System.out.println("当前迭代器位置: " + iterator.getCurrentIndex());
        System.out.println();

        // 场景3：同一聚合对象上创建多个独立迭代器
        System.out.println("========== 测试3：使用多个迭代器同时遍历 ==========");
        Iterator iterator1 = bookShelf.createIterator();
        Iterator iterator2 = bookShelf.createIterator();

        System.out.println("迭代器1遍历前2本书:");
        for (int i = 0; i < 2 && iterator1.hasNext(); i++) {
            System.out.println("迭代器1: " + iterator1.next());
        }

        System.out.println("\n迭代器2遍历所有书（独立遍历）:");
        int idx = 1;
        while (iterator2.hasNext()) {
            System.out.println("迭代器2-" + idx + ": " + iterator2.next());
            idx++;
        }

        System.out.println("\n迭代器1继续遍历剩余的书:");
        while (iterator1.hasNext()) {
            System.out.println("迭代器1: " + iterator1.next());
        }
        System.out.println();

        // 场景4：通过扩展方法 skip() 控制遍历起点
        System.out.println("========== 测试4：使用迭代器跳过元素 ==========");
        Iterator skipIterator = bookShelf.createIterator();
        System.out.println("创建新迭代器，跳过前2本书:");

        // 跳过前两本，从第三本开始读
        ((BookShelfIterator) skipIterator).skip(2);

        System.out.println("从第3本书开始遍历:");
        int skipCount = 3;
        while (skipIterator.hasNext()) {
            Book book = (Book) skipIterator.next();
            System.out.println(skipCount + ". " + book);
            skipCount++;
        }
        System.out.println();

        // 场景5：手动控制迭代流程（next/current/索引）
        System.out.println("========== 测试5：手动控制迭代过程 ==========");
        BookShelfIterator manualIterator = (BookShelfIterator) bookShelf.createIterator();
        System.out.println("手动控制迭代过程:");

        // 读取第一本书
        if (manualIterator.hasNext()) {
            System.out.println("第一本书: " + manualIterator.next());
        }

        // 查看当前游标位置对应书籍（不移动指针）
        Book currentBook = manualIterator.current();
        if (currentBook != null) {
            System.out.println("当前指针位置的书: " + currentBook);
        }

        // 继续前进两步
        if (manualIterator.hasNext()) {
            System.out.println("下一本书: " + manualIterator.next());
        }
        if (manualIterator.hasNext()) {
            System.out.println("再下一本书: " + manualIterator.next());
        }

        System.out.println("当前索引位置: " + manualIterator.getCurrentIndex());
        System.out.println("是否还有下一本: " + manualIterator.hasNext());
        System.out.println();

        // 场景6：模拟 for-each 风格遍历
        System.out.println("========== 测试6：使用for-each风格遍历（模拟） ==========");
        System.out.println("模拟for-each循环遍历:");

        Iterator foreachIterator = bookShelf.createIterator();
        for (int i = 1; foreachIterator.hasNext(); i++) {
            Book book = (Book) foreachIterator.next();
            System.out.println("第" + i + "次循环: " + book);
        }
        System.out.println();

        // 场景7：遍历过程中修改聚合对象
        System.out.println("========== 测试7：遍历过程中修改聚合对象 ==========");
        System.out.println("注意：在实际应用中，遍历时修改聚合对象可能导致ConcurrentModificationException");
        System.out.println("这里演示遍历到一半时添加新书:");

        Iterator modIterator = bookShelf.createIterator();
        System.out.println("开始遍历，遍历到第3本书时添加新书:");

        for (int i = 1; modIterator.hasNext(); i++) {
            Book book = (Book) modIterator.next();
            System.out.println("第" + i + "本书: " + book);

            if (i == 3) {
                System.out.println(">>> 在遍历过程中添加新书《新书》");
                bookShelf.addBook(new Book("新书", "新作者", 2025));
                System.out.println(">>> 书架现在有 " + bookShelf.size() + " 本书");
            }
        }
        System.out.println();

        // 场景8：反向遍历思路（示例中用索引模拟）
        System.out.println("========== 测试8：不同类型的迭代器（反向迭代器示例） ==========");
        System.out.println("创建反向迭代器（从后往前遍历）:");

        // 这里未实现独立反向迭代器类，仅演示核心思路
        System.out.println("反向遍历书架:");
        for (int i = bookShelf.size() - 1; i >= 0; i--) {
            Book book = (Book) bookShelf.get(i);
            System.out.println((bookShelf.size() - i) + ". " + book);
        }
        System.out.println("注意：实际实现中应该创建ReverseBookShelfIterator类");
        System.out.println();

        // 场景9：借助迭代器实现条件搜索
        System.out.println("========== 测试9：使用迭代器查找特定元素 ==========");
        Iterator searchIterator = bookShelf.createIterator();
        String searchTitle = "重构";

        System.out.println("查找书名为《" + searchTitle + "》的书:");
        boolean found = false;
        while (searchIterator.hasNext()) {
            Book book = (Book) searchIterator.next();
            if (book.getTitle().equals(searchTitle)) {
                System.out.println("找到了: " + book);
                System.out.println("位置: 第" + searchIterator.getCurrentIndex() + "本");
                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("未找到书名为《" + searchTitle + "》的书");
        }
        System.out.println();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  模式总结：                                                  ║");
        System.out.println("║  1. Aggregate是聚合接口，定义创建迭代器的方法              ║");
        System.out.println("║  2. BookShelf是具体聚合，实现Aggregate接口                 ║");
        System.out.println("║  3. Iterator是迭代器接口，定义hasNext()和next()方法        ║");
        System.out.println("║  4. BookShelfIterator是具体迭代器，实现遍历逻辑            ║");
        System.out.println("║  5. 客户端通过迭代器访问聚合对象，无需了解内部结构          ║");
        System.out.println("║  6. 可以创建多个迭代器同时遍历同一个聚合对象                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}

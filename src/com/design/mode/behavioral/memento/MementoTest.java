package com.design.mode.behavioral.memento;

/**
 * 备忘录模式测试类
 *
 * 本测试以“文本编辑器撤销/重做”为业务场景，
 * 系统性演示备忘录模式中三个核心角色的协作方式：
 * 1. TextEditor 负责产生和恢复状态
 * 2. EditorMemento 负责保存状态快照
 * 3. History 负责管理历史记录与回退流程
 *
 * 备忘录模式（Memento Pattern）详解：
 *
 * 一、模式意图
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
 * 这样以后就可将该对象恢复到原先保存的状态。
 *
 * 二、模式结构
 * 1. 发起人（Originator）: 创建一个备忘录，用以记录当前时刻它的内部状态，并可使用备忘录恢复内部状态，
 *    这里是TextEditor
 * 2. 备忘录（Memento）: 存储发起人的内部状态，这里是EditorMemento
 * 3. 管理者（Caretaker）: 负责保存备忘录，但不能对备忘录的内容进行操作或检查，
 *    这里是History
 *
 * 三、适用场景
 * 1. 必须保存一个对象在某一个时刻的状态，这样以后需要时它才能恢复到先前的状态
 * 2. 如果一个用接口来让其他对象直接得到这些状态，将会暴露对象的实现细节并破坏对象的封装性
 *
 * 四、优缺点
 * 优点：
 * - 提供了一种状态恢复的实现机制，使得用户可以方便地回到某个历史状态
 * - 实现了信息的封装，用户不需要关心状态的保存细节
 *
 * 缺点：
 * - 如果发起人需要保存的状态数据过多，会占用比较大的存储资源
 * - 当发起人的状态改变时，有可能这个状态无效，这时候使用备忘录模式将无法恢复到该状态
 *
 * 五、实际应用
 * - 文本编辑器的撤销/重做功能
 * - 游戏存档
 * - 数据库事务回滚
 * - 浏览器历史记录
 */
public class MementoTest {

    /**
     * 程序入口
     *
     * 演示内容包括：
     * - 基础输入与状态保存
     * - 撤销与重做
     * - 检查点恢复
     * - 历史容量限制
     * - 复杂编辑流程
     *
     * @param args 命令行参数（本示例未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  备忘录模式测试                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 创建文本编辑器（发起人）
        TextEditor editor = new TextEditor();
        System.out.println("========== 创建文本编辑器 ==========");
        editor.display();
        System.out.println();

        // 创建历史记录管理器（管理者）
        History history = new History(10); // 最多保存10个历史记录
        System.out.println("========== 创建历史记录管理器 ==========");
        history.displayHistory();
        System.out.println();

        // 场景1：连续输入文本，并在每一步之后保存快照
        System.out.println("========== 测试1：输入文本并保存状态 ==========");
        editor.type("设计模式");
        history.save(editor.createMemento());

        editor.type("是软件开发中");
        history.save(editor.createMemento());

        editor.type("的重要概念");
        history.save(editor.createMemento());

        System.out.println("当前编辑器状态:");
        editor.display();
        System.out.println();

        // 场景2：执行一次撤销，恢复到上一个已保存状态
        System.out.println("========== 测试2：撤销操作 ==========");
        System.out.println("执行撤销操作...");
        EditorMemento undoState = history.undo();
        if (undoState != null) {
            editor.restoreFromMemento(undoState);
        }
        System.out.println();

        // 场景3：继续撤销，观察状态沿历史链路回退
        System.out.println("========== 测试3：再次撤销操作 ==========");
        System.out.println("再次执行撤销操作...");
        undoState = history.undo();
        if (undoState != null) {
            editor.restoreFromMemento(undoState);
        }
        System.out.println();

        // 场景4：将刚刚撤销的状态重新恢复
        System.out.println("========== 测试4：重做操作 ==========");
        System.out.println("执行重做操作...");
        EditorMemento redoState = history.redo();
        if (redoState != null) {
            editor.restoreFromMemento(redoState);
        }
        System.out.println();

        // 场景5：在已有历史基础上继续编辑，验证新分支会清空重做栈
        System.out.println("========== 测试5：继续编辑并保存 ==========");
        editor.type("的核心思想");
        history.save(editor.createMemento());

        editor.moveCursor(2);
        editor.select(2, 6);
        history.save(editor.createMemento());

        editor.type("（可重用）");
        history.save(editor.createMemento());

        System.out.println("当前编辑器状态:");
        editor.display();
        System.out.println();

        // 场景6：多次撤销，验证栈式回退过程
        System.out.println("========== 测试6：多次撤销操作 ==========");
        System.out.println("连续执行3次撤销操作...");
        for (int i = 0; i < 3; i++) {
            undoState = history.undo();
            if (undoState != null) {
                editor.restoreFromMemento(undoState);
            }
            System.out.println();
        }
        System.out.println();

        // 场景7：多次重做，验证已撤销状态可按顺序恢复
        System.out.println("========== 测试7：多次重做操作 ==========");
        System.out.println("连续执行2次重做操作...");
        for (int i = 0; i < 2; i++) {
            redoState = history.redo();
            if (redoState != null) {
                editor.restoreFromMemento(redoState);
            }
            System.out.println();
        }
        System.out.println();

        // 场景8：删除操作后恢复，验证编辑行为也可通过快照回退
        System.out.println("========== 测试8：删除操作和恢复 ==========");
        editor.moveCursor(6);
        editor.delete(3); // 删除"核心"
        history.save(editor.createMemento());

        System.out.println("删除后的状态:");
        editor.display();

        System.out.println("撤销删除...");
        undoState = history.undo();
        if (undoState != null) {
            editor.restoreFromMemento(undoState);
        }
        System.out.println();

        // 场景9：替换操作后恢复，验证复杂状态同样可保存与撤销
        System.out.println("========== 测试9：替换操作和恢复 ==========");
        editor.select(2, 6); // 选中"设计模式"
        editor.replaceSelection("Design Pattern");
        history.save(editor.createMemento());

        System.out.println("替换后的状态:");
        editor.display();

        System.out.println("撤销替换...");
        undoState = history.undo();
        if (undoState != null) {
            editor.restoreFromMemento(undoState);
        }
        System.out.println();

        // 场景10：使用检查点快速标记关键版本
        System.out.println("========== 测试10：检查点功能 ==========");
        System.out.println("保存检查点 '重要节点'...");
        EditorMemento checkpoint = history.saveCheckpoint("重要节点", editor);

        editor.type(" - 需要重点掌握");
        history.save(editor.createMemento());

        System.out.println("添加文本后的状态:");
        editor.display();

        System.out.println("恢复到检查点 '重要节点'...");
        history.restoreToCheckpoint(checkpoint);
        editor.restoreFromMemento(checkpoint);
        System.out.println();

        // 场景11：超过最大历史容量时，自动移除最旧记录
        System.out.println("========== 测试11：历史记录限制测试 ==========");
        System.out.println("添加大量历史记录（超过限制）...");
        for (int i = 1; i <= 15; i++) {
            editor.type("文本" + i);
            history.save(editor.createMemento());
        }
        System.out.println();

        history.displayHistory();
        System.out.println();

        // 场景12：清空历史记录后，撤销链路失效
        System.out.println("========== 测试12：清空历史记录 ==========");
        history.clear();
        history.displayHistory();

        System.out.println("尝试撤销空历史...");
        undoState = history.undo();
        if (undoState == null) {
            System.out.println("撤销失败：没有历史记录");
        }
        System.out.println();

        // 场景13：通过组合操作构造更完整的编辑流程示例
        System.out.println("========== 测试13：复杂编辑流程演示 ==========");
        System.out.println("开始复杂编辑流程...");

        // 为复杂流程重新初始化编辑器与历史管理器
        editor = new TextEditor();
        history = new History();

        // 逐步执行一组连续编辑动作，并在关键节点保存快照
        editor.type("备忘录模式演示");
        history.save(editor.createMemento());

        editor.moveCursor(6);
        editor.type("（Memento Pattern）");
        history.save(editor.createMemento());

        editor.select(6, 24);
        editor.delete(18); // 删除"（Memento Pattern）"
        history.save(editor.createMemento());

        editor.moveCursor(editor.getContent().length());
        editor.type(" - 状态保存与恢复");
        history.save(editor.createMemento());

        System.out.println("最终状态:");
        editor.display();

        System.out.println("查看完整历史记录:");
        java.util.Stack<EditorMemento> allStates = history.getAllStates();
        for (int i = 0; i < allStates.size(); i++) {
            System.out.println("状态 " + (i + 1) + ":");
            allStates.get(i).display();
        }
        System.out.println();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  模式总结：                                                  ║");
        System.out.println("║  1. TextEditor是发起人，创建和恢复备忘录                    ║");
        System.out.println("║  2. EditorMemento是备忘录，保存编辑器的内部状态             ║");
        System.out.println("║  3. History是管理者，管理备忘录的保存和恢复                 ║");
        System.out.println("║  4. 编辑器通过createMemento()保存状态                      ║");
        System.out.println("║  5. 通过restoreFromMemento()从备忘录恢复状态               ║");
        System.out.println("║  6. 历史管理器实现撤销/重做功能，封装了状态管理细节          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}

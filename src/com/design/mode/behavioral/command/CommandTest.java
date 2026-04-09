package com.design.mode.behavioral.command;

/**
 * 命令模式测试类
 *
 * 命令模式（Command Pattern）详解：
 *
 * 一、模式意图
 * 将一个请求封装为一个对象，从而使你可以用不同的请求对客户进行参数化；
 * 对请求排队或记录请求日志，以及支持可撤销的操作。
 *
 * 二、模式结构
 * 1. 命令（Command）: 声明执行操作的接口，这里是Command
 * 2. 具体命令（Concrete Command）: 将一个接收者对象绑定于一个动作，实现execute方法，
 *    这里是CopyCommand、PasteCommand等
 * 3. 客户端（Client）: 创建具体命令对象并设定它的接收者
 * 4. 调用者（Invoker）: 要求命令执行请求，这里是EditorInvoker
 * 5. 接收者（Receiver）: 知道如何实施与执行一个请求相关的操作，这里是TextEditor
 *
 * 三、适用场景
 * 1. 需要将请求调用者和请求接收者解耦，使得调用者和接收者不直接交互
 * 2. 需要在不同的时间指定请求、将请求排队和执行请求
 * 3. 需要支持命令的撤销（Undo）和恢复（Redo）操作
 * 4. 需要将一组操作组合在一起，即支持宏命令
 *
 * 四、优缺点
 * 优点：
 * - 降低系统的耦合度：请求者与接收者之间没有直接引用关系
 * - 新的命令可以很容易地加入到系统中，符合开闭原则
 * - 可以比较容易地设计一个命令队列或宏命令（组合命令）
 * - 可以方便地实现请求的撤销（Undo）和恢复（Redo）
 *
 * 缺点：
 * - 可能会导致系统有过多的具体命令类
 * - 命令模式的结果就是接收者和执行者之间解耦，增加了系统的复杂性
 *
 * 五、实际应用
 * - GUI中的按钮和菜单项
 * - 事务处理
 * - 宏录制功能
 * - 线程池、工作队列
 *
 * 六、本测试的目标
 * 1. 串联 Receiver、ConcreteCommand、Invoker 三类角色的交互过程
 * 2. 验证执行、撤销、重做在双栈历史模型下的行为表现
 * 3. 展示命令组合调用（宏命令思路）在业务流程中的可读性
 *
 * 七、阅读建议
 * - 先看“测试1~5”理解基础编辑命令
 * - 再看“测试6~12”观察历史栈状态变化
 * - 最后看“测试13”理解命令序列化执行的扩展方向
 */
public class CommandTest {

    /**
     * 命令模式演示入口。
     * 通过顺序脚本化场景输出，直观观察各命令对文本与历史栈的影响。
     * @param args 命令行参数（本示例未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  命令模式测试                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 1) 创建接收者：负责执行真正文本操作
        TextEditor editor = new TextEditor();
        System.out.println("========== 初始化文本编辑器 ==========");
        editor.setText("设计模式是软件开发中的重要概念。");
        editor.display();
        System.out.println();

        // 2) 创建调用者：负责统一调度命令与管理历史
        EditorInvoker invoker = new EditorInvoker();
        System.out.println("========== 创建编辑器调用者 ==========");
        invoker.displayHistory();
        System.out.println();

        // 测试1：插入命令（验证 execute 对文本前缀插入的效果）
        System.out.println("========== 测试1：插入文本命令 ==========");
        InsertCommand insertCmd = new InsertCommand(editor, 0, "学习");
        invoker.executeCommand(insertCmd);
        editor.display();
        System.out.println();

        // 测试2：复制命令（验证剪贴板更新）
        System.out.println("========== 测试2：复制文本命令 ==========");
        CopyCommand copyCmd = new CopyCommand(editor, 0, 2); // 复制"学习"
        invoker.executeCommand(copyCmd);
        editor.display();
        System.out.println();

        // 测试3：粘贴命令（验证剪贴板内容插入到末尾）
        System.out.println("========== 测试3：粘贴文本命令 ==========");
        PasteCommand pasteCmd = new PasteCommand(editor, editor.getText().length());
        invoker.executeCommand(pasteCmd);
        editor.display();
        System.out.println();

        // 测试4：剪切命令（验证正文删除 + 剪贴板更新）
        System.out.println("========== 测试4：剪切文本命令 ==========");
        CutCommand cutCmd = new CutCommand(editor, 2, 4); // 剪切"设计"
        invoker.executeCommand(cutCmd);
        editor.display();
        System.out.println();

        // 测试5：删除命令（验证区间删除）
        System.out.println("========== 测试5：删除文本命令 ==========");
        DeleteCommand deleteCmd = new DeleteCommand(editor, 0, 2); // 删除"学习"
        invoker.executeCommand(deleteCmd);
        editor.display();
        System.out.println();

        // 测试6：首次撤销（观察 commandHistory -> redoHistory 的迁移）
        System.out.println("========== 测试6：撤销操作 ==========");
        System.out.println("当前命令历史记录大小: " + invoker.getCommandHistorySize());
        System.out.println("执行撤销操作...");
        invoker.undo();
        editor.display();
        System.out.println("命令历史记录大小: " + invoker.getCommandHistorySize());
        System.out.println("重做历史记录大小: " + invoker.getRedoHistorySize());
        System.out.println();

        // 测试7：再次撤销（连续回滚）
        System.out.println("========== 测试7：再次撤销操作 ==========");
        System.out.println("执行撤销操作...");
        invoker.undo();
        editor.display();
        System.out.println("命令历史记录大小: " + invoker.getCommandHistorySize());
        System.out.println("重做历史记录大小: " + invoker.getRedoHistorySize());
        System.out.println();

        // 测试8：重做（将最近撤销命令重新执行）
        System.out.println("========== 测试8：重做操作 ==========");
        System.out.println("执行重做操作...");
        invoker.redo();
        editor.display();
        System.out.println("命令历史记录大小: " + invoker.getCommandHistorySize());
        System.out.println("重做历史记录大小: " + invoker.getRedoHistorySize());
        System.out.println();

        // 测试9：连续撤销多次（验证边界场景与栈状态）
        System.out.println("========== 测试9：连续撤销多次 ==========");
        System.out.println("连续执行3次撤销操作...");
        for (int i = 0; i < 3; i++) {
            invoker.undo();
        }
        editor.display();
        System.out.println("命令历史记录大小: " + invoker.getCommandHistorySize());
        System.out.println("重做历史记录大小: " + invoker.getRedoHistorySize());
        System.out.println();

        // 测试10：连续重做多次（验证可重做命令逐步消耗）
        System.out.println("========== 测试10：连续重做多次 ==========");
        System.out.println("连续执行2次重做操作...");
        for (int i = 0; i < 2; i++) {
            invoker.redo();
        }
        editor.display();
        System.out.println("命令历史记录大小: " + invoker.getCommandHistorySize());
        System.out.println("重做历史记录大小: " + invoker.getRedoHistorySize());
        System.out.println();

        // 测试11：清空历史（模拟“新文档”或“重置会话”）
        System.out.println("========== 测试11：清空历史记录 ==========");
        invoker.clearHistory();
        invoker.displayHistory();
        System.out.println();

        // 测试12：空历史撤销（验证防御性提示）
        System.out.println("========== 测试12：尝试撤销空历史 ==========");
        invoker.undo();
        System.out.println();

        // 测试13：宏命令思路（按顺序执行多个命令形成复合操作）
        System.out.println("========== 测试13：宏命令示例（组合命令） ==========");
        System.out.println("创建宏命令：插入文本 -> 复制 -> 粘贴");

        // 重置编辑器，避免前置场景干扰组合命令演示
        editor.setText("");
        System.out.println("重置编辑器文本为空");

        // 依次执行“插入 -> 复制 -> 粘贴”，模拟一个简单宏脚本
        System.out.println("执行一系列命令：");
        InsertCommand macroInsert = new InsertCommand(editor, 0, "Hello");
        invoker.executeCommand(macroInsert);

        CopyCommand macroCopy = new CopyCommand(editor, 0, 5); // 复制"Hello"
        invoker.executeCommand(macroCopy);

        PasteCommand macroPaste = new PasteCommand(editor, 5); // 在末尾粘贴
        invoker.executeCommand(macroPaste);

        editor.display();
        System.out.println();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  模式总结：                                                  ║");
        System.out.println("║  1. Command是命令接口，定义了execute()和undo()方法          ║");
        System.out.println("║  2. TextEditor是接收者，知道如何执行具体的文本操作          ║");
        System.out.println("║  3. CopyCommand、PasteCommand等是具体命令，绑定接收者和动作 ║");
        System.out.println("║  4. EditorInvoker是调用者，负责执行命令和管理历史记录       ║");
        System.out.println("║  5. 客户端通过调用者执行命令，与接收者解耦                  ║");
        System.out.println("║  6. 命令模式支持撤销/重做操作，提高了系统的灵活性           ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}

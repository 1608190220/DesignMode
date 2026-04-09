package com.design.mode.behavioral.command;

import java.util.Stack;

/**
 * 编辑器调用者
 * 负责调用命令对象执行请求。
 *
 * 在命令模式中，该类是 Invoker：
 * - 接收客户端提交的命令并触发 execute()
 * - 维护执行历史栈以支持 undo
 * - 维护重做栈以支持 redo
 *
 * 该实现使用两个栈模拟常见编辑器历史机制：
 * 1. commandHistory：保存已执行命令，栈顶是最近一次执行
 * 2. redoHistory：保存被撤销命令，栈顶是最近一次撤销
 */
public class EditorInvoker {
    private Stack<Command> commandHistory; // 已执行命令栈（用于撤销）
    private Stack<Command> redoHistory;    // 已撤销命令栈（用于重做）

    public EditorInvoker() {
        commandHistory = new Stack<>();
        redoHistory = new Stack<>();
    }

    /**
     * 执行命令
     * @param command 要执行的命令
     */
    public void executeCommand(Command command) {
        if (command != null) {
            command.execute();
            commandHistory.push(command);
            // 一旦出现新分支操作，旧 redo 链路失效，需清空
            redoHistory.clear();
            System.out.println("命令已执行并添加到历史记录");
        }
    }

    /**
     * 撤销上一个命令
     */
    public void undo() {
        if (!commandHistory.isEmpty()) {
            // 从执行栈弹出最近命令，先回滚，再压入 redo 栈
            Command lastCommand = commandHistory.pop();
            lastCommand.undo();
            redoHistory.push(lastCommand);
            System.out.println("已撤销上一个命令");
        } else {
            System.out.println("没有可撤销的命令");
        }
    }

    /**
     * 重做上一个撤销的命令
     */
    public void redo() {
        if (!redoHistory.isEmpty()) {
            // 从 redo 栈恢复最近撤销命令，并重新计入执行栈
            Command lastUndoneCommand = redoHistory.pop();
            lastUndoneCommand.execute();
            commandHistory.push(lastUndoneCommand);
            System.out.println("已重做上一个撤销的命令");
        } else {
            System.out.println("没有可重做的命令");
        }
    }

    /**
     * 获取执行历史命令数量。
     * @return commandHistory 栈中命令数
     */
    public int getCommandHistorySize() {
        return commandHistory.size();
    }

    /**
     * 获取可重做命令数量。
     * @return redoHistory 栈中命令数
     */
    public int getRedoHistorySize() {
        return redoHistory.size();
    }

    /**
     * 清空执行历史与重做历史。
     * 常用于场景重置或测试隔离。
     */
    public void clearHistory() {
        commandHistory.clear();
        redoHistory.clear();
        System.out.println("历史记录已清空");
    }

    /**
     * 打印当前历史记录状态。
     * 用于快速观察命令栈与重做栈是否符合预期。
     */
    public void displayHistory() {
        System.out.println("命令历史记录: " + commandHistory.size() + " 个命令");
        System.out.println("重做历史记录: " + redoHistory.size() + " 个命令");
    }
}

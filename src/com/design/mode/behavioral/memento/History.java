package com.design.mode.behavioral.memento;

import java.util.Stack;

/**
 * 历史记录管理器 - 管理者（Caretaker）
 *
 * 该类负责维护备忘录的生命周期，但不直接修改备忘录内部数据。
 * 在当前示例中，它通过撤销栈与重做栈实现：
 * 1. 状态保存
 * 2. 撤销操作
 * 3. 重做操作
 * 4. 检查点管理
 */
public class History {
    /** 撤销栈，栈顶表示当前状态 */
    private Stack<EditorMemento> undoStack;
    /** 重做栈，保存被撤销后可恢复的状态 */
    private Stack<EditorMemento> redoStack;
    /** 最大历史记录数，用于限制内存占用 */
    private final int maxHistorySize;

    /**
     * 使用默认历史容量创建管理器
     */
    public History() {
        this(50);
    }

    /**
     * 使用指定历史容量创建管理器
     *
     * @param maxHistorySize 最大历史记录数，小于等于 0 时使用默认值 50
     */
    public History(int maxHistorySize) {
        this.undoStack = new Stack<>();
        this.redoStack = new Stack<>();
        this.maxHistorySize = maxHistorySize > 0 ? maxHistorySize : 50;
    }

    /**
     * 保存状态到历史记录
     *
     * 每次保存新状态时都会清空重做栈，
     * 因为新的编辑路径已经改变，旧的“未来状态”不再有效。
     *
     * @param memento 备忘录
     */
    public void save(EditorMemento memento) {
        if (memento != null) {
            undoStack.push(memento);
            redoStack.clear();

            // 控制历史容量，避免无限增长
            if (undoStack.size() > maxHistorySize) {
                // 通过重建栈的方式移除最旧状态（栈底元素）
                Stack<EditorMemento> newStack = new Stack<>();
                for (int i = 1; i < undoStack.size(); i++) {
                    newStack.push(undoStack.get(i));
                }
                undoStack = newStack;
                System.out.println("历史记录已满，移除了最旧的记录");
            }

            System.out.println("状态已保存到历史记录");
            displayHistory();
        }
    }

    /**
     * 撤销操作
     *
     * 逻辑说明：
     * 1. 当前状态从撤销栈弹出并压入重做栈；
     * 2. 撤销栈新的栈顶即为“撤销后的状态”；
     * 3. 若撤销后无历史状态，则表示回到初始状态。
     *
     * @return 上一个状态的备忘录，如果没有可撤销的操作返回null
     */
    public EditorMemento undo() {
        if (!undoStack.isEmpty()) {
            EditorMemento currentState = undoStack.pop();
            redoStack.push(currentState);

            EditorMemento previousState = undoStack.isEmpty() ? null : undoStack.peek();
            System.out.println("执行撤销操作");

            if (previousState != null) {
                System.out.println("恢复到状态:");
                previousState.display();
            } else {
                System.out.println("已恢复到初始状态");
            }

            displayHistory();
            return previousState;
        } else {
            System.out.println("没有可撤销的操作");
            return null;
        }
    }

    /**
     * 重做操作
     *
     * 逻辑说明：
     * 1. 从重做栈取出下一个状态；
     * 2. 放回撤销栈作为当前状态；
     * 3. 返回该状态供发起人恢复。
     *
     * @return 下一个状态的备忘录，如果没有可重做的操作返回null
     */
    public EditorMemento redo() {
        if (!redoStack.isEmpty()) {
            EditorMemento nextState = redoStack.pop();
            undoStack.push(nextState);

            System.out.println("执行重做操作");
            System.out.println("恢复到状态:");
            nextState.display();

            displayHistory();
            return nextState;
        } else {
            System.out.println("没有可重做的操作");
            return null;
        }
    }

    /**
     * 清空历史记录
     *
     * 会同时清空撤销栈与重做栈。
     */
    public void clear() {
        undoStack.clear();
        redoStack.clear();
        System.out.println("历史记录已清空");
    }

    /**
     * 获取可撤销的次数
     *
     * 当前状态本身不计入“可撤销次数”，
     * 因此使用总数减 1 的方式计算。
     *
     * @return 可撤销次数
     */
    public int getUndoCount() {
        return Math.max(0, undoStack.size() - 1);
    }

    /**
     * 获取可重做的次数
     *
     * @return 可重做次数
     */
    public int getRedoCount() {
        return redoStack.size();
    }

    /**
     * 获取当前状态
     *
     * @return 当前状态的备忘录
     */
    public EditorMemento getCurrentState() {
        return undoStack.isEmpty() ? null : undoStack.peek();
    }

    /**
     * 获取所有历史状态
     *
     * 返回副本而不是原始栈，避免外部直接破坏内部历史结构。
     *
     * @return 历史状态副本
     */
    public Stack<EditorMemento> getAllStates() {
        return new Stack<EditorMemento>() {{
            addAll(undoStack);
        }};
    }

    /**
     * 显示历史记录状态
     *
     * 用于演示当前撤销/重做体系的结构情况。
     */
    public void displayHistory() {
        System.out.println("=== 历史记录状态 ===");
        System.out.println("可撤销次数: " + getUndoCount());
        System.out.println("可重做次数: " + getRedoCount());
        System.out.println("总历史记录数: " + undoStack.size());

        if (!undoStack.isEmpty()) {
            System.out.println("当前状态:");
            undoStack.peek().display();
        }

        System.out.println("==================");
        System.out.println();
    }

    /**
     * 保存检查点（用于复杂操作）
     *
     * 检查点适合在重要编辑节点进行人工标记，
     * 便于后续快速回退到关键版本。
     *
     * @param name 检查点名称
     * @param editor 编辑器
     * @return 检查点的备忘录
     */
    public EditorMemento saveCheckpoint(String name, TextEditor editor) {
        if (editor != null) {
            EditorMemento checkpoint = editor.createMemento();
            undoStack.push(checkpoint);
            redoStack.clear();

            System.out.println("保存检查点: " + name);
            checkpoint.display();
            displayHistory();

            return checkpoint;
        }
        return null;
    }

    /**
     * 恢复到指定检查点
     *
     * 恢复逻辑只负责调整历史结构，
     * 具体编辑器内容恢复仍由发起人自行执行。
     *
     * @param checkpoint 检查点备忘录
     * @return 是否恢复成功
     */
    public boolean restoreToCheckpoint(EditorMemento checkpoint) {
        if (checkpoint != null && undoStack.contains(checkpoint)) {
            // 恢复检查点后，原先的“未来状态”不再有效
            redoStack.clear();

            // 回退到检查点，将其之后的状态全部丢弃
            while (!undoStack.isEmpty() && undoStack.peek() != checkpoint) {
                undoStack.pop();
            }

            System.out.println("恢复到检查点:");
            checkpoint.display();
            displayHistory();

            return true;
        } else {
            System.out.println("检查点不存在或无效");
            return false;
        }
    }
}

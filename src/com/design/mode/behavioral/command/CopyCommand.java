package com.design.mode.behavioral.command;

/**
 * 复制命令。
 *
 * 职责说明：
 * - execute()：将指定区间文本复制到 TextEditor 剪贴板
 * - undo()：复制不会改变正文内容，因此本示例中不可逆，仅给出提示
 */
public class CopyCommand implements Command {
    private TextEditor editor; // 命令接收者，执行实际复制动作
    private int start;         // 复制起始索引（含）
    private int end;           // 复制结束索引（不含）

    public CopyCommand(TextEditor editor, int start, int end) {
        this.editor = editor;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute() {
        // 委托接收者执行复制，参数合法性由 TextEditor 统一校验
        editor.copy(start, end);
    }

    @Override
    public void undo() {
        // 复制只改变剪贴板语义，不改变正文；此处保留教学层面的不可撤销提示
        System.out.println("复制操作不可撤销");
    }

    /**
     * 获取复制起始位置。
     * @return 起始索引（含）
     */
    public int getStart() {
        return start;
    }

    /**
     * 获取复制结束位置。
     * @return 结束索引（不含）
     */
    public int getEnd() {
        return end;
    }
}

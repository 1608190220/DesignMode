package com.design.mode.behavioral.command;

/**
 * 删除命令。
 *
 * 行为特征：
 * - execute() 删除指定文本区间
 * - undo() 依据执行前缓存的 deletedText 回填删除内容
 */
public class DeleteCommand implements Command {
    private TextEditor editor; // 命令接收者
    private int start;         // 删除起始索引（含）
    private int end;           // 删除结束索引（不含）
    private String deletedText; // 执行时缓存的删除片段（用于撤销）

    public DeleteCommand(TextEditor editor, int start, int end) {
        this.editor = editor;
        this.start = start;
        this.end = end;
        this.deletedText = null;
    }

    @Override
    public void execute() {
        // 执行前先提取即将删除的区间文本，确保 undo() 可恢复
        if (start >= 0 && end <= editor.getText().length() && start < end) {
            deletedText = editor.getText().substring(start, end);
        }
        editor.delete(start, end);
    }

    @Override
    public void undo() {
        if (deletedText != null) {
            // 将缓存片段插回原起点，实现删除动作逆操作
            editor.insert(start, deletedText);
            System.out.println("已撤销删除操作，恢复文本: \"" + deletedText + "\"");
        } else {
            System.out.println("无法撤销删除操作：没有保存被删除的文本");
        }
    }

    /**
     * 获取删除起始位置。
     * @return 起始索引（含）
     */
    public int getStart() {
        return start;
    }

    /**
     * 获取删除结束位置。
     * @return 结束索引（不含）
     */
    public int getEnd() {
        return end;
    }

    /**
     * 获取本次命令缓存的删除文本。
     * @return 被删除文本，未成功缓存时可能为 null
     */
    public String getDeletedText() {
        return deletedText;
    }
}

package com.design.mode.behavioral.command;

/**
 * 剪切命令。
 *
 * 行为特征：
 * - 执行时删除指定区间文本，并同步更新剪贴板
 * - 撤销时在原位置重新插入执行阶段保存的文本快照
 */
public class CutCommand implements Command {
    private TextEditor editor; // 命令接收者
    private int start;         // 剪切起始索引（含）
    private int end;           // 剪切结束索引（不含）
    private String cutText;    // 执行时缓存的剪切内容，用于撤销补偿

    public CutCommand(TextEditor editor, int start, int end) {
        this.editor = editor;
        this.start = start;
        this.end = end;
        this.cutText = null;
    }

    @Override
    public void execute() {
        // 在真正剪切前缓存目标区间文本，供 undo() 恢复
        if (start >= 0 && end <= editor.getText().length() && start < end) {
            cutText = editor.getText().substring(start, end);
        }
        editor.cut(start, end);
    }

    @Override
    public void undo() {
        if (cutText != null) {
            // 将缓存片段回填到原始起点，实现对剪切动作的逆操作
            editor.insert(start, cutText);
            System.out.println("已撤销剪切操作，恢复文本: \"" + cutText + "\"");
        } else {
            System.out.println("无法撤销剪切操作：没有保存被剪切的文本");
        }
    }

    /**
     * 获取剪切起始位置。
     * @return 起始索引（含）
     */
    public int getStart() {
        return start;
    }

    /**
     * 获取剪切结束位置。
     * @return 结束索引（不含）
     */
    public int getEnd() {
        return end;
    }

    /**
     * 获取本次命令缓存的剪切文本。
     * @return 被剪切文本，未成功缓存时可能为 null
     */
    public String getCutText() {
        return cutText;
    }
}

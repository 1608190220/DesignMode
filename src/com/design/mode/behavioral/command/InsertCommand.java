package com.design.mode.behavioral.command;

/**
 * 插入命令。
 *
 * 行为特征：
 * - execute() 在给定位置插入指定文本
 * - undo() 校验文本一致后删除对应片段，尽量恢复到执行前状态
 */
public class InsertCommand implements Command {
    private TextEditor editor; // 命令接收者
    private int position;      // 插入起点
    private String textToInsert; // 计划插入的文本内容
    private boolean executed = false; // execute() 是否已成功触发过

    public InsertCommand(TextEditor editor, int position, String textToInsert) {
        this.editor = editor;
        this.position = position;
        this.textToInsert = textToInsert;
    }

    @Override
    public void execute() {
        // 正向执行：将文本插入到指定位置
        editor.insert(position, textToInsert);
        executed = true;
    }

    @Override
    public void undo() {
        if (executed && textToInsert != null && !textToInsert.isEmpty()) {
            // 逆向补偿：删除 execute() 时插入的同长度片段
            int deleteStart = position;
            int deleteEnd = position + textToInsert.length();

            // 为避免误删，先确认当前位置文本与原插入内容一致
            String currentText = editor.getText();
            if (deleteEnd <= currentText.length()) {
                String insertedText = currentText.substring(deleteStart, deleteEnd);
                if (insertedText.equals(textToInsert)) {
                    editor.delete(deleteStart, deleteEnd);
                    System.out.println("已撤销插入操作，删除文本: \"" + textToInsert + "\"");
                } else {
                    System.out.println("无法撤销插入操作：文本不匹配");
                }
            } else {
                System.out.println("无法撤销插入操作：文本范围无效");
            }
        } else {
            System.out.println("无法撤销插入操作：命令未执行或文本为空");
        }
    }

    /**
     * 获取插入位置。
     * @return 插入起点索引
     */
    public int getPosition() {
        return position;
    }

    /**
     * 获取待插入文本。
     * @return 插入文本内容
     */
    public String getTextToInsert() {
        return textToInsert;
    }

    /**
     * 查询命令是否执行过。
     * @return true 表示 execute() 已调用
     */
    public boolean isExecuted() {
        return executed;
    }
}

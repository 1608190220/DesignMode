package com.design.mode.behavioral.command;

/**
 * 粘贴命令。
 *
 * 职责说明：
 * - execute()：在指定位置插入剪贴板内容
 * - undo()：理论上需删除本次粘贴片段；本示例未保存完整快照，采用提示方式说明
 */
public class PasteCommand implements Command {
    private TextEditor editor; // 命令接收者，处理实际粘贴
    private int position;      // 目标粘贴位置

    public PasteCommand(TextEditor editor, int position) {
        this.editor = editor;
        this.position = position;
    }

    @Override
    public void execute() {
        // 委托接收者处理粘贴，并由接收者执行边界与剪贴板校验
        editor.paste(position);
    }

    @Override
    public void undo() {
        // 若要精确撤销，应记录粘贴前后状态或记录“插入长度 + 插入位置”
        System.out.println("撤销粘贴操作（实际实现需要记录粘贴的内容和位置）");
    }

    /**
     * 获取粘贴位置。
     * @return 粘贴目标索引
     */
    public int getPosition() {
        return position;
    }
}

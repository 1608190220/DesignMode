package com.design.mode.behavioral.command;

/**
 * 文本编辑器 - 接收者类
 * 知道如何执行与请求相关的操作。
 *
 * 该类是命令模式中的 Receiver，封装了真实文本处理逻辑：
 * - 文本读写（set/get）
 * - 复制、粘贴、剪切、删除、插入
 * - 记录最后一次删除内容用于简单撤销演示
 *
 * 注意：本示例聚焦模式结构，状态管理为教学简化实现，不追求工业级编辑器能力。
 */
public class TextEditor {
    private String text;          // 当前编辑区文本
    private String clipboard;     // 剪贴板中的文本快照
    private String lastDeleted;   // 最近一次 delete/cut 的文本快照（用于演示撤销）

    public TextEditor() {
        this.text = "";
        this.clipboard = "";
        this.lastDeleted = "";
    }

    /**
     * 获取当前文本内容。
     * @return 编辑器内当前完整文本
     */
    public String getText() {
        return text;
    }

    /**
     * 直接设置编辑器文本。
     * 常用于测试初始化或场景重置。
     * @param text 新的完整文本
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 复制选中的文本到剪贴板
     * @param start 起始位置
     * @param end   结束位置（开区间）
     */
    public void copy(int start, int end) {
        if (start >= 0 && end <= text.length() && start < end) {
            clipboard = text.substring(start, end);
            System.out.println("已复制文本: \"" + clipboard + "\" 到剪贴板");
        } else {
            System.out.println("复制失败：无效的选择范围");
        }
    }

    /**
     * 粘贴剪贴板内容到指定位置
     * @param position 插入位置
     */
    public void paste(int position) {
        // 同时校验位置合法与剪贴板非空，避免无意义拼接
        if (position >= 0 && position <= text.length() && !clipboard.isEmpty()) {
            // 采用“前缀 + 剪贴板 + 后缀”方式构造新字符串
            String before = text.substring(0, position);
            String after = text.substring(position);
            text = before + clipboard + after;
            System.out.println("已粘贴文本: \"" + clipboard + "\" 到位置 " + position);
        } else {
            System.out.println("粘贴失败：无效的位置或剪贴板为空");
        }
    }

    /**
     * 剪切选中的文本
     * @param start 起始位置
     * @param end   结束位置（开区间）
     */
    public void cut(int start, int end) {
        if (start >= 0 && end <= text.length() && start < end) {
            // 先保存被剪切内容，便于展示与撤销
            lastDeleted = text.substring(start, end);
            clipboard = lastDeleted;

            // 删除选中区间
            String before = text.substring(0, start);
            String after = text.substring(end);
            text = before + after;

            System.out.println("已剪切文本: \"" + lastDeleted + "\"");
        } else {
            System.out.println("剪切失败：无效的选择范围");
        }
    }

    /**
     * 删除选中的文本
     * @param start 起始位置
     * @param end   结束位置（开区间）
     */
    public void delete(int start, int end) {
        if (start >= 0 && end <= text.length() && start < end) {
            // 保存删除片段，供 undoDelete 演示恢复使用
            lastDeleted = text.substring(start, end);

            String before = text.substring(0, start);
            String after = text.substring(end);
            text = before + after;

            System.out.println("已删除文本: \"" + lastDeleted + "\"");
        } else {
            System.out.println("删除失败：无效的选择范围");
        }
    }

    /**
     * 在指定位置插入文本
     * @param position 插入位置
     * @param newText  要插入的文本
     */
    public void insert(int position, String newText) {
        if (position >= 0 && position <= text.length() && newText != null) {
            // 插入本质是将原文本拆分后再拼接
            String before = text.substring(0, position);
            String after = text.substring(position);
            text = before + newText + after;
            System.out.println("已在位置 " + position + " 插入文本: \"" + newText + "\"");
        } else {
            System.out.println("插入失败：无效的位置或文本");
        }
    }

    /**
     * 撤销上次删除操作。
     * 说明：该方法仅演示最近一次删除恢复，非完整多步撤销栈实现。
     */
    public void undoDelete(int position) {
        if (position >= 0 && position <= text.length() && !lastDeleted.isEmpty()) {
            String before = text.substring(0, position);
            String after = text.substring(position);
            text = before + lastDeleted + after;
            System.out.println("已撤销删除，恢复文本: \"" + lastDeleted + "\"");
            lastDeleted = "";
        } else {
            System.out.println("撤销失败：没有可撤销的操作");
        }
    }

    /**
     * 显示当前文本状态。
     * 输出包含文本、剪贴板和最近删除片段，便于观察命令执行效果。
     */
    public void display() {
        System.out.println("当前文本内容: \"" + text + "\"");
        System.out.println("剪贴板内容: \"" + clipboard + "\"");
        System.out.println("最后删除的内容: \"" + lastDeleted + "\"");
    }
}

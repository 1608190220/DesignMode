package com.design.mode.behavioral.memento;

/**
 * 文本编辑器 - 发起人（Originator）
 *
 * 该类代表备忘录模式中的发起人角色，
 * 负责维护真实业务状态，并提供：
 * 1. 创建备忘录
 * 2. 从备忘录恢复
 * 3. 执行编辑操作
 */
public class TextEditor {
    /** 当前文本内容 */
    private String content;
    /** 当前光标位置 */
    private int cursorPosition;
    /** 当前选中文本 */
    private String selection;

    /**
     * 创建一个空编辑器实例
     */
    public TextEditor() {
        this.content = "";
        this.cursorPosition = 0;
        this.selection = "";
    }

    /**
     * 输入文本
     *
     * 在当前光标位置插入文本，并将光标移动到插入内容之后。
     * 输入新文本后会清空当前选区。
     *
     * @param text 要输入的文本
     */
    public void type(String text) {
        if (text != null && !text.isEmpty()) {
            // 按“光标前 + 新文本 + 光标后”的方式重组内容
            String before = content.substring(0, cursorPosition);
            String after = content.substring(cursorPosition);
            content = before + text + after;
            cursorPosition += text.length();
            selection = "";

            System.out.println("输入文本: \"" + text + "\"");
            display();
        }
    }

    /**
     * 删除文本
     *
     * 约定：
     * - 正数表示从光标位置向后删除；
     * - 负数表示从光标位置向前删除。
     *
     * @param length 删除的长度（负数表示向前删除）
     */
    public void delete(int length) {
        if (length == 0 || content.isEmpty()) {
            return;
        }

        if (length > 0) {
            // 从光标位置向后删除指定长度的文本
            if (cursorPosition + length <= content.length()) {
                String before = content.substring(0, cursorPosition);
                String after = content.substring(cursorPosition + length);
                selection = content.substring(cursorPosition, cursorPosition + length);
                content = before + after;
                System.out.println("向后删除: \"" + selection + "\"");
            }
        } else {
            // 从光标前方向前删除指定长度的文本
            length = -length;
            if (cursorPosition >= length) {
                int start = cursorPosition - length;
                String before = content.substring(0, start);
                String after = content.substring(cursorPosition);
                selection = content.substring(start, cursorPosition);
                content = before + after;
                cursorPosition = start;
                System.out.println("向前删除: \"" + selection + "\"");
            }
        }

        display();
    }

    /**
     * 移动光标
     *
     * @param position 目标位置
     */
    public void moveCursor(int position) {
        if (position >= 0 && position <= content.length()) {
            cursorPosition = position;
            selection = "";
            System.out.println("移动光标到位置: " + position);
            display();
        } else {
            System.out.println("光标位置无效: " + position);
        }
    }

    /**
     * 选中文本
     *
     * @param start 起始位置
     * @param end   结束位置
     */
    public void select(int start, int end) {
        if (start >= 0 && end <= content.length() && start < end) {
            cursorPosition = end;
            selection = content.substring(start, end);
            System.out.println("选中文本: \"" + selection + "\" (位置: " + start + "-" + end + ")");
            display();
        } else {
            System.out.println("选择范围无效: " + start + "-" + end);
        }
    }

    /**
     * 替换选中文本
     *
     * 简化实现中通过查找当前 selection 在内容中的位置进行替换，
     * 用于演示备忘录模式，不强调复杂编辑器中的精确选区模型。
     *
     * @param newText 新文本
     */
    public void replaceSelection(String newText) {
        if (selection != null && !selection.isEmpty()) {
            // 查找当前选中文本第一次出现的位置并执行替换
            int start = content.indexOf(selection);
            if (start != -1) {
                String before = content.substring(0, start);
                String after = content.substring(start + selection.length());
                content = before + (newText != null ? newText : "") + after;
                cursorPosition = start + (newText != null ? newText.length() : 0);
                System.out.println("替换选中文本: \"" + selection + "\" -> \"" + newText + "\"");
                selection = "";
                display();
            }
        } else {
            System.out.println("没有选中文本");
        }
    }

    /**
     * 创建备忘录
     *
     * 当前编辑器状态被封装为不可变快照对象，
     * 交由管理者保存。
     *
     * @return 备忘录对象
     */
    public EditorMemento createMemento() {
        System.out.println("创建备忘录...");
        return new EditorMemento(content, cursorPosition, selection);
    }

    /**
     * 从备忘录恢复状态
     *
     * 该方法将编辑器恢复为某次快照保存时的完整状态。
     *
     * @param memento 备忘录对象
     */
    public void restoreFromMemento(EditorMemento memento) {
        if (memento != null) {
            this.content = memento.getContent();
            this.cursorPosition = memento.getCursorPosition();
            this.selection = memento.getSelection();
            System.out.println("从备忘录恢复状态:");
            memento.display();
            display();
        }
    }

    /**
     * 显示编辑器状态
     *
     * 用于示例输出和调试观察。
     */
    public void display() {
        System.out.println("编辑器状态:");
        System.out.println("  内容: \"" + content + "\"");
        System.out.println("  长度: " + content.length() + " 字符");
        System.out.println("  光标: 位置 " + cursorPosition);

        if (selection != null && !selection.isEmpty()) {
            System.out.println("  选中: \"" + selection + "\"");
        } else {
            System.out.println("  选中: 无");
        }
        System.out.println();
    }

    /**
     * 获取当前文本内容
     *
     * @return 文本内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 获取当前光标位置
     *
     * @return 光标位置
     */
    public int getCursorPosition() {
        return cursorPosition;
    }

    /**
     * 获取当前选中文本
     *
     * @return 选中文本
     */
    public String getSelection() {
        return selection;
    }
}

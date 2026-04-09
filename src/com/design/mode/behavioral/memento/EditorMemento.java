package com.design.mode.behavioral.memento;

/**
 * 编辑器备忘录
 *
 * 该类是备忘录模式中的备忘录（Memento）角色，
 * 专门负责保存文本编辑器在某一时刻的快照信息。
 * 它只负责“存状态”，不负责发起编辑动作或管理历史记录。
 *
 * 备忘录模式（Memento Pattern）详解：
 *
 * 一、模式意图
 * 在不破坏封装性的前提下，捕获一个对象的内部状态，并在该对象之外保存这个状态。
 * 这样以后就可将该对象恢复到原先保存的状态。
 *
 * 二、模式结构
 * 1. 发起人（Originator）: 创建一个备忘录，用以记录当前时刻它的内部状态，并可使用备忘录恢复内部状态，
 *    这里是TextEditor
 * 2. 备忘录（Memento）: 存储发起人的内部状态，这里是EditorMemento
 * 3. 管理者（Caretaker）: 负责保存备忘录，但不能对备忘录的内容进行操作或检查，
 *    这里是History
 *
 * 三、适用场景
 * 1. 必须保存一个对象在某一个时刻的状态，这样以后需要时它才能恢复到先前的状态
 * 2. 如果一个用接口来让其他对象直接得到这些状态，将会暴露对象的实现细节并破坏对象的封装性
 *
 * 四、优缺点
 * 优点：
 * - 提供了一种状态恢复的实现机制，使得用户可以方便地回到某个历史状态
 * - 实现了信息的封装，用户不需要关心状态的保存细节
 *
 * 缺点：
 * - 如果发起人需要保存的状态数据过多，会占用比较大的存储资源
 * - 当发起人的状态改变时，有可能这个状态无效，这时候使用备忘录模式将无法恢复到该状态
 *
 * 五、实际应用
 * - 文本编辑器的撤销/重做功能
 * - 游戏存档
 * - 数据库事务回滚
 * - 浏览器历史记录
 */
public class EditorMemento {
    /** 文本内容快照 */
    private final String content;
    /** 光标位置快照 */
    private final int cursorPosition;
    /** 选中文本快照 */
    private final String selection;
    /** 创建该快照时的时间戳 */
    private final long timestamp;

    /**
     * 构造备忘录对象
     *
     * 在创建时立即固化编辑器当前状态，确保后续恢复时可获得一致快照。
     *
     * @param content 文本内容
     * @param cursorPosition 光标位置
     * @param selection 选中文本
     */
    public EditorMemento(String content, int cursorPosition, String selection) {
        this.content = content;
        this.cursorPosition = cursorPosition;
        this.selection = selection;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 获取快照中的文本内容
     *
     * @return 文本内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 获取快照中的光标位置
     *
     * @return 光标位置
     */
    public int getCursorPosition() {
        return cursorPosition;
    }

    /**
     * 获取快照中的选中文本
     *
     * @return 选中文本
     */
    public String getSelection() {
        return selection;
    }

    /**
     * 获取快照创建时间
     *
     * @return 时间戳
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * 显示备忘录信息
     *
     * 主要用于示例演示与调试，帮助观察当前保存的状态内容。
     */
    public void display() {
        System.out.println("备忘录信息:");
        System.out.println("  时间: " + new java.util.Date(timestamp));
        System.out.println("  内容长度: " + (content != null ? content.length() : 0) + " 字符");
        System.out.println("  光标位置: " + cursorPosition);
        System.out.println("  选中文本: \"" + (selection != null ? selection : "") + "\"");
    }
}

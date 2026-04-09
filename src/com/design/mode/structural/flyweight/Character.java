package com.design.mode.structural.flyweight;

/**
 * 字符享元类（Flyweight）
 * 
 * 享元模式中的具体享元角色，负责存储字符的内部状态。
 * 
 * 内部状态（Intrinsic State）：
 * - charCode：字符代码，如 'A'、'B' 等，是不变的，可在多个上下文中共享
 * - fontFamily：字体名称，如 "Arial"、"Times New Roman" 等，也是不变的
 * 
 * 外部状态（Extrinsic State）：
 * - 位置坐标 (x, y)
 * - 字体大小
 * - 颜色
 * 这些状态由客户端在使用时传入，不存储在享元对象中，因此是可变的。
 * 
 * 设计原则：
 * - 内部状态使用 final 修饰，确保不可变性
 * - 不提供 setter 方法，防止内部状态被修改
 * - 通过构造函数初始化内部状态
 */
public class Character {
    
    /**
     * 字符代码（内部状态）
     * 例如：'A'、'B'、'1' 等字符
     * 使用 final 修饰，确保不可变性，这是享元模式的关键
     */
    private final char charCode;
    
    /**
     * 字体名称（内部状态）
     * 例如："Arial"、"Times New Roman"、"Courier New" 等
     * 同样使用 final 修饰，确保不可变性
     */
    private final String fontFamily;

    /**
     * 构造函数，初始化字符享元对象
     * 
     * @param charCode 字符代码，如 'A'
     * @param fontFamily 字体名称，如 "Arial"
     */
    public Character(char charCode, String fontFamily) {
        this.charCode = charCode;
        this.fontFamily = fontFamily;
    }

    /**
     * 显示字符的方法
     * 
     * 该方法接收外部状态作为参数，将内部状态与外部状态结合起来使用。
     * 这是享元模式的核心：外部状态不存储在享元对象中，而是在使用时传入。
     * 
     * @param x 水平位置坐标（外部状态）
     * @param y 垂直位置坐标（外部状态）
     * @param fontSize 字体大小（外部状态）
     * @param color 字体颜色（外部状态）
     */
    public void display(int x, int y, int fontSize, String color) {
        System.out.printf("字符 '%c' [字体: %s, 大小: %d, 颜色: %s] 显示在位置 (%d, %d)%n",
                charCode, fontFamily, fontSize, color, x, y);
    }

    /**
     * 获取字符代码
     * 
     * @return 字符代码，如 'A'
     */
    public char getCharCode() {
        return charCode;
    }

    /**
     * 获取字体名称
     * 
     * @return 字体名称，如 "Arial"
     */
    public String getFontFamily() {
        return fontFamily;
    }
}
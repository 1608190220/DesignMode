package com.design.mode.creational.abstractfactory.product;

/**
 * 抽象产品B：复选框
 *
 * 定义复选框组件的统一行为契约。
 * 具体平台实现（如 WindowsCheckbox / MacCheckbox）只需遵循此契约，
 * 客户端即可通过多态使用，不关心底层细节。
 */
public interface Checkbox {

    /**
     * 复选框的渲染方法
     *
     * 负责展示当前选中状态对应的视觉效果。
     */
    void render();

    /**
     * 复选框的选中状态
     *
     * @return true 表示已选中，false 表示未选中
     */
    boolean isChecked();

    /**
     * 设置复选框的选中状态
     *
     * @param checked 目标状态，true为选中，false为取消选中
     */
    void setChecked(boolean checked);
}

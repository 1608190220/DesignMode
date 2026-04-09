package com.design.mode.creational.abstractfactory.product.impl;

import com.design.mode.creational.abstractfactory.product.Checkbox;

/**
 * 具体产品：Mac风格的复选框
 *
 * 该类提供 Mac 风格复选框的状态管理与渲染逻辑。
 * 通过 checked 字段维护当前选中状态，并在 render() 中体现视觉差异。
 */
public class MacCheckbox implements Checkbox {

    private boolean checked = false;

    @Override
    public void render() {
        System.out.println("渲染 Mac 风格的复选框：");
        // 使用不同符号模拟选中与未选中的视觉状态
        String box = checked ? "✓" : "○";
        System.out.println(" └─ " + box + " Mac 复选框");
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
        // 输出状态变更结果，便于演示交互过程
        System.out.println("Mac 复选框" + (checked ? "选中" : "取消选中"));
    }
}

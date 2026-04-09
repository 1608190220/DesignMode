package com.design.mode.creational.abstractfactory.product.impl;

import com.design.mode.creational.abstractfactory.product.Checkbox;

/**
 * 具体产品：Windows风格的复选框
 *
 * 该类实现 Checkbox 接口，负责维护并展示 Windows 风格复选框状态。
 * 通过 checked 字段表示当前是否选中，便于业务层读取或切换状态。
 */
public class WindowsCheckbox implements Checkbox {

    private boolean checked = false;

    @Override
    public void render() {
        System.out.println("渲染 Windows 风格的复选框：");
        // 使用 [X]/[ ] 模拟 Windows 常见复选框样式
        String box = checked ? "[X]" : "[ ]";  // X表示选中，空格表示未选中
        System.out.println(" └─ " + box + " Windows 复选框");
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void setChecked(boolean checked) {
        this.checked = checked;
        // 输出状态变化结果，便于测试观察
        System.out.println("Windows 复选框" + (checked ? "选中" : "取消选中"));
    }
}

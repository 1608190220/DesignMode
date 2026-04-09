package com.design.mode.behavioral.visitor;

import java.util.ArrayList;
import java.util.List;

/**
 * 对象结构（Object Structure）- 员工列表
 *
 * 该类负责维护员工元素集合，并提供统一的访问入口。
 * 在访问者模式里，对象结构的核心职责是：
 * 1. 管理元素生命周期（添加、移除、遍历）。
 * 2. 将访问者分发给每一个元素，触发元素与访问者的协作。
 * 3. 对外隐藏集合实现细节，降低调用方对容器结构的耦合。
 */
public class EmployeeList {
    /**
     * 内部员工集合。
     * 使用 List 便于保持插入顺序，保证报表输出顺序可预期。
     */
    private List<Employee> employees = new ArrayList<>();
    
    /**
     * 添加员工到对象结构
     *
     * @param employee 员工对象
     */
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }
    
    /**
     * 从对象结构中移除员工
     *
     * @param employee 员工对象
     */
    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }
    
    /**
     * 接受访问者访问并分发到所有员工元素
     *
     * 该方法体现了对象结构在访问者模式中的关键作用：
     * 统一遍历元素，并调用每个元素的 {@code accept}，
     * 从而让访问者对不同元素执行相应处理逻辑。
     *
     * @param visitor 访问者对象
     */
    public void accept(Visitor visitor) {
        for (Employee employee : employees) {
            // 让每个元素触发双分派，进入访问者对应的 visit 重载方法
            employee.accept(visitor);
        }
    }
    
    /**
     * 获取员工列表副本
     *
     * 返回副本而非内部引用，避免外部直接修改内部集合，
     * 保护对象结构的封装性。
     *
     * @return 员工列表副本
     */
    public List<Employee> getEmployees() {
        return new ArrayList<>(employees);
    }
    
    /**
     * 获取当前员工数量
     *
     * @return 员工数量
     */
    public int getEmployeeCount() {
        return employees.size();
    }
}

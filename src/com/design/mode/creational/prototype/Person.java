package com.design.mode.creational.prototype;

import com.design.mode.creational.prototype.Address;
import com.design.mode.creational.prototype.Prototype;

/**
 * 具体原型类：人（演示浅拷贝的问题）
 * 
 * 这是一个具体原型类，用于演示浅拷贝（Shallow Copy）的问题。
 * 它实现了Prototype接口和Cloneable接口。
 * 
 * 浅拷贝（Shallow Copy）的定义：
 * 1. 创建一个新对象，然后将当前对象的非静态字段复制到新对象
 * 2. 如果字段是值类型（如int、String），则进行值复制
 * 3. 如果字段是引用类型（如Address），则复制引用，但不复制引用的对象
 * 4. 原始对象和拷贝对象共享引用类型的对象
 * 
 * 浅拷贝的问题：
 * - 修改克隆对象的引用类型字段会影响原始对象
 * - 因为它们共享同一个引用对象
 * 
 * Cloneable接口的作用：
 * - 标记接口，表示该类可以被克隆
 * - 如果没有实现Cloneable接口，调用clone()会抛出CloneNotSupportedException
 * - Object.clone()是native方法，性能很好
 */
public class Person implements Prototype<Person>, Cloneable {

    private String name;
    private int age;
    private Address address;  // 引用类型，浅拷贝会共享这个对象

    public Person(String name, int age, Address address) {
        this.name = name;
        this.age = age;
        this.address = address;
    }

    /**
     * 克隆方法（浅拷贝）
     * 
     * 调用 Object 的 clone() 方法实现浅拷贝。
     * 这是一个native方法，基于内存二进制流复制，性能很好。
     * 
     * 注意：
     * - 这个方法只进行浅拷贝
     * - address字段只是复制引用，不复制Address对象
     * 
     * @return 浅拷贝的Person对象
     */
    @Override
    public Person clone() {
        try {
            // 调用 Object 的 clone() 方法实现浅拷贝
            return (Person) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Person克隆失败", e);
        }
    }

    @Override
    public String toString() {
        return "Person{name='" + name + "', age=" + age + ", address=" + address + "}";
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}

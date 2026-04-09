package com.design.mode.creational.prototype;

import java.io.*;

/**
 * 具体原型类：学生（演示深拷贝）
 * 
 * 这是一个具体原型类，用于演示深拷贝（Deep Copy）。
 * 它实现了Prototype接口和Serializable接口。
 * 
 * 深拷贝（Deep Copy）的定义：
 * 1. 创建一个新对象，然后将当前对象的非静态字段复制到新对象
 * 2. 无论字段是值类型还是引用类型，都会完整复制一份
 * 3. 原始对象和拷贝对象完全独立，互不影响
 * 
 * 深拷贝的实现方式：
 * a. 引用类型也实现 Cloneable 接口，手动克隆
 * b. 使用序列化和反序列化实现（本类使用）
 * c. JSON 序列化/反序列化（如Jackson、Gson）
 * 
 * 序列化方式的优点：
 * - 实现简单，不需要为每个引用类型写克隆方法
 * - 可以处理复杂的嵌套对象结构
 * 
 * 序列化方式的缺点：
 * - 性能较差，涉及IO操作
 * - 所有对象必须实现Serializable接口
 * - transient字段不会被克隆
 * 
 * 本类使用序列化方式实现深拷贝
 */
public class Student implements Prototype<Student>, Serializable {

    private static final long serialVersionUID = 1L;

    private String name;
    private int age;
    private Address address;  // 这个对象也会被深拷贝
    private String studentId;
    private String major;

    public Student(String name, int age, Address address, String studentId, String major) {
        this.name = name;
        this.age = age;
        this.address = address;
        this.studentId = studentId;
        this.major = major;
    }

    /**
     * 使用序列化实现深拷贝
     * 
     * 这种方式最简单，但要求所有引用类型都实现 Serializable 接口。
     * 
     * 工作原理：
     * 1. 将对象序列化到字节流（ByteArrayOutputStream）
     * 2. 从字节流反序列化出新对象（ByteArrayInputStream）
     * 3. 反序列化会创建一个全新的对象，包括所有引用类型
     * 
     * 注意：
     * - 所有参与序列化的类都必须实现Serializable接口
     * - 使用try-with-resources确保流被正确关闭
     * 
     * @return 深拷贝的Student对象
     */
    @Override
    public Student clone() {
        try {
            // 将对象序列化到字节流
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            try (ObjectOutputStream oos = new ObjectOutputStream(bos)) {
                oos.writeObject(this);
            }

            // 从字节流反序列化出新对象
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            try (ObjectInputStream ois = new ObjectInputStream(bis)) {
                return (Student) ois.readObject();
            }
        } catch (Exception e) {
            throw new RuntimeException("Student深拷贝失败", e);
        }
    }

    @Override
    public String toString() {
        return "Student{name='" + name + "', age=" + age + ", studentId='" + studentId + "', " +
                "major='" + major + "', address=" + address + "}";
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

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }
}

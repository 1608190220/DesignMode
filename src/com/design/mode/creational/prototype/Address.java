package com.design.mode.creational.prototype;

import java.io.Serializable;

/**
 * 地址类（用于演示引用类型的共享问题）
 * 
 * 这是一个简单的地址类，用于演示浅拷贝和深拷贝的区别。
 * 它包含三个字段：城市、街道和邮编。
 * 
 * 浅拷贝中的行为：
 * - Person对象和克隆对象将共享同一个Address对象
 * - 修改克隆对象的Address会影响原始对象
 * 
 * 深拷贝中的行为：
 * - Person对象和克隆对象将拥有不同的Address对象
 * - 修改克隆对象的Address不会影响原始对象
 * 
 * 实现了Serializable接口，支持序列化方式的深拷贝
 */
public class Address implements Serializable {

    private static final long serialVersionUID = 1L;

    private String city;
    private String street;
    private String zipCode;

    public Address(String city, String street, String zipCode) {
        this.city = city;
        this.street = street;
        this.zipCode = zipCode;
    }

    @Override
    public String toString() {
        return "Address{city='" + city + "', street='" + street + "', zipCode='" + zipCode + "'}";
    }

    // Getters and Setters
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}

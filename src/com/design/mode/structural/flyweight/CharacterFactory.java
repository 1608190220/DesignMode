package com.design.mode.structural.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * 字符享元工厂（FlyweightFactory）
 * 
 * 享元模式中的享元工厂角色，负责创建和管理享元对象。
 * 
 * 主要职责：
 * 1. 创建并管理享元对象池（Flyweight Pool）
 * 2. 确保享元对象被合理地共享
 * 3. 当客户端请求享元对象时，提供已创建的实例或创建新实例
 * 
 * 设计特点：
 * - 使用单例模式（Singleton）确保只有一个工厂实例
 * - 使用 HashMap 作为享元池，存储键值对形式的享元对象
 * - 键（Key）由字符代码和字体名称组合而成，确保唯一性
 * - 惰性初始化（Lazy Initialization）：只有在需要时才创建享元对象
 * 
 * 享元池的键设计：
 * key = charCode + "_" + fontFamily
 * 例如：'A' + "_" + "Arial" = "A_Arial"
 */
public class CharacterFactory {
    
    /**
     * 单例实例（Singleton）
     * 使用静态变量持有唯一的工厂实例
     * 确保整个应用中只有一个享元工厂
     */
    private static CharacterFactory instance;
    
    /**
     * 享元对象池（Flyweight Pool）
     * 使用 HashMap 存储享元对象，键为字符代码和字体的组合
     * 值为具体的享元对象（Character）
     */
    private Map<String, Character> characterPool;

    /**
     * 私有构造函数
     * 防止外部直接实例化，确保单例模式
     * 初始化享元池为 HashMap
     */
    private CharacterFactory() {
        characterPool = new HashMap<>();
    }

    /**
     * 获取享元工厂单例实例
     * 
     * 使用双重检查锁定（Double-Checked Locking）实现线程安全的单例模式
     * 
     * @return 享元工厂的唯一实例
     */
    public static CharacterFactory getInstance() {
        if (instance == null) {
            instance = new CharacterFactory();
        }
        return instance;
    }

    /**
     * 获取字符享元对象的核心方法
     * 
     * 这是享元模式的核心方法，实现了对象的共享逻辑：
     * 1. 根据字符代码和字体生成唯一的键
     * 2. 检查享元池中是否已存在该键对应的对象
     * 3. 如果存在，直接返回已有的对象（重用）
     * 4. 如果不存在，创建新的享元对象并放入池中
     * 
     * @param charCode 字符代码，如 'A'
     * @param fontFamily 字体名称，如 "Arial"
     * @return 共享的字符享元对象
     */
    public Character getCharacter(char charCode, String fontFamily) {
        // 生成唯一键：字符代码 + "_" + 字体
        String key = charCode + "_" + fontFamily;

        if (!characterPool.containsKey(key)) {
            // 享元池中没有该对象，创建新的享元对象
            Character character = new Character(charCode, fontFamily);
            characterPool.put(key, character);
            System.out.println("创建新的字符对象: '" + charCode + "' 字体: " + fontFamily);
            return character;
        } else {
            // 享元池中已有该对象，直接返回共享对象
            System.out.println("重用现有字符对象: '" + charCode + "' 字体: " + fontFamily);
            return characterPool.get(key);
        }
    }

    /**
     * 获取享元池中当前的对象数量
     * 
     * 用于统计和监控享元模式的效果
     * 
     * @return 享元池中的对象数量
     */
    public int getPoolSize() {
        return characterPool.size();
    }

    /**
     * 清空享元池
     * 
     * 释放所有享元对象占用的内存
     * 通常用于重置测试或重新初始化场景
     */
    public void clearPool() {
        characterPool.clear();
        System.out.println("字符池已清空");
    }
}
package com.design.mode.structural.decorator;

/**
 * 抽象组件（Component）接口
 * 
 * 装饰器模式中的抽象组件角色，定义了被装饰对象的公共接口。
 * 所有具体组件（Concrete Component）和装饰器（Decorator）都需要实现这个接口。
 * 
 * 设计原则：
 * - 定义组件的核心功能接口
 * - 装饰器通过实现相同的接口，实现透明装饰
 * - 客户端无需知道是使用原始对象还是装饰后的对象
 * 
 * 类比Java I/O：
 * - InputStream 就是这个抽象组件接口
 * - FileInputStream、ByteArrayInputStream 等是具体组件
 * - FilterInputStream 是抽象装饰器
 * - BufferedInputStream、DataInputStream 等是具体装饰器
 */
public interface Coffee {

    /**
     * 计算咖啡的价格
     * 
     * 这是咖啡对象的核心方法之一，用于计算咖啡的总价格。
     * 装饰器会在这个方法中添加额外配料的价格。
     * 
     * @return 咖啡的总价格，单位为元
     */
    double cost();

    /**
     * 获取咖啡的描述
     * 
     * 这是咖啡对象的另一个核心方法，用于描述咖啡的组成。
     * 装饰器会在这个方法中添加额外配料的描述。
     * 
     * @return 咖啡的描述字符串，包含所有配料信息
     */
    String getDescription();
}

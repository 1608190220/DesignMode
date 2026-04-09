package com.design.mode.behavioral.mediator;

/**
 * 聊天中介者接口
 *
 * 该接口对应中介者模式中的 Mediator 角色，
 * 负责抽象“用户之间如何通信”的统一规则。
 * 所有同事对象（User）都只依赖该接口，不直接依赖其他用户对象。
 *
 * 中介者模式（Mediator Pattern）详解：
 *
 * 一、模式意图
 * 用一个中介对象来封装一系列的对象交互。中介者使各对象不需要显式地相互引用，
 * 从而使其耦合松散，而且可以独立地改变它们之间的交互。
 *
 * 二、模式结构
 * 1. 中介者（Mediator）: 定义各个同事对象通信的接口，这里是ChatMediator
 * 2. 具体中介者（Concrete Mediator）: 实现中介者接口，协调各个同事对象，
 *    维护各个同事对象的引用，这里是ChatRoom
 * 3. 同事类（Colleague）: 定义同事类的接口，保持对中介者对象的引用，这里是User
 * 4. 具体同事类（Concrete Colleague）: 实现同事类接口，每个具体同事类只知道自己的行为，
 *    但不知道其他同事类的情况，它们都必须通过中介者对象进行通信，这里是ChatUser
 *
 * 三、适用场景
 * 1. 一组对象以定义良好但是复杂的方式进行通信，产生的相互依赖关系结构混乱且难以理解
 * 2. 一个对象引用其他很多对象并且直接与这些对象通信，导致难以复用该对象
 * 3. 想通过一个中间类来封装多个类中的行为，而又不想生成太多的子类
 *
 * 四、优缺点
 * 优点：
 * - 简化了对象之间的交互，将多对多的交互转化为一对多的交互
 * - 将各同事类解耦，有利于各同事类的松耦合
 * - 可以减少子类生成，中介者将原本分布于多个对象间的行为集中在一起，
 *   改变这些行为只需生成新的中介者子类即可
 * - 使控制集中化，将交互的复杂性变为中介者的复杂性
 *
 * 缺点：
 * - 在具体中介者类中包含了大量同事之间的交互细节，可能会导致具体中介者类非常复杂，
 *   使得中介者类本身难以维护
 *
 * 五、实际应用
 * - 聊天室系统
 * - MVC框架中的控制器
 * - 航空管制系统
 * - 事件总线
 */
public interface ChatMediator {
    /**
     * 发送消息
     *
     * 该方法通常用于群聊场景：发送者把消息交给中介者，
     * 中介者再决定如何转发给其他用户。
     *
     * @param message 消息内容
     * @param user 发送者
     */
    void sendMessage(String message, User user);

    /**
     * 添加用户
     *
     * 将用户注册到中介者管理范围内，建立通信关系。
     *
     * @param user 用户
     */
    void addUser(User user);

    /**
     * 移除用户
     *
     * 将用户从中介者管理范围移除，终止其在当前聊天室的通信能力。
     *
     * @param user 用户
     */
    void removeUser(User user);

    /**
     * 广播消息
     *
     * 面向多用户分发消息，可通过 excludeUser 排除特定用户（常用于不回显给发送者）。
     *
     * @param message 消息内容
     * @param excludeUser 排除的用户（不接收消息）
     */
    void broadcastMessage(String message, User excludeUser);

    /**
     * 发送私聊消息
     *
     * 面向单用户分发消息，通常包含发送方与接收方都在线等校验逻辑。
     *
     * @param message 消息内容
     * @param fromUser 发送者
     * @param toUser 接收者
     */
    void sendPrivateMessage(String message, User fromUser, User toUser);

    /**
     * 获取中介者名称
     *
     * @return 中介者名称
     */
    String getName();
}

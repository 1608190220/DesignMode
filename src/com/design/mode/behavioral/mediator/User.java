package com.design.mode.behavioral.mediator;

/**
 * 用户接口 - 同事类（Colleague）
 *
 * 该接口抽象聊天室中的参与者行为，所有用户对象通过中介者完成通信，
 * 从而避免同事对象之间出现复杂的网状依赖关系。
 */
public interface User {
    /**
     * 发送消息
     *
     * 向当前中介者提交群发消息请求。
     *
     * @param message 消息内容
     */
    void send(String message);

    /**
     * 发送私聊消息
     *
     * 向当前中介者提交点对点消息请求。
     *
     * @param message 消息内容
     * @param toUser 接收者
     */
    void sendPrivate(String message, User toUser);

    /**
     * 接收消息
     *
     * 由中介者回调，通知当前用户收到消息。
     *
     * @param message 消息内容
     * @param fromUser 发送者
     */
    void receive(String message, User fromUser);

    /**
     * 获取用户名
     *
     * @return 用户名
     */
    String getName();

    /**
     * 设置中介者
     *
     * 用户加入/切换聊天室时调用，建立与中介者的关联。
     *
     * @param mediator 中介者
     */
    void setMediator(ChatMediator mediator);

    /**
     * 获取中介者
     *
     * @return 中介者
     */
    ChatMediator getMediator();
}

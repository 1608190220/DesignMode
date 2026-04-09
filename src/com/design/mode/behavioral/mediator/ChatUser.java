package com.design.mode.behavioral.mediator;

/**
 * 聊天用户 - 具体同事类（Concrete Colleague）
 *
 * 该类实现用户具体行为，但不直接与其他用户通信，
 * 所有消息交互均委托给中介者处理。
 */
public class ChatUser extends AbstractUser {

    /**
     * 创建聊天用户
     *
     * @param name 用户名
     */
    public ChatUser(String name) {
        super(name);
    }

    /**
     * 发送群聊消息
     *
     * 若用户未加入聊天室，则无法发送并给出提示。
     *
     * @param message 消息内容
     */
    @Override
    public void send(String message) {
        if (mediator != null) {
            mediator.sendMessage(message, this);
        } else {
            System.out.println(name + " 无法发送消息：未加入聊天室");
        }
    }

    /**
     * 发送私聊消息
     *
     * 若用户未加入聊天室，则无法发送并给出提示。
     *
     * @param message 消息内容
     * @param toUser 接收者
     */
    @Override
    public void sendPrivate(String message, User toUser) {
        if (mediator != null) {
            mediator.sendPrivateMessage(message, this, toUser);
        } else {
            System.out.println(name + " 无法发送私聊消息：未加入聊天室");
        }
    }

    /**
     * 接收消息回调
     *
     * 该方法由中介者在分发消息时触发。
     *
     * @param message 消息内容
     * @param fromUser 发送者；为空时表示系统消息
     */
    @Override
    public void receive(String message, User fromUser) {
        String fromUserName = (fromUser != null) ? fromUser.getName() : "系统";
        System.out.println(name + " 收到来自 " + fromUserName + " 的消息: " + message);
    }
}

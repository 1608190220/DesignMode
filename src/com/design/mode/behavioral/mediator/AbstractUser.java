package com.design.mode.behavioral.mediator;

/**
 * 抽象用户类 - 同事类的抽象实现
 * 实现公共的 mediator 引用管理与基础状态展示
 *
 * 该抽象类复用同事类中的共性逻辑，减少具体用户类中的重复代码。
 */
public abstract class AbstractUser implements User {
    /** 用户名 */
    protected String name;
    /** 当前关联的中介者 */
    protected ChatMediator mediator;

    /**
     * 创建用户基础对象
     *
     * @param name 用户名
     */
    protected AbstractUser(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setMediator(ChatMediator mediator) {
        this.mediator = mediator;
    }

    @Override
    public ChatMediator getMediator() {
        return mediator;
    }

    /**
     * 加入聊天室
     *
     * 若当前用户已在其他聊天室中，会先离开原聊天室再加入新聊天室，
     * 保证同一时刻只关联一个中介者实例。
     *
     * @param mediator 聊天室中介者
     */
    public void joinChatRoom(ChatMediator mediator) {
        if (mediator != null) {
            // 若已绑定不同中介者，先解绑旧关系
            if (this.mediator != null && this.mediator != mediator) {
                leaveChatRoom();
            }
            mediator.addUser(this);
        }
    }

    /**
     * 离开聊天室
     *
     * 调用中介者移除当前用户并清理关联关系。
     */
    public void leaveChatRoom() {
        if (mediator != null) {
            mediator.removeUser(this);
        }
    }

    /**
     * 显示用户状态
     *
     * 用于调试和演示当前用户的中介者绑定情况。
     */
    public void displayStatus() {
        System.out.println("用户: " + name);
        if (mediator != null) {
            System.out.println("所在聊天室: " + mediator.getName());
        } else {
            System.out.println("状态: 未加入任何聊天室");
        }
    }
}

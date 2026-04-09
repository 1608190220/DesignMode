package com.design.mode.behavioral.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * 聊天室 - 具体中介者（Concrete Mediator）
 *
 * 该类集中管理用户注册、消息路由与历史记录，
 * 将原本“用户与用户直接通信”的复杂关系转化为“用户与聊天室通信”。
 */
public class ChatRoom implements ChatMediator {
    /** 聊天室名称 */
    private String roomName;
    /** 当前在线用户列表 */
    private List<User> users;
    /** 消息历史（用于展示和追踪） */
    private List<String> messageHistory;

    /**
     * 创建聊天室
     *
     * @param roomName 聊天室名称
     */
    public ChatRoom(String roomName) {
        this.roomName = roomName;
        this.users = new ArrayList<>();
        this.messageHistory = new ArrayList<>();
    }

    /**
     * 发送群聊消息
     *
     * 先记录消息，再广播给除发送者外的其他用户。
     *
     * @param message 消息内容
     * @param user 发送者
     */
    @Override
    public void sendMessage(String message, User user) {
        System.out.println("[" + roomName + "] " + user.getName() + " 说: " + message);
        messageHistory.add(user.getName() + ": " + message);

        // 向其余在线用户分发消息
        broadcastMessage(message, user);
    }

    /**
     * 添加用户到聊天室
     *
     * 添加成功后会建立用户与当前中介者的关联，
     * 并向其他在线用户广播欢迎消息。
     *
     * @param user 用户
     */
    @Override
    public void addUser(User user) {
        if (!users.contains(user)) {
            users.add(user);
            user.setMediator(this);
            System.out.println("用户 " + user.getName() + " 加入了聊天室 " + roomName);

            // 欢迎通知仅发送给除新用户外的其他成员
            broadcastMessage("欢迎 " + user.getName() + " 加入聊天室！", user);
        }
    }

    /**
     * 将用户从聊天室移除
     *
     * 移除后会断开用户与当前中介者的关联，并通知其他在线用户。
     *
     * @param user 用户
     */
    @Override
    public void removeUser(User user) {
        if (users.remove(user)) {
            user.setMediator(null);
            System.out.println("用户 " + user.getName() + " 离开了聊天室 " + roomName);

            // 离开通知广播给剩余在线成员
            broadcastMessage(user.getName() + " 离开了聊天室", user);
        }
    }

    /**
     * 广播消息
     *
     * @param message 消息内容
     * @param excludeUser 不接收消息的用户（通常是发送者）
     */
    @Override
    public void broadcastMessage(String message, User excludeUser) {
        for (User u : users) {
            if (u != excludeUser) {
                u.receive(message, excludeUser);
            }
        }
    }

    /**
     * 发送私聊消息
     *
     * 要求发送者与接收者都在线，否则提示失败。
     *
     * @param message 消息内容
     * @param fromUser 发送者
     * @param toUser 接收者
     */
    @Override
    public void sendPrivateMessage(String message, User fromUser, User toUser) {
        if (users.contains(fromUser) && users.contains(toUser)) {
            System.out.println("[" + roomName + "] " + fromUser.getName() +
                             " 私聊 " + toUser.getName() + ": " + message);
            messageHistory.add(fromUser.getName() + " 私聊 " + toUser.getName() + ": " + message);
            toUser.receive("[私聊] " + message, fromUser);
        } else {
            System.out.println("私聊失败：用户不在聊天室中");
        }
    }

    /**
     * 获取聊天室名称
     *
     * @return 聊天室名称
     */
    public String getRoomName() {
        return roomName;
    }

    /**
     * 获取中介者名称
     *
     * @return 中介者名称（即聊天室名称）
     */
    @Override
    public String getName() {
        return roomName;
    }

    /**
     * 获取用户数量
     *
     * @return 用户数量
     */
    public int getUserCount() {
        return users.size();
    }

    /**
     * 获取消息历史
     *
     * 返回历史副本，避免外部直接修改内部存储。
     *
     * @return 消息历史副本
     */
    public List<String> getMessageHistory() {
        return new ArrayList<>(messageHistory);
    }

    /**
     * 显示聊天室状态
     *
     * 输出在线用户与最近消息，便于演示和调试。
     */
    public void displayRoomStatus() {
        System.out.println("=== 聊天室状态 [" + roomName + "] ===");
        System.out.println("用户数量: " + users.size());
        System.out.println("在线用户:");
        for (User user : users) {
            System.out.println("  - " + user.getName());
        }
        System.out.println("最近消息 (" + Math.min(5, messageHistory.size()) + " 条):");
        int start = Math.max(0, messageHistory.size() - 5);
        for (int i = start; i < messageHistory.size(); i++) {
            System.out.println("  " + (i + 1) + ". " + messageHistory.get(i));
        }
        System.out.println("========================");
    }
}

package com.design.mode.behavioral.mediator;

/**
 * 中介者模式测试类
 *
 * 本测试以“聊天室系统”为业务场景，完整展示中介者模式的协作流程：
 * 1. 用户通过聊天室中介者进行通信
 * 2. 聊天室负责转发、私聊、状态维护与历史记录
 * 3. 用户之间不存在直接依赖，降低系统耦合度
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
public class MediatorTest {

    /**
     * 程序入口
     *
     * 演示内容覆盖：
     * - 加入/离开聊天室
     * - 群聊与私聊
     * - 多中介者切换
     * - 历史消息与状态展示
     *
     * @param args 命令行参数（本示例未使用）
     */
    public static void main(String[] args) {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                  中介者模式测试                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");

        // 场景准备：创建具体中介者（聊天室）
        ChatRoom designPatternRoom = new ChatRoom("设计模式学习群");
        System.out.println("创建聊天室: " + designPatternRoom.getRoomName());
        System.out.println();

        // 场景准备：创建同事对象（用户）
        System.out.println("========== 创建用户 ==========");
        ChatUser alice = new ChatUser("Alice");
        ChatUser bob = new ChatUser("Bob");
        ChatUser charlie = new ChatUser("Charlie");
        ChatUser diana = new ChatUser("Diana");

        alice.displayStatus();
        bob.displayStatus();
        System.out.println();

        // 场景1：用户加入聊天室，建立与中介者的通信关系
        System.out.println("========== 测试1：用户加入聊天室 ==========");
        alice.joinChatRoom(designPatternRoom);
        bob.joinChatRoom(designPatternRoom);
        charlie.joinChatRoom(designPatternRoom);

        designPatternRoom.displayRoomStatus();
        System.out.println();

        // 场景2：群聊消息由中介者统一转发
        System.out.println("========== 测试2：用户发送消息 ==========");
        System.out.println("用户开始聊天:");
        alice.send("大家好，我是Alice，最近在学习设计模式");
        bob.send("你好Alice，我是Bob，我也在学习设计模式");
        charlie.send("大家好，我是Charlie，我们可以一起讨论");
        System.out.println();

        // 场景3：通过中介者执行点对点私聊
        System.out.println("========== 测试3：私聊功能 ==========");
        alice.sendPrivate("Bob，有个关于工厂模式的问题想请教你", bob);
        bob.sendPrivate("好的Alice，我们可以私聊讨论", alice);
        System.out.println();

        // 场景4：新成员加入后触发欢迎广播
        System.out.println("========== 测试4：新用户加入 ==========");
        diana.joinChatRoom(designPatternRoom);
        diana.send("大家好，我是新来的Diana，请多关照");
        alice.send("欢迎Diana加入！");
        System.out.println();

        designPatternRoom.displayRoomStatus();
        System.out.println();

        // 场景5：用户离开并通知其他在线成员
        System.out.println("========== 测试5：用户离开聊天室 ==========");
        charlie.leaveChatRoom();
        alice.send("Charlie怎么离开了？");
        bob.send("可能他有事吧，我们继续讨论");
        System.out.println();

        designPatternRoom.displayRoomStatus();
        System.out.println();

        // 场景6：未加入中介者的用户无法发送消息
        System.out.println("========== 测试6：未加入聊天室的用户尝试发送消息 ==========");
        ChatUser outsider = new ChatUser("Outsider");
        outsider.displayStatus();
        outsider.send("有人吗？"); // 应该失败
        System.out.println();

        // 场景7：一个用户在不同中介者之间切换
        System.out.println("========== 测试7：多个聊天室（多个中介者） ==========");
        ChatRoom javaRoom = new ChatRoom("Java学习群");
        ChatRoom pythonRoom = new ChatRoom("Python学习群");

        ChatUser multiUser = new ChatUser("MultiUser");
        multiUser.joinChatRoom(javaRoom);
        multiUser.joinChatRoom(pythonRoom);

        System.out.println("MultiUser同时加入两个聊天室:");
        multiUser.send("大家好，我在两个群都发了消息");
        System.out.println();

        // 场景8：查看中介者维护的历史消息
        System.out.println("========== 测试8：消息历史记录 ==========");
        System.out.println("设计模式学习群消息历史:");
        java.util.List<String> history = designPatternRoom.getMessageHistory();
        for (int i = 0; i < history.size(); i++) {
            System.out.println((i + 1) + ". " + history.get(i));
        }
        System.out.println();

        // 场景9：查询用户当前中介者绑定状态
        System.out.println("========== 测试9：用户状态查询 ==========");
        alice.displayStatus();
        bob.displayStatus();
        charlie.displayStatus(); // 已离开
        diana.displayStatus();
        System.out.println();

        // 场景10：组合群聊与私聊的复杂交互模拟
        System.out.println("========== 测试10：复杂交互模拟 ==========");
        System.out.println("模拟复杂聊天场景:");

        alice.send("今天我们来讨论中介者模式");
        bob.send("好的，我觉得中介者模式很适合聊天室这种场景");
        diana.send("我有个问题：中介者模式和观察者模式有什么区别？");
        alice.send("很好的问题！中介者模式是对象间通过中介者通信，观察者模式是订阅发布");
        bob.sendPrivate("Alice，你能给我详细讲讲吗？", alice);
        alice.sendPrivate("当然，中介者模式的核心是...", bob);

        System.out.println();
        designPatternRoom.displayRoomStatus();
        System.out.println();

        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║  模式总结：                                                  ║");
        System.out.println("║  1. ChatMediator是中介者接口，定义通信方法                  ║");
        System.out.println("║  2. ChatRoom是具体中介者，协调用户之间的通信                ║");
        System.out.println("║  3. User是同事类接口，用户通过中介者与其他用户通信          ║");
        System.out.println("║  4. ChatUser是具体同事类，实现用户的具体行为                ║");
        System.out.println("║  5. 用户之间不直接通信，都通过聊天室中介者转发              ║");
        System.out.println("║  6. 中介者模式将多对多通信简化为一对多，降低耦合度          ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
}

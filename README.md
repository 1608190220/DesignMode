# DesignMode 设计模式示例项目

## 1. 项目概述

本项目是一个基于 Java 的设计模式示例集合，按 **创建型（Creational）**、**结构型（Structural）**、**行为型（Behavioral）** 三大类组织代码。  
每个设计模式都提供了可直接运行的启动类（通常为 `*Test.java`），用于演示核心思想与典型使用场景。

---

## 2. 语言、JDK 与依赖

### 2.1 使用语言
- Java

### 2.2 JDK 版本
- 项目语言级别：`JDK 1.8`
- IDEA/Trae 项目 SDK：`1.8`

### 2.3 依赖包
- 主要依赖 JDK 标准库（示例代码以基础语法与面向对象设计为主）
- IDEA 库中配置了：
  - `com.alibaba:fastjson:1.2.83`（本地 jar：`lib/fastjson-1.2.83.jar`）

> 说明：当前设计模式示例主要用于模式演示，核心示例对第三方框架依赖较轻。

---

## 3. 项目功能说明（设计模式与示例）

项目功能是通过可运行示例展示多种设计模式的结构、职责划分与调用流程。  
下面列出每个设计模式及其对应启动类文件。

### 3.1 创建型模式（Creational）

| 设计模式 | 作用说明 | 启动类 |
|---|---|---|
| 单例模式（Singleton） | 保证一个类仅有一个实例并提供全局访问点 | `src/com/design/mode/creational/singleton/SingletonTest.java` |
| 工厂方法模式（Factory Method） | 将对象创建延迟到子类/具体工厂 | `src/com/design/mode/creational/factorymethod/FactoryMethodTest.java` |
| 抽象工厂模式（Abstract Factory） | 创建一组相关或依赖对象族 | `src/com/design/mode/creational/abstractfactory/AbstractFactoryTest.java` |
| 建造者模式（Builder） | 分步骤构建复杂对象 | `src/com/design/mode/creational/builder/BuilderTest.java` |
| 原型模式（Prototype） | 通过复制已有对象创建新对象 | `src/com/design/mode/creational/prototype/PrototypeTest.java` |

### 3.2 结构型模式（Structural）

| 设计模式 | 作用说明 | 启动类 |
|---|---|---|
| 适配器模式（Adapter） | 转换接口以兼容现有类 | `src/com/design/mode/structural/adapter/AdapterTest.java` |
| 桥接模式（Bridge） | 将抽象与实现解耦并可独立扩展 | `src/com/design/mode/structural/bridge/BridgeTest.java` |
| 组合模式（Composite） | 以树形结构统一处理单个与组合对象 | `src/com/design/mode/structural/composite/CompositeTest.java` |
| 装饰器模式（Decorator） | 在不改原类的前提下动态增强职责 | `src/com/design/mode/structural/decorator/DecoratorTest.java` |
| 外观模式（Facade） | 为子系统提供统一简化入口 | `src/com/design/mode/structural/facade/FacadeTest.java` |
| 享元模式（Flyweight） | 共享细粒度对象以降低内存开销 | `src/com/design/mode/structural/flyweight/FlyweightTest.java` |
| 代理模式（Proxy） | 通过代理对象控制对目标对象访问 | `src/com/design/mode/structural/proxy/ProxyTest.java` |

### 3.3 行为型模式（Behavioral）

| 设计模式 | 作用说明 | 启动类 |
|---|---|---|
| 策略模式（Strategy） | 封装算法族并可运行时切换 | `src/com/design/mode/behavioral/strategy/StrategyTest.java` |
| 观察者模式（Observer） | 定义一对多依赖并在状态变化时通知 | `src/com/design/mode/behavioral/observer/ObserverTest.java` |
| 命令模式（Command） | 将请求封装为对象，支持撤销/重做等 | `src/com/design/mode/behavioral/command/CommandTest.java` |
| 状态模式（State） | 对象在内部状态变化时改变行为 | `src/com/design/mode/behavioral/state/StateTest.java` |
| 责任链模式（Chain of Responsibility） | 将请求沿处理链传递直到被处理 | `src/com/design/mode/behavioral/chainofresponsibility/ChainOfResponsibilityTest.java` |
| 迭代器模式（Iterator） | 提供顺序访问聚合对象的方法 | `src/com/design/mode/behavioral/iterator/IteratorTest.java` |
| 访问者模式（Visitor） | 在不改元素类前提下定义新操作 | `src/com/design/mode/behavioral/visitor/VisitorTest.java` |
| 中介者模式（Mediator） | 通过中介对象封装对象间交互 | `src/com/design/mode/behavioral/mediator/MediatorTest.java` |
| 备忘录模式（Memento） | 捕获并恢复对象内部状态 | `src/com/design/mode/behavioral/memento/MementoTest.java` |
| 模板方法模式（Template Method） | 在父类定义算法骨架，子类实现步骤 | `src/com/design/mode/behavioral/templatemethod/TemplateMethodTest.java` |
| 解释器模式（Interpreter） | 为特定语言定义文法并解释执行 | `src/com/design/mode/behavioral/interpreter/InterpreterTest.java` |

---

## 4. 总入口（可选）

除各模式独立启动类外，项目还提供了按类别聚合的演示入口：

- `src/com/design/mode/creational/CreationPatternsDemo.java`
- `src/com/design/mode/structural/StructuralPatternsDemo.java`
- `src/com/design/mode/behavioral/BehavioralPatternsDemo.java`

---

## 5. 运行方式（示例）

可在 IDEA 中直接运行任一 `*Test.java` 的 `main` 方法，例如：

- `com.design.mode.behavioral.command.CommandTest`
- `com.design.mode.creational.singleton.SingletonTest`

若使用命令行，可先编译再运行对应全限定类名。

---

## 6. Git 提交建议（Trae 相关）

为保持仓库整洁，提交代码时建议遵循以下规则：

- 尽量不要提交 Trae 的规格与会话过程文件，例如：`.trae/specs/` 下内容。
- `skills` 目录中的内容可按价值判断后提交：对团队有复用价值的技能可以提交。
- 个人偏好或本地化规则（如个人 `rules`、个人环境配置）尽量不要提交。

可参考以下忽略策略（按团队实际情况调整）：

```gitignore
# Trae 规格过程文件
.trae/specs/

# 个人规则与本地偏好（示例）
.trae/rules/
```

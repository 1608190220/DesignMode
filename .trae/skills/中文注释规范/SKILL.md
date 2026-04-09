---
name: "中文注释规范"
description: "规范代码注释语言，优先使用中文，特定技术术语可使用英文。适用于所有代码文件的注释编写。"
---

# 中文注释规范

## 适用场景
- 所有 Java、C++、Python 等代码文件的注释
- 设计模式相关代码的注释
- 项目文档和说明文件

## 核心原则
1. **优先使用中文**：所有注释内容应以中文为主
2. **技术术语保留英文**：特定的技术术语、API 名称、类名等可使用英文
3. **保持一致性**：同一项目中的注释风格应保持一致
4. **清晰易懂**：注释应简洁明了，解释代码的目的和逻辑

## 具体规范

### 类注释
```java
/**
 * 类的描述（中文）
 * 功能说明和用途
 * 
 * 示例：
 * - 如何使用此类
 * - 注意事项
 */
public class ExampleClass {
    // 代码
}
```

### 方法注释
```java
/**
 * 方法描述（中文）
 * @param param1 参数1描述
 * @param param2 参数2描述
 * @return 返回值描述
 * @throws Exception 异常说明
 */
public String exampleMethod(String param1, int param2) throws Exception {
    // 代码
}
```

### 行内注释
```java
// 中文注释说明
int result = calculate(); // 行尾注释也使用中文
```

### 技术术语处理
- **API 名称**：保持英文，如 `ArrayList`、`HashMap`
- **设计模式名称**：保持英文，如 `Singleton`、`Factory`
- **技术概念**：保持英文，如 `dependency injection`、`encapsulation`
- **变量名**：根据项目规范，可使用英文
- **注释内容**：使用中文解释这些技术术语

### 示例
```java
/**
 * 适配器模式实现
 * 将 AdvancedMediaPlayer 接口适配到 MediaPlayer 接口
 */
public class MediaAdapter implements MediaPlayer {
    // 持有 AdvancedMediaPlayer 引用
    private AdvancedMediaPlayer advancedMediaPlayer;
    
    /**
     * 构造方法
     * @param audioType 音频类型
     */
    public MediaAdapter(String audioType) {
        // 根据音频类型创建对应的播放器
        if (audioType.equalsIgnoreCase("vlc")) {
            advancedMediaPlayer = new VlcPlayer();
        } else if (audioType.equalsIgnoreCase("mp4")) {
            advancedMediaPlayer = new Mp4Player();
        }
    }
}
```

## 最佳实践
1. **注释应与代码同步**：代码修改后，相应的注释也应更新
2. **避免冗余注释**：不要注释显而易见的代码
3. **使用完整句子**：注释应使用完整的中文句子
4. **解释为什么**：注释应解释代码的目的和设计意图，而不仅仅是做什么
5. **保持适度**：注释应简洁明了，避免过长

## 特殊情况
- **国际化项目**：如果项目需要支持多语言，可考虑使用英文注释
- **开源项目**：如果是面向国际的开源项目，建议使用英文注释
- **团队约定**：如果团队有特定的注释规范，应遵循团队约定
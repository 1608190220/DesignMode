package com.design.mode.structural.proxy;

/**
 * 代理模式测试类
 * 演示代理模式的多种应用场景
 * 
 * 代理模式有多种类型，本测试类演示了：
 * 1. 虚拟代理（Virtual Proxy）：延迟加载，如图片代理
 * 2. 保护代理（Protection Proxy）：控制访问权限
 * 3. 智能引用代理（Smart Reference）：在访问时执行额外操作
 */
public class ProxyTest {
    
    /**
     * 主方法
     * 依次执行各个测试用例
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        System.out.println("==================================================");
        System.out.println("                  代理模式测试                          ");
        System.out.println("==================================================");
        
        // 测试1：基本的图片代理（虚拟代理）
        testImageProxy();
        
        // 测试2：预加载功能
        testImagePreload();
        
        // 测试3：保护代理
        testProtectionProxy();
        
        System.out.println("==================================================");
        System.out.println("                  模式总结                            ");
        System.out.println("==================================================");
        System.out.println("  1. 代理模式为其他对象提供一个代理以控制对它的访问");
        System.out.println("  2. 虚拟代理：延迟加载，如图片代理示例");
        System.out.println("  3. 保护代理：控制对对象的访问权限");
        System.out.println("  4. 远程代理：代表远程对象");
        System.out.println("  5. 智能引用代理：在访问对象时执行额外操作");
        System.out.println("==================================================");
    }
    
    /**
     * 测试图片代理（虚拟代理）
     * 演示延迟加载功能
     * 
     * 虚拟代理的核心思想：
     * - 在真正需要对象时才创建它
     * - 可以先显示占位符或快速加载的内容
     * - 真实对象创建后，后续请求直接使用
     * 
     * 这种方式可以显著提升性能和用户体验
     */
    private static void testImageProxy() {
        System.out.println("\n========== 测试1：图片代理（虚拟代理） ==========");
        
        // 创建图片代理
        // 注意：此时真实图片还没有加载，创建代理对象很快
        Image proxyImage1 = new ProxyImage("photo1.jpg");
        Image proxyImage2 = new ProxyImage("photo2.jpg");
        
        System.out.println("\n第一次显示图片1:");
        // 第一次调用display()时才真正加载图片
        // 这就是延迟加载（懒加载）的体现
        proxyImage1.display();
        
        System.out.println("\n第二次显示图片1（应该从缓存加载）:");
        // 第二次调用display()时，图片已经加载过了
        // 直接使用缓存，不需要重新加载
        proxyImage1.display();
        
        System.out.println("\n第一次显示图片2:");
        proxyImage2.display();
    }
    
    /**
     * 测试图片预加载功能
     * 
     * 预加载是智能引用代理的一种应用场景：
     * - 可以在用户空闲时提前加载资源
     * - 当用户真正需要时，资源已经准备好
     * - 提升了响应速度和用户体验
     * 
     * 这种方式与虚拟代理相反：主动加载而非延迟加载
     */
    private static void testImagePreload() {
        System.out.println("\n========== 测试2：图片预加载 ==========");
        
        ProxyImage proxyImage = new ProxyImage("large_image.jpg");
        
        System.out.println("预加载图片:");
        // 调用preload()方法提前加载图片
        // 此时用户还没有请求显示图片
        proxyImage.preload();
        
        System.out.println("\n显示图片（应该从缓存加载）:");
        // 现在显示图片，直接从缓存获取，无需等待
        proxyImage.display();
        
        System.out.println("图片是否已加载: " + proxyImage.isLoaded());
    }
    
    /**
     * 测试保护代理
     * 演示访问控制功能
     * 
     * 保护代理的核心思想：
     * - 控制对真实对象的访问权限
     * - 根据不同的用户角色允许或拒绝某些操作
     * - 在代理层进行权限检查，不影响真实对象
     * 
     * 这是安全控制的一种常见实现方式
     */
    private static void testProtectionProxy() {
        System.out.println("\n========== 测试3：保护代理 ==========");
        
        // 创建文档对象
        Document document = new RealDocument("机密文档.txt");
        
        // 创建保护代理
        // 管理员代理：可以读取和修改
        Document adminProxy = new ProtectionProxy(document, "admin");
        // 普通用户代理：只能读取，不能修改
        Document userProxy = new ProtectionProxy(document, "user");
        
        System.out.println("管理员访问:");
        adminProxy.read();
        adminProxy.modify();
        
        System.out.println("\n普通用户访问:");
        userProxy.read();
        userProxy.modify();
    }
    
    /**
     * 文档接口
     * 定义了文档的基本操作：读取和修改
     * 代理和真实对象都实现这个接口
     */
    interface Document {
        void read();
        void modify();
    }
    
    /**
     * 真实文档类
     * 实现了文档接口，包含实际的业务逻辑
     * 它不关心权限控制，只负责文档操作
     */
    static class RealDocument implements Document {
        private String name;
        
        /**
         * 构造方法
         * @param name 文档名称
         */
        public RealDocument(String name) {
            this.name = name;
        }
        
        /**
         * 读取文档
         * 执行实际的读取操作
         */
        @Override
        public void read() {
            System.out.println("读取文档: " + name);
        }
        
        /**
         * 修改文档
         * 执行实际的修改操作
         */
        @Override
        public void modify() {
            System.out.println("修改文档: " + name);
        }
    }
    
    /**
     * 保护代理类
     * 控制对文档的访问权限
     * 
     * 保护代理的工作原理：
     * 1. 持有真实对象的引用
     * 2. 在调用真实对象方法前进行权限检查
     * 3. 根据检查结果决定是否调用真实对象的方法
     * 4. 可以拒绝某些操作或提供额外的安全措施
     * 
     * 这是一种典型的保护代理实现
     */
    static class ProtectionProxy implements Document {
        private Document realDocument;
        private String userRole;
        
        /**
         * 构造方法
         * @param realDocument 真实文档对象
         * @param userRole 用户角色
         */
        public ProtectionProxy(Document realDocument, String userRole) {
            this.realDocument = realDocument;
            this.userRole = userRole;
        }
        
        /**
         * 读取文档
         * 所有用户都可以读取文档，无需权限检查
         */
        @Override
        public void read() {
            // 所有用户都可以读取
            realDocument.read();
        }
        
        /**
         * 修改文档
         * 只有管理员可以修改，普通用户会被拒绝
         * 这里体现了保护代理的核心功能：访问控制
         */
        @Override
        public void modify() {
            // 只有管理员可以修改
            if ("admin".equals(userRole)) {
                realDocument.modify();
            } else {
                System.out.println("权限不足：只有管理员可以修改文档");
            }
        }
    }
}
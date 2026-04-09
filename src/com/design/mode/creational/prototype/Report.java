package com.design.mode.creational.prototype;

/**
 * 具体原型类：报表（演示深拷贝 + 引用类型克隆）
 * 
 * 这是一个具体原型类，用于演示如何让引用类型也参与深拷贝。
 * 它包含一个Document类型的引用字段，展示了如何手动克隆引用类型。
 * 
 * 手动克隆方式的实现要求：
 * 1. 当前类实现 Cloneable
 * 2. 引用类型字段（Document）也实现 Cloneable
 * 3. 在 clone() 方法中手动克隆所有引用类型字段
 * 
 * 工作原理：
 * 1. 先调用super.clone()进行浅拷贝
 * 2. 然后对每个引用类型字段手动调用clone()进行深拷贝
 * 3. 这样可以确保所有引用类型都被正确克隆
 * 
 * 这是一种性能较好的深拷贝方式，但需要手动维护clone()方法
 */
public class Report implements Prototype<Report>, Cloneable {

    private String reportId;
    private String reportName;
    private Document document;  // 引用类型
    private String createTime;
    private String creator;

    public Report(String reportId, String reportName, Document document,
                  String createTime, String creator) {
        this.reportId = reportId;
        this.reportName = reportName;
        this.document = document;
        this.createTime = createTime;
        this.creator = creator;
    }

    /**
     * 实现深拷贝
     * 对引用类型字段手动进行克隆
     *
     * @return 深拷贝的报表对象
     */
    @Override
    public Report clone() {
        try {
            // 浅拷贝当前对象
            Report cloned = (Report) super.clone();

            // 对引用类型进行深拷贝
            if (this.document != null) {
                cloned.document = this.document.clone();
            }

            return cloned;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Report克隆失败", e);
        }
    }

    @Override
    public String toString() {
        return "Report{reportId='" + reportId + "', reportName='" + reportName + ",\n" +
                "          document=" + document + "\n" +
                "          creator='" + creator + "', createTime='" + createTime + "'}";
    }

    // Getters and Setters
    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportName() {
        return reportName;
    }

    public void setReportName(String reportName) {
        this.reportName = reportName;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }
}

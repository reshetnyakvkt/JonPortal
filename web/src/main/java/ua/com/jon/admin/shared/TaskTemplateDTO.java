package ua.com.jon.admin.shared;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 20.04.13
 * Time: 21:22
 */
public class TaskTemplateDTO implements Serializable {

    private static final Long SerialVersionUID = 1L;

    private Long id;
    private String name;
    private String text;
    private String type;
    private String className;
    private String testName;

    public TaskTemplateDTO() {
    }

    public TaskTemplateDTO(String name) {
        this.name = name;
    }

    public TaskTemplateDTO(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public TaskTemplateDTO(Long id, String name, String text, String type, String className, String testName) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.type = type;
        this.className = className;
        this.testName = testName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    @Override
    public String toString() {
        return "TaskTemplateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                ", className='" + className + '\'' +
                ", testName='" + testName + '\'' +
                '}';
    }
}

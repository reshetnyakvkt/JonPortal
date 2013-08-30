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

    public TaskTemplateDTO() {
    }

    public TaskTemplateDTO(String name) {
        this.name = name;
    }

    public TaskTemplateDTO(String name, String text) {
        this.name = name;
        this.text = text;
    }

    public TaskTemplateDTO(Long id, String name, String text, String type) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.type = type;
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

    @Override
    public String toString() {
        return "TaskTemplateDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", text='" + text + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

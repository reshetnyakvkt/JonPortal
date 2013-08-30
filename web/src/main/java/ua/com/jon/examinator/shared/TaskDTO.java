package ua.com.jon.examinator.shared;

import ua.com.jon.common.domain.Task;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/10/13
 */
public class TaskDTO implements Serializable {

    private Long id;
    private String text;
    private String name;
    private String status;
    private String result;

    public TaskDTO(Long id, String text, String name, String status, String result) {
        this.id = id;
        this.text = text;
        this.name = name;
        this.status = status;
        this.result = result;
    }

    public TaskDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getName() {
        return name;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}

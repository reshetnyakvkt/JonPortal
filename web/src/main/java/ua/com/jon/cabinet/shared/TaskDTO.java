package ua.com.jon.cabinet.shared;

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
    private String code;
    private String type;

    public TaskDTO(Long id, String text, String name, String status, String result, String code, String type) {
        this.id = id;
        this.text = text;
        this.name = name;
        this.status = status;
        this.result = result;
        this.code = code;
        this.type = type;
    }

    public TaskDTO() {

    }

    public TaskDTO(TaskDTO taskDTO) {
        this(taskDTO.getId(),
                taskDTO.getText(),
                taskDTO.getName(),
                taskDTO.getStatus(),
                taskDTO.getResult(),
                taskDTO.getCode(),
                taskDTO.getType());
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", text='" + text + '\'' +
                ", result='" + result + '\'' +
                ", code='" + code + '\'' +
                '}';
    }
}

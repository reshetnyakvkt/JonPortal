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
    private String userName;
    private String className;
    private Long taskTemplateId;
    private Long groupId;
    private String material;

    public TaskDTO(Long id, String text, String name, String status, String result, String code, String type,
                   String userName, String className, Long taskTemplateId, String material, Long groupId) {
        this.id = id;
        this.text = text;
        this.name = name;
        this.status = status;
        this.result = result;
        this.code = code;
        this.type = type;
        this.userName = userName;
        this.className = className;
        this.taskTemplateId = taskTemplateId;
        this.material = material;
        this.groupId = groupId;
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
                taskDTO.getType(),
                taskDTO.getUserName(),
                taskDTO.getClassName(),
                taskDTO.getTaskTemplateId(),
                taskDTO.getMaterial(),
                taskDTO.getGroupId());
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

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Long getTaskTemplateId() {
        return taskTemplateId;
    }

    public void setTaskTemplateId(Long taskTemplateId) {
        this.taskTemplateId = taskTemplateId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", name='" + name + '\'' +
                ", status='" + status + '\'' +
                ", result='" + result + '\'' +
                ", code='" + code + '\'' +
                ", type='" + type + '\'' +
                ", userName='" + userName + '\'' +
                ", className='" + className + '\'' +
                ", taskTemplateId=" + taskTemplateId +
                ", material='" + material + '\'' +
                '}';
    }
}

package ua.com.jon.common.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 1/15/14
 */
public class TaskDTO implements Serializable {
    private String templateName;
    private String templateDesk;
    private String status;
    private String userName;

    public TaskDTO(String templateName, String templateDesk, String status, String userName) {
        this.templateName = templateName;
        this.templateDesk = templateDesk;
        this.status = status;
        this.userName = userName;
    }

    public String getTemplateName() {
        return templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateDesk() {
        return templateDesk;
    }

    public void setTemplateDesk(String templateDesk) {
        this.templateDesk = templateDesk;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "templateName='" + templateName + '\'' +
                ", templateDesk='" + templateDesk + '\'' +
                ", status=" + status +
                ", userName='" + userName + '\'' +
                '}';
    }
}

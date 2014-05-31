package ua.com.jon.common.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 31.05.14
 * Time: 21:35
 * To change this template use File | Settings | File Templates.
 */

@Entity
@Table(name = "TASKS_HISTORY")
public class TaskHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 5000)
    private String code;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private TaskTemplate taskTemplate;

    @Column(length = 10)
    private String userName;

    @Temporal(value = TemporalType.DATE)
    private Date date;

    @Column(length = 200)
    private String result;

    public TaskHistory() {
    }

    public TaskHistory(String code, TaskTemplate taskTemplate, String userName, Date date, String result) {
        this.code = code;
        this.taskTemplate = taskTemplate;
        this.userName = userName;
        this.date = date;
        this.result = result;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TaskTemplate getTaskTemplate() {
        return taskTemplate;
    }

    public void setTaskTemplate(TaskTemplate taskTemplate) {
        this.taskTemplate = taskTemplate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "TaskHistory{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", taskTemplate=" + taskTemplate +
                ", userName='" + userName + '\'' +
                ", date=" + date +
                ", result='" + result + '\'' +
                '}';
    }
}

package ua.com.jon.common.domain;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 27.05.13
 * Time: 22:00
 */

@Entity(name = "TASKS")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "template_id")
    private TaskTemplate taskTemplate;

    @ManyToOne
    private Sprint sprint;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Group group;

    @Column
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(length = 20000)
    private String code;

    @Column(length = 20000)
    private String result;

    public Task() {
    }

    public Task(User user, TaskTemplate taskTemplate, Sprint sprint,
                Status status, String result, String code, Group group) {
        this.user = user;
        this.taskTemplate = taskTemplate;
        this.sprint = sprint;
        this.status = status;
        this.result = result;
        this.code = code;
        this.group = group;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public TaskTemplate getTaskTemplate() {
        return taskTemplate;
    }

    public void setTaskTemplate(TaskTemplate taskTemplate) {
        this.taskTemplate = taskTemplate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", user=" + user +
                ", taskTemplate=" + taskTemplate +
                ", sprint=" + sprint +
                ", status=" + status +
                ", group=" + group.getName() +
                ", result='" + result + '\'' +
                '}';
    }
}


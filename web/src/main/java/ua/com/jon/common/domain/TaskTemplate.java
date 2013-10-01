package ua.com.jon.common.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 27.05.13
 * Time: 21:57
 */
@Entity
@Table(name = "TASK_TEMPLATES")
public class TaskTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(length = 20000)
    private String taskText;

    private String name;

    private String materials;

    @Column(name = "CLASS_NAME")
    private String className;

    @OneToMany(mappedBy = "taskTemplate", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<Task>();

    @ManyToOne
    private Sprint sprint;

    @Column
    @Enumerated(EnumType.STRING)
    private TaskType type;

    private String testName;

    public TaskTemplate() {
    }

    public TaskTemplate(String name, String taskText) {
        this.name = name;
        this.taskText = taskText;
    }

    public TaskTemplate(Long id, String taskText, String name, TaskType type, String className, String materials, String testName) {
        this.id = id;
        this.taskText = taskText;
        this.name = name;
        this.type = type;
        this.className = className;
        this.materials = materials;
        this.testName = testName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getMaterials() {
        return materials;
    }

    public void setMaterials(String materials) {
        this.materials = materials;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sprint getSprint() {
        return sprint;
    }

    public void setSprint(Sprint sprint) {
        this.sprint = sprint;
    }

    public TaskType getType() {
        return type;
    }

    public void setType(TaskType type) {
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
        return "TaskTemplate{" +
                "id=" + id +
                ", taskText='" + taskText + '\'' +
                ", name='" + name + '\'' +
                ", materials=" + materials +
                ", className='" + className + '\'' +
                ", tasks=" + tasks +
                ", sprint=" + sprint +
                ", type=" + type +
                ", testName='" + testName + '\'' +
                '}';
    }
}

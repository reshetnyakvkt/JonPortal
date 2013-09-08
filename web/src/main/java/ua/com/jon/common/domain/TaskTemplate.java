package ua.com.jon.common.domain;

import javax.persistence.*;
import java.sql.Blob;
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

    private Blob materials;

    @OneToMany(mappedBy = "taskTemplate", cascade = CascadeType.ALL)
    private Set<Task> tasks = new HashSet<Task>();

    @ManyToOne
    private Sprint sprint;

    @Column
    @Enumerated(EnumType.STRING)
    private TaskType type;

    public TaskTemplate() {
    }

    public TaskTemplate(String name, String taskText) {
        this.name = name;
        this.taskText = taskText;
    }

    public TaskTemplate(Long id, String taskText, String name, TaskType type) {
        this.id = id;
        this.taskText = taskText;
        this.name = name;
        this.sprint = sprint;
        this.type = type;
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

    public Blob getMaterials() {
        return materials;
    }

    public void setMaterials(Blob materials) {
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

    @Override
    public String toString() {
        return "TaskTemplate{" +
                "id=" + (id != null?id:"") +
                ", name='" + name + '\'' +
                ", taskText='" + taskText + '\'' +
                ", materials=" + (materials != null?materials:"") +
                ", sprint=" + (sprint != null?sprint.getName():"") +
                '}';
    }
}

package ua.com.jon.examinator.shared;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/23/13
 */
public class SprintDTO implements Serializable {
    private String name;
    private boolean active;
    private List<TaskDTO> tasks;

    public SprintDTO() {
    }

    public SprintDTO(String name, boolean active, List<TaskDTO> tasks) {
        this.name = name;
        this.active = active;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public String toString() {
        return "SprintDTO{" +
                "name='" + name + '\'' +
                ", active=" + active +
                ", tasks=" + tasks +
                '}';
    }
}

package ua.com.jon.admin.shared;

import ua.com.jon.admin.client.components.list.Nameble;

import java.io.Serializable;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/23/13
 */
public class SprintDTO implements Serializable, Nameble {

    private static final Long SerialVersionUID = 1L;

    private Long id;
    private String name;
    private String type;
    private boolean active;
    private List<TaskTemplateDTO> tasks;

    public SprintDTO() {
    }

    public SprintDTO(String name) {
        this.name = name;
    }

    public SprintDTO(Long id, String name, boolean active, String type, List<TaskTemplateDTO> tasks) {
        this.id = id;
        this.name = name;
        this.active = active;
        this.type = type;
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TaskTemplateDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskTemplateDTO> tasks) {
        this.tasks = tasks;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SprintDTO sprintDTO = (SprintDTO) o;

        if (!name.equals(sprintDTO.name)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString() {
        return "SprintDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", active=" + active +
                ", tasks=" + tasks +
                '}';
    }
}

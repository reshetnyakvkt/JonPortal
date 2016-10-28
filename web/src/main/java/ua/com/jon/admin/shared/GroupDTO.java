package ua.com.jon.admin.shared;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 20.04.13
 */
public class GroupDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private  Long groupId;
    private String name;
    private List<TaskTemplateDTO> tasks;

    public GroupDTO() {
        this.name = "";
        this.tasks = new ArrayList<>();
    }

    public GroupDTO(Long id, String name, List<TaskTemplateDTO> tasks) {
        this.groupId = id;
        this.name = name;
        this.tasks = tasks;
    }

    public GroupDTO(String name) {
        this.name = name;
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

    public void setTasks(ArrayList<TaskTemplateDTO> tasks) {
        this.tasks = tasks;
    }

    public Long getId() {
        return groupId;
    }

    public void setId(Long groupId) {
        this.groupId = groupId;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "name='" + name + '\'' +
                ", id=" + groupId +
                ", tasks=" + tasks +
                '}';
    }
}

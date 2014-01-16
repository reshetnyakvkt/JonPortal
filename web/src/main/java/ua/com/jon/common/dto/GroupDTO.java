package ua.com.jon.common.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 1/15/14
 */
public class GroupDTO implements Serializable {
    private List<TaskDTO> tasks = new ArrayList<TaskDTO>();
    private String name;
    private boolean status;
    private String repository;

    public GroupDTO(List<TaskDTO> tasks, String groupName, boolean groupStatus, String repository) {
        this.tasks = tasks;
        this.name = groupName;
        this.status = groupStatus;
        this.repository = repository;
    }

    public List<TaskDTO> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    @Override
    public String toString() {
        return "GroupDTO{" +
                "tasks=" + tasks.size() +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", repository='" + repository + '\'' +
                '}';
    }
}

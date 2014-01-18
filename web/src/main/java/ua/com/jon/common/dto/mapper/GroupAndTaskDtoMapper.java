package ua.com.jon.common.dto.mapper;

import ua.com.jon.common.domain.Group;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.dto.TaskDTO;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 1/16/14
 */
public class GroupAndTaskDtoMapper {
    public static GroupDTO domainToDto(Group group) {
        return new GroupDTO(tasksToDto(group.getTasks()), group.getName(), group.isActive(), group.getRepositoryUrl());
    }

    private static List<TaskDTO> tasksToDto(Set<Task> tasks) {
        List<TaskDTO> taskDtos = new ArrayList<TaskDTO>(tasks.size());
        for (Task task : tasks) {
            taskDtos.add(new TaskDTO(
                    task.getTaskTemplate().getName(),
                    task.getTaskTemplate().getTaskText(),
                    task.getStatus().name(),
                    task.getUser().getLogin()));
        }
        return taskDtos;
    }
}

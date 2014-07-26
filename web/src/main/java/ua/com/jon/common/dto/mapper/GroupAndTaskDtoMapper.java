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
/*        if(group == null) {
            return new GroupDTO();
        }*/
        return new GroupDTO(group.getId(), tasksToDto(group.getTasks()), group.getName(), group.isActive(),
                group.getRepositoryUrl(), group.getCode());
    }

    private static List<TaskDTO> tasksToDto(Set<Task> tasks) {
        List<TaskDTO> taskDtos = new ArrayList<TaskDTO>(tasks.size());
        for (Task task : tasks) {
            taskDtos.add(new TaskDTO(
                    task.getId(),
                    task.getTaskTemplate().getName(),
                    task.getTaskTemplate().getTaskText(),
                    task.getStatus().name(),
                    task.getUser().getLogin(),
                    task.getTaskTemplate().getTestName()));
        }
        return taskDtos;
    }

    public static List<GroupDTO> domainsToDtos(List<Group> groups) {
        List<GroupDTO> groupDtos = new ArrayList<GroupDTO>(groups.size());
        for (Group group : groups) {
            groupDtos.add(
                    new GroupDTO(
                            group.getId(),
                            tasksToDto(group.getTasks()),
                            group.getName(),
                            group.isActive(),
                            group.getRepositoryUrl(),
                            group.getCode()
                    )
            );
        }
        return groupDtos;
    }
}

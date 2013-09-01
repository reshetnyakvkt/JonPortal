package ua.com.jon.common.dto.mapper;

import ua.com.jon.cabinet.shared.TaskDTO;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.domain.TaskTemplate;
import ua.com.jon.common.domain.TaskType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/25/13
 */
public class TaskDtoMapper {
    public static TaskDTO domainToDto(Task task) {

        TaskTemplate taskTemplate = task.getTaskTemplate();
        String taskText = "";
        String taskName = "";
        TaskType taskType = TaskType.CLASS;
        if (task.getTaskTemplate() != null) {
            taskText = taskTemplate.getTaskText();
            taskName = taskTemplate.getName();
            taskType = taskTemplate.getType();
        }

        return new TaskDTO(
                task.getId(),
                taskText,
                taskName,
                task.getStatus().name(),
                task.getResult(),
                task.getCode(),
                taskType.name()
        );
    }

    public static List<TaskDTO> domainsToDtos(List<Task> tasks) {
        List<TaskDTO> taskDTOs = new ArrayList<TaskDTO>(tasks.size());
        for (Task task : tasks) {
            taskDTOs.add(domainToDto(task));
        }
        return taskDTOs;
    }

    public static List<ua.com.jon.examinator.shared.TaskDTO> domainsToExamineDtos(List<Task> tasks) {
        List<ua.com.jon.examinator.shared.TaskDTO> taskDTOs = new ArrayList<ua.com.jon.examinator.shared.TaskDTO>(tasks.size());
        for (Task task : tasks) {
            taskDTOs.add(domainToExamDto(task));
        }
        return taskDTOs;
    }

    private static ua.com.jon.examinator.shared.TaskDTO domainToExamDto(Task task) {
        return new ua.com.jon.examinator.shared.TaskDTO(
                task.getId(),
                task.getTaskTemplate().getTaskText(),
                task.getTaskTemplate().getName(),
                task.getStatus().name(),
                task.getResult()
        );
    }
}

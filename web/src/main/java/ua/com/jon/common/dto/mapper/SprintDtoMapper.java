package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.Task;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/29/13
 */
public class SprintDtoMapper {
    public static SprintDTO domainToDto(Sprint sprint) {
        return new SprintDTO(
                sprint.getId(),
                sprint.getName(),
                sprint.getActive(),
                TaskTemplateDtoMapper.domainsToDtos(sprint.getTasks())
        );
    }

    public static SprintDTO domainToDto(Sprint sprint, List<Task> tasks) {
        return new SprintDTO(
                sprint.getId(),
                sprint.getName(),
                sprint.getActive(),
                TaskConverter.convertToTaskTemplate(tasks)
        );
    }

    public static ua.com.jon.cabinet.shared.SprintDTO domainToDto(List<Task> tasks, Sprint sprint) {
        return new ua.com.jon.cabinet.shared.SprintDTO(
                sprint.getName(),
                sprint.getActive(),
                TaskDtoMapper.domainsToDtos(tasks)
        );
    }

    public static ua.com.jon.examinator.shared.SprintDTO cabinetDtoToExamine(List<Task> tasks, Sprint sprint) {
        return new ua.com.jon.examinator.shared.SprintDTO(
                sprint.getName(),
                sprint.getActive(),
                TaskDtoMapper.domainsToExamineDtos(tasks)
        );
    }
}

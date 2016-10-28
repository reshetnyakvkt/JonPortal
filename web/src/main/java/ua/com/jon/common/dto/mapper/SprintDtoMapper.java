package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.common.domain.*;
import ua.com.jon.examinator.shared.TaskDTO;

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
                sprint.getType().name(),
                TaskTemplateDtoMapper.domainsToDtos(sprint.getTasks()),
                sprint.getEndDate()
        );
    }

    public static SprintDTO domainToDto(Sprint sprint, List<Task> tasks) {
        return new SprintDTO(
                sprint.getId(),
                sprint.getName(),
                sprint.getActive(),
                sprint.getType().name(),
                TaskConverter.convertToTaskTemplate(tasks),
                sprint.getEndDate()
        );
    }

    public static ua.com.jon.cabinet.shared.SprintDTO domainToDto(List<Task> tasks, Sprint sprint, Double rate) {
        return new ua.com.jon.cabinet.shared.SprintDTO(
                sprint.getId(),
                sprint.getName(),
                sprint.getActive(),
                TaskDtoMapper.domainsToDtos(tasks, rate)
        );
    }

    public static ua.com.jon.examinator.shared.SprintDTO cabinetDtoToExamine(Sprint sprint) {
        return new ua.com.jon.examinator.shared.SprintDTO(
                sprint.getName(),
                sprint.getActive(),
                domainsToDtos(sprint.getTasks()),
                sprint.getType().toString()
        );
    }

    public static ua.com.jon.examinator.shared.ExamineSprintDTO cabinetDtoToExamineDto(Sprint sprint) {
        return new ua.com.jon.examinator.shared.ExamineSprintDTO(
                sprint.getName(),
                sprint.getActive(),
                domainsToExamineDtos(sprint.getTasks()),
                sprint.getType().toString()
        );
    }

    private static List<TaskDTO> domainsToExamineDtos(List<TaskTemplate> taskTemplates) {
        if(taskTemplates == null) {
            return null;
        }
        ArrayList<ua.com.jon.examinator.shared.TaskDTO> taskDTOs = new ArrayList<>(taskTemplates.size());
        for (TaskTemplate taskTemplate : taskTemplates) {
            taskDTOs.add(TaskDtoMapper.domainToExamDto(
                    new Task(new User(), taskTemplate, new Sprint(), Status.NEW, "", "", new Group()), false));
        }
        return taskDTOs;
    }

    private static List<ua.com.jon.examinator.shared.TaskTemplateDTO> domainsToDtos(List<TaskTemplate> taskTemplates) {
        if(taskTemplates == null) {
            return null;
        }
        ArrayList<ua.com.jon.examinator.shared.TaskTemplateDTO> taskDTOs = new ArrayList<>(taskTemplates.size());
        for (TaskTemplate taskTemplate : taskTemplates) {
            taskDTOs.add(TaskTemplateDtoMapper.domainToExamDto(taskTemplate));
        }
        return taskDTOs;
    }

    public static List<Sprint> convertSprintDtosToEntity(List<Sprint> sprintList, List<SprintDTO> sprintDtoMap) {
        List<Sprint> sprints = new ArrayList<Sprint>();
        for (SprintDTO sprintDTO : sprintDtoMap) {
            String sprintName = sprintDTO.getName();
            int index = sprintList.indexOf(new Sprint(sprintDTO.getId(), sprintDTO.getName(), SprintType.IT_CENTRE,
                    sprintDTO.getEndDate(), sprintDTO.isActive()));
            Sprint sprint;
            if(index >= 0) {
                sprint = sprintList.get(index);
                sprint.setId(sprintDTO.getId());
                sprint.setName(sprintDTO.getName());
                sprint.setActive(sprintDTO.isActive());
                sprint.setTasks(TaskTemplateDtoMapper.convertTaskDtosToEntity(sprintDTO.getTasks()));
            } else {
                sprint = new Sprint(sprintDTO.getId(), sprintName, SprintType.IT_CENTRE, sprintDTO.getEndDate(),
                        sprintDTO.isActive());
            }
            sprints.add(sprint);
        }
        return sprints;
    }
}

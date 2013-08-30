package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.TaskTemplateDTO;
import ua.com.jon.common.domain.TaskTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/27/13
 */
public class TaskTemplateDtoMapper {
    public static TaskTemplateDTO domainToDto(TaskTemplate taskTemplate) {
        return new TaskTemplateDTO(
                taskTemplate.getId(),
                taskTemplate.getName(),
                taskTemplate.getTaskText(),
                taskTemplate.getType() == null? null: taskTemplate.getType().name()
        );
    }

    public static ArrayList<TaskTemplateDTO> domainsToDtos(List<TaskTemplate> taskTemplates) {
        if(taskTemplates == null) {
            return null;
        }
        ArrayList<TaskTemplateDTO> taskDTOs = new ArrayList<TaskTemplateDTO>(taskTemplates.size());
        for (TaskTemplate taskTemplate : taskTemplates) {
            taskDTOs.add(domainToDto(taskTemplate));
        }
        return taskDTOs;
    }
}

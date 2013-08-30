package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.GroupDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.cabinet.shared.TaskDTO;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.domain.TaskTemplate;
import ua.com.jon.common.domain.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/27/13
 */
public class GroupDtoMapper {

    public static GroupDTO domainToDto(Group group, ArrayList<TaskTemplate> tasks) {
        return new GroupDTO(group.getName(), TaskTemplateDtoMapper.domainsToDtos(tasks));
    }
}

package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.GroupDTO;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.domain.TaskTemplate;

import java.util.ArrayList;

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

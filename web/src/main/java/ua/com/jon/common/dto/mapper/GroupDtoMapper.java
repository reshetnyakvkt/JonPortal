package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.GroupDTO;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.domain.TaskTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/27/13
 */
public class GroupDtoMapper {

    public static GroupDTO domainToDto(Group group, ArrayList<TaskTemplate> tasks) {
        return new GroupDTO(group.getId(), group.getName(), TaskTemplateDtoMapper.domainsToDtos(tasks));
    }

    public static ArrayList<ua.com.jon.cabinet.shared.GroupDTO> domainToAdminDtos(List<Group> groups) {
        ArrayList<ua.com.jon.cabinet.shared.GroupDTO> groupDTOs = new ArrayList<ua.com.jon.cabinet.shared.GroupDTO>(groups.size());
        for (Group group : groups) {
            groupDTOs.add(domainToAdminDto(group));
        }
        return groupDTOs;
    }

    private static ua.com.jon.cabinet.shared.GroupDTO domainToAdminDto(Group group) {
        return new ua.com.jon.cabinet.shared.GroupDTO(group.getId(), group.getName());
    }
}

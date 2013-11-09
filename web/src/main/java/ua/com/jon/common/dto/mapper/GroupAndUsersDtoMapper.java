package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.GroupAndUsersDTO;
import ua.com.jon.common.domain.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 09.11.13
 * Time: 21:48
 * To change this template use File | Settings | File Templates.
 */
public class GroupAndUsersDtoMapper {

    public static ArrayList<GroupAndUsersDTO> domainsToDtos(List<Group> groups) {
        ArrayList<ua.com.jon.admin.shared.GroupAndUsersDTO> groupDTOs = new ArrayList<GroupAndUsersDTO>(groups.size());
        for (Group group : groups) {
            groupDTOs.add(GroupAndUsersDtoMapper.domainToDto(group));
        }
        return groupDTOs;
    }

    public static GroupAndUsersDTO domainToDto(Group group) {
        return new GroupAndUsersDTO(group.getName(), UserDtoMapper.domainsToAdminDtos(group.getUsers()));
    }
}

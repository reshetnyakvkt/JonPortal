package ua.com.jon.common.dto.mapper;

import ua.com.jon.admin.shared.GroupDTO;
import ua.com.jon.admin.shared.SpaceDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;
import ua.com.jon.admin.shared.UserDTO;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.auth.util.UserMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/2/13
 */
public class SpaceDtoMapper {
    public static SpaceDTO spaceToDto(AssemblaSpace space, boolean isActive) {
        return new SpaceDTO(
                space.getName(),
                new ArrayList<UserDTO>()
        );
    }

    public static SpaceDTO spaceToDto(AssemblaSpace space, List<AssemblaUser> users) {
        return new SpaceDTO(
                space.getName(),
                UserMapper.assemblaToDtos(users)
        );
    }

    public static ArrayList<SpaceDTO> spacesToDtos(List<AssemblaSpace> assemblaSpaces, boolean isActive) {
        ArrayList<SpaceDTO> spaces = new ArrayList<SpaceDTO>(assemblaSpaces.size());
        for (AssemblaSpace assemblaSpace : assemblaSpaces) {
            spaces.add(spaceToDto(assemblaSpace, isActive));
        }
        return spaces;
    }
}

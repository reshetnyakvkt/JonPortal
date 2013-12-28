package ua.com.jon.auth.util;

import ua.com.jon.admin.shared.UserDTO;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.auth.domain.SpringUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/30/13
 */
public class UserMapper {
    public static SpringUser convertAssemblaToSpring(AssemblaUser assemblaUser) {
        return new SpringUser(assemblaUser.getLogin(), assemblaUser.getLogin());
    }

    public static ArrayList<UserDTO> assemblaToDtos(List<AssemblaUser> assemblaUsers) {
        ArrayList<UserDTO> userDTOs = new ArrayList<UserDTO>(assemblaUsers.size());
        for (AssemblaUser assemblaUser : assemblaUsers) {
            userDTOs.add(assemblaToDto(assemblaUser));
        }
        return userDTOs;
    }

    public static UserDTO assemblaToDto(AssemblaUser assemblaUser) {
        return new UserDTO(null, assemblaUser.getLogin());

    }
}

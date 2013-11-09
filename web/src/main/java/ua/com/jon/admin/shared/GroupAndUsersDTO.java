package ua.com.jon.admin.shared;

import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 09.11.13
 * Time: 21:44
 * To change this template use File | Settings | File Templates.
 */
public class GroupAndUsersDTO {

    private static final Long SerialVersionUID = 1L;

    private String name;

    private HashSet<UserDTO> users;

    public GroupAndUsersDTO() {
        this.name = "";
        users = new HashSet<UserDTO>();
    }

    public GroupAndUsersDTO(String name, HashSet<UserDTO> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public HashSet<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(HashSet<UserDTO> users) {
        this.users = users;
    }
}

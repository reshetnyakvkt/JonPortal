package ua.com.jon.admin.shared;

import java.io.Serializable;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 09.11.13
 */
public class GroupAndUsersDTO implements Serializable {

    private static final Long serialVersionUID = 1L;

    private Long id;
    private String name;
    private HashSet<UserDTO> users;

    public GroupAndUsersDTO() {
        this.name = "";
        users = new HashSet<UserDTO>();
    }

    public GroupAndUsersDTO(Long id, String name, HashSet<UserDTO> users) {
        this.id = id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GroupAndUsersDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}

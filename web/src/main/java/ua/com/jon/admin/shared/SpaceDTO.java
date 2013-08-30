package ua.com.jon.admin.shared;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/2/13
 */
public class SpaceDTO implements Serializable {
    private String name;
    private ArrayList<UserDTO> users;

    public SpaceDTO() {
    }

    public SpaceDTO(String name, ArrayList<UserDTO> users) {
        this.name = name;
        this.users = users;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<UserDTO> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<UserDTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "SpaceDTO{" +
                "name='" + name + '\'' +
                ", users=" + users +
                '}';
    }
}

package ua.com.jon.admin.shared;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/1/13
 */
public class UserDTO implements Serializable {
    private String name;

    public UserDTO() {
    }

    public UserDTO(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "name='" + name + '\'' +
                '}';
    }
}

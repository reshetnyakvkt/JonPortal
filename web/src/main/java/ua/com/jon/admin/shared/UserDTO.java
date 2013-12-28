package ua.com.jon.admin.shared;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 7/1/13
 */
public class UserDTO implements Serializable {

    private Long id;
    private String name;

    public UserDTO() {
    }

    public UserDTO(Long id, String login) {
        this.id = id;
        this.name = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}

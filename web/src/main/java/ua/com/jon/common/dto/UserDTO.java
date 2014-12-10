package ua.com.jon.common.dto;

import java.io.Serializable;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 10.09.14
 */
public class UserDTO implements Serializable {
    private long id;
    private String userName;
    private boolean admin;
    private boolean ignore;

    public UserDTO(long id, String userName, boolean admin, boolean ignore) {
        this.id = id;
        this.userName = userName;
        this.admin = admin;
        this.ignore = ignore;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public boolean isIgnore() {
        return ignore;
    }

    public void setIgnore(boolean ignore) {
        this.ignore = ignore;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", admin='" + admin + '\'' +
                ", ignore=" + ignore +
                '}';
    }
}

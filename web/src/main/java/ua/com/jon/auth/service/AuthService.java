package ua.com.jon.auth.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.common.domain.User;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 29.03.14
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
public interface AuthService {
    UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException;

    boolean isAuth(String space, String userName);

    AssemblaUser getAssemblaUser(String space, String userName);

    List<AssemblaUser> getUsersBySpace(String spaceName);

    AssemblaSpace getSpace(String spaceName);

    List<AssemblaSpace> getSpaces();

    void createNewUser(String login, String password);

    User getUserFromDBByName(String login);

    void updateUser(User user);

    User getDBUser(String login, String password);
}

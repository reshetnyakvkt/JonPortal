package ua.com.jon.common.dao;



import ua.com.jon.common.domain.User;

import java.util.Collection;


/**
 * Date: 01.01.13
 * Time: 23:58
 */
public interface UserDAOInterface {

    public boolean insert(User user);

    public User getUser(String nickname, String eMail);

    public boolean getById(Long id);

    public boolean getUserByParam(String nickname, String login, String password, String eMail);

    public boolean update(User user);

    public boolean delete(User user);

    public Collection<User> getAllUsers();

}

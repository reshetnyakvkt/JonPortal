package ua.com.jon.auth.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import ua.com.jon.auth.domain.AssemblaSpace;
import ua.com.jon.auth.domain.AssemblaSpaces;
import ua.com.jon.auth.domain.AssemblaUser;
import ua.com.jon.auth.domain.SpringUser;
import ua.com.jon.auth.exceptions.RestException;
import ua.com.jon.auth.util.UserMapper;
import ua.com.jon.common.domain.User;
import ua.com.jon.common.repository.UserRepository;
import ua.com.jon.utils.RestClient;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/28/13
 */
@Service
public class AuthServiceImpl implements UserDetailsService, AuthService {
    @Autowired
    private RestClient restClient;

    @Autowired
    private UserRepository userRepository;

    private static Logger log = Logger.getLogger(AuthServiceImpl.class);
    /*
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("User login: " + userName);
        SpringUser user;
        try {
            AssemblaUser assemblaUser = restClient.getAssemblaUser(userName);
            user = UserMapper.convertAssemblaToSpring(assemblaUser);
        } catch (ResourceAccessException e) {
            user = new SpringUser(userName, userName);
        } catch (Exception e) {
            log.error("Error user authentication " + userName, e);
            throw new UsernameNotFoundException("User/Password incorrect");
        }
        log.info("Authenticated user info: " + user);

        return user;
    }
    */
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("User login: " + userName);
        SpringUser springUser;
        try {
            //AssemblaUser assemblaUser = restClient.getAssemblaUser(userName);
            //user = UserMapper.convertAssemblaToSpring(assemblaUser);
            User user = userRepository.findByUserName(userName);
            springUser = UserMapper.convertDBToSpring(user);
        } catch (ResourceAccessException e) {
            springUser = new SpringUser(userName, userName);
        } catch (Exception e) {
            log.error("Error user authentication " + userName, e);
            throw new UsernameNotFoundException("User/Password incorrect");
        }
        log.info("Authenticated user info: " + springUser);

        return springUser;
    }

    @Override
    public boolean isAuth(String space, String userName) {
        boolean res = false;
        AssemblaUser assemblaUser = null;
        try {
            assemblaUser = restClient.getUserBySpaceAndUName(space, userName);
        } catch (RestException e) {
            log.error(e);
        }
        if(assemblaUser != null) {
            res = true;
        }
        return res;
    }

    @Override
    public AssemblaUser getAssemblaUser(String space, String userName) {
        AssemblaUser assemblaUser = null;
        try {
            assemblaUser = restClient.getUserBySpaceAndUName(space, userName);
        } catch (ResourceAccessException e) {
            assemblaUser = new AssemblaUser(userName);
        } catch (RestException e) {
            log.error(e);
        }
        return assemblaUser;
    }

    @Override
    public User getDBUser(String login, String password) {
        return userRepository.findByLoginAndPassword(login, password);
    }

    @Override
    public List<AssemblaUser> getUsersBySpace(String spaceName) {
        try {
            return restClient.getUserListBySpace(spaceName);
        } catch (RestException e) {
            log.error(e);
        }
        return new ArrayList<AssemblaUser>();
    }

    @Override
    public AssemblaSpace getSpace(String spaceName) {
        AssemblaSpace assemblaSpace = null;
        try {
            assemblaSpace = restClient.getSpace(spaceName);
        } catch (RestException e) {
            log.error(e);
        }
        return assemblaSpace;
    }

//    public List<AssemblaUser> getUsers() {
//        assemblaUser = restClient.get;
//    }
    @Override
    public List<AssemblaSpace> getSpaces() {
        List<AssemblaSpace> spacesList = new ArrayList<AssemblaSpace>();
        try {

            AssemblaSpaces spaces = restClient.getSpaces();
            return spaces.getSpaces();
        } catch (RestException e) {
            log.error(e);
        }
        return spacesList;
    }

    @Override
    public void createNewUser(String login, String password) {
        User user = new User(login, password, new Date(), null);
        userRepository.save(user);
    }

    @Override
    public User getUserFromDBByName(String login) {
        return userRepository.findByUserName(login);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }
}

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
import ua.com.jon.utils.RestClient;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 3/28/13
 */
@Service
public class AuthService implements UserDetailsService {
    @Autowired
    private RestClient restClient;

    private static Logger log = Logger.getLogger(AuthService.class);

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("User login: " + userName);
        SpringUser user;
        try {
            AssemblaUser assemblaUser = restClient.getUser(userName);
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

    public AssemblaUser getUser(String space, String userName) {
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

    public List<AssemblaUser> getUsersBySpace(String spaceName) {
        try {
            return restClient.getUserListBySpace(spaceName);
        } catch (RestException e) {
            log.error(e);
        }
        return new ArrayList<AssemblaUser>();
    }

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
}

package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.jon.common.domain.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/27/13
 */
public interface UserRepository extends CrudRepository<User, Long> {
    @Query("select u from User u where u.group.name = :groupName")
    List<User> findByGroupName(@Param("groupName") String groupName);

    @Query("select u from User u where u.login in (:names)")
    List<User> findByNames(@Param("names") Collection<String> names);

    @Query("select u from User u where u.login = :name")
    User findByUserName(@Param("name") String name);

}

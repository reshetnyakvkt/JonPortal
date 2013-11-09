package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ua.com.jon.common.domain.Group;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/26/13
 */
public interface GroupRepository extends CrudRepository<Group, Long> {

    @Query("select g from Group g where g.name = ?1")
    Group findByName(String name);

    @Query("select g from Group g JOIN FETCH g.users")
    List<Group> findAllGroupsAndUsers ();
}

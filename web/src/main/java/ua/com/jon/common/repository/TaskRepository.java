package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.jon.common.domain.Task;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/23/13
 */
public interface TaskRepository extends JpaRepository<Task, Long> {

    @Query("select t from ua.com.jon.common.domain.Task t where t.sprint.name = :sprintName")
    List<Task> findBySprintName(@Param("sprintName") String sprintName);

    @Query("select t from ua.com.jon.common.domain.Task t where t.user.login = :userName")
    List<Task> findByUserName(@Param("userName") String userName);

    @Query("select t from ua.com.jon.common.domain.Task t where t.user.group.id = :groupId and t.result <> ''")
    List<Task> findEvaluatedByGroupId(@Param("groupId") Long groupId);

    //@Query("select t from ua.com.jon.common.domain.Task t where t.user.login = :userName and t.sprint.name = :sprintName")
    @Query("select t from ua.com.jon.common.domain.Task t JOIN FETCH t.taskTemplate where t.user.login = :userName and t.sprint.name = :sprintName")
    List<Task> findByUserAndSprint(@Param("userName") String userName, @Param("sprintName") String name);
}

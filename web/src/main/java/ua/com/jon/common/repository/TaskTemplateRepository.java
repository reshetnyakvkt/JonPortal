package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.jon.common.domain.TaskTemplate;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/26/13
 */
public interface TaskTemplateRepository extends CrudRepository<TaskTemplate, Long> {

/*    @Query("select t from ua.com.jon.common.domain.TaskTemplate t where t.sprint.name = :sprintName order by t.taskTemplate.name")
    List<Task> findBySprintName(@Param("sprintName") String sprintName);

    @Query("select tt from TaskTemplate tt where tt.name in (:names)")
    List<TaskTemplate> findByNames(@Param("names") Collection names);*/

    @Query("select t.taskTemplate from ua.com.jon.common.domain.Task t JOIN t.user.groups gs where gs.name = :groupName")
    ArrayList<TaskTemplate> findByGroupName(@Param("groupName") String groupName);
}

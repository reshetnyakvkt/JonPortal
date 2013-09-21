package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.SprintType;
import ua.com.jon.common.domain.User;

import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 6/29/13
 */
public interface SprintRepository extends CrudRepository<Sprint, Long> {

    @Query("select s from Sprint s where s.name = :sprintName")
    Sprint findByName(@Param("sprintName") String sprintName);

    @Query("select s from Sprint s where s.name in (:names)")
    List<Sprint> findByNames(@Param("names") Collection<String> names);

    @Query("select s from Sprint s where s.id in (:ids)")
    List<Sprint> findByIds(@Param("ids") Collection<Long> ids);

    @Query("select s from Sprint s where s.type = (:type)")
    List<Sprint> findByType(@Param("type") SprintType type);
}

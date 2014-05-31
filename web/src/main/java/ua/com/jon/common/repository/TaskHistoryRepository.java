package ua.com.jon.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.jon.common.domain.TaskHistory;

/**
 * Created with IntelliJ IDEA.
 * User: sergey
 * Date: 31.05.14
 * Time: 22:00
 * To change this template use File | Settings | File Templates.
 */
public interface TaskHistoryRepository extends JpaRepository<TaskHistory, Long> {

}

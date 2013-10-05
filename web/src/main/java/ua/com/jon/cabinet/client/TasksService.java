package ua.com.jon.cabinet.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@RemoteServiceRelativePath("/service/tasksService")
public interface TasksService extends RemoteService {
    String greet(String name);
    ArrayList<TaskDTO> getUserTasks();
    void taskStatusChanged(TaskDTO dto);
    ArrayList<SprintDTO> getSprints();
    String postForTest(TaskDTO taskDTO);
    ArrayList<TaskDTO> getTasksByUserGroup(Long id);
}

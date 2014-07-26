package ua.com.jon.cabinet.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.com.jon.cabinet.shared.GroupDTO;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@RemoteServiceRelativePath("/service/tasksService")
public interface TasksService extends RemoteService {
    String greet(String name);
    ArrayList<TaskDTO> getUserTasks();
    String dispatchTaskChecking(TaskDTO dto);
    String taskStatusChanged(TaskDTO dto);
    ArrayList<SprintDTO> getSprints(GroupDTO selectedGroup);
    String postForTest(TaskDTO taskDTO);
    ArrayList<TaskDTO> getTasksByUserGroup(Long taskTemplateId, Long selectedGroupId, Long selectedSprintId);
    double getSprintRate(Long groupId, Long taskTemplateId, String userName);
    double getCourseRate(Long taskTemplateId, String userName);
    String getSpringUserName();
    ArrayList<GroupDTO> getUserGroups();
    void setValidationResult(Long id, String statusStr, String result);

    List<TaskDTO> refreshTasks(List<Long> ids);
    List<List<String>> getGroupInfo(Long selectedGroupId) throws Exception;
}

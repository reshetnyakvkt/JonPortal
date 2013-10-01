package ua.com.jon.admin.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.com.jon.admin.shared.GroupDTO;
import ua.com.jon.admin.shared.SpaceDTO;
import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@RemoteServiceRelativePath("/service/adminService")
public interface AdminService extends RemoteService {

    List<TaskTemplateDTO> getTaskTemplates();

    List<GroupDTO> getGroupsAndTasks();

    void postTasksByNames(GroupDTO groupDto, ArrayList<Long> taskIds, SprintDTO sprintDto);

    ArrayList<SpaceDTO> getSpaces();

    void createGroup(SpaceDTO group) throws Exception;

    void saveSprints(List<SprintDTO> newSprints);

    ArrayList<SprintDTO> getSprintsAndTasks();

    void sprintTypeChanged(SprintDTO dto);

    ArrayList<String> getAvailableTestNames();
}

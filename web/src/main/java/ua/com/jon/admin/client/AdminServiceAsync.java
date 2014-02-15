package ua.com.jon.admin.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.com.jon.admin.shared.GroupAndUsersDTO;
import ua.com.jon.admin.shared.GroupDTO;
import ua.com.jon.admin.shared.SpaceDTO;
import ua.com.jon.admin.shared.SprintDTO;
import ua.com.jon.admin.shared.TaskTemplateDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */

public interface AdminServiceAsync {

    void getTaskTemplates(AsyncCallback<List<TaskTemplateDTO>> async);

    void getGroupsAndTasks(AsyncCallback<List<GroupDTO>> async);

    void getSprintsAndTasks(AsyncCallback<ArrayList<SprintDTO>> callback);

    void postTasksByNames(GroupDTO name, ArrayList<Long> taskNames, SprintDTO sprint, AsyncCallback<Void> callback);

    void getSpaces(AsyncCallback<ArrayList<SpaceDTO>> async);

    void createGroup(SpaceDTO group, AsyncCallback<Void> groupCallback);

    void saveSprints(List<SprintDTO> newSprints, AsyncCallback groupCallback);

    void saveGroups(ArrayList<GroupAndUsersDTO> newSprints, AsyncCallback groupCallback);

    void saveGroup(GroupAndUsersDTO newGroup, AsyncCallback groupCallback);

    void sprintTypeChanged(SprintDTO dto, AsyncCallback<Void> asyncCallback);

    void getAvailableTestNames(AsyncCallback<ArrayList<String>> async);

    void getTasksByGroup(String name, AsyncCallback<List<ua.com.jon.admin.shared.TaskDTO>> async);

    void getGroupsAndUsers(AsyncCallback<ArrayList<GroupAndUsersDTO>> async);

    void deleteGroup(Long id, AsyncCallback<Void> callback);

    void deleteUserFromGroup(Long groupId, Long userId, AsyncCallback<Void> callback);

    void deleteUser(Long userId, AsyncCallback<Void> async);

    void addStudentToGroup(String groupName, String userName, AsyncCallback<Void> async);
}


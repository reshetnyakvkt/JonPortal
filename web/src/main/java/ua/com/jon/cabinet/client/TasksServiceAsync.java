package ua.com.jon.cabinet.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */

public interface TasksServiceAsync {
    void greet(String name, AsyncCallback<String> callback);
    void getUserTasks(AsyncCallback<ArrayList<TaskDTO>> async);
    void taskStatusChanged(TaskDTO dto, AsyncCallback<Void> async);
    void getSprints(AsyncCallback<ArrayList<SprintDTO>> callback);
    void postForTest(TaskDTO taskDTO, AsyncCallback<String> callback);
    void getTasksByUserGroup(AsyncCallback<ArrayList<TaskDTO>> async);
}


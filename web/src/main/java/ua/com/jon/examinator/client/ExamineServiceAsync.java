package ua.com.jon.examinator.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.com.jon.examinator.shared.SprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */

public interface ExamineServiceAsync {
    void greet(String name, AsyncCallback<String> callback);
    void getUserTasks(AsyncCallback<ArrayList<TaskDTO>> async);
    void taskStatusChanged(TaskDTO dto, AsyncCallback<Void> async);
    void getSprints(AsyncCallback<ArrayList<SprintDTO>> callback);
    void postForTest(TaskDTO taskDTO, AsyncCallback<String> callback);
}


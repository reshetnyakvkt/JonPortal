package ua.com.jon.examinator.client;

import com.google.gwt.user.client.rpc.AsyncCallback;
import ua.com.jon.examinator.shared.ExamineSprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;
import ua.com.jon.examinator.shared.TaskHistoryDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */

public interface ExamineServiceAsync {
    void getSprints(AsyncCallback<List<ExamineSprintDTO>> callback);
    void postForTest(TaskDTO taskDTO, String userName, AsyncCallback<String> callback);
    void getTaskHistoryByHash(String hash, AsyncCallback<TaskHistoryDto> callback);
}


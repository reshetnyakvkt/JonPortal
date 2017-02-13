package ua.com.jon.examinator.client;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;
import ua.com.jon.examinator.shared.ExamineSprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;
import ua.com.jon.examinator.shared.TaskHistoryDto;

import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@RemoteServiceRelativePath("/service/examineService")
public interface ExamineService extends RemoteService {
    List<ExamineSprintDTO> getSprints();
    String postForTest(TaskDTO taskDTO, String userName);
    TaskHistoryDto getTaskHistoryByHash(String hash);
}

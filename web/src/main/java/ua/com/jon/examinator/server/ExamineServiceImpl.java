package ua.com.jon.examinator.server;


import com.jon.tron.exception.CompilationException;
import com.jon.tron.service.processor.ClassProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.jon.auth.domain.SpringUser;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.SprintType;
import ua.com.jon.common.domain.Status;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.dto.mapper.SprintDtoMapper;
import ua.com.jon.common.repository.SprintRepository;
import ua.com.jon.common.repository.TaskRepository;
import ua.com.jon.examinator.client.ExamineService;
import ua.com.jon.examinator.shared.SprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@Service("examineService")
public class ExamineServiceImpl implements ExamineService {
    private static final Logger log = Logger.getLogger(ExamineServiceImpl.class);

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private SprintRepository sprintRepository;

    @Autowired
    private ClassProcessor classProcessor;

    @Override
    public String greet(String name) {
        System.out.println(name);
        return "great";
    }

    @Override
    public ArrayList<TaskDTO> getUserTasks() {
        SpringUser springUser = (SpringUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = springUser.getUsername();
        List<Task> tasks = taskRepository.findByUserName(userName);
        ArrayList<TaskDTO> taskDtos = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
//            taskDtos.add(TaskDtoMapper.domainToDto(task));
        }
//        System.out.println(tasks);
        return taskDtos;
    }

    @Override
    public void taskStatusChanged(TaskDTO dto) {
        Task task = taskRepository.findOne(dto.getId());
        Status newStatus = Status.valueOf(dto.getStatus());
        task.setStatus(newStatus);
        taskRepository.save(task);

        System.out.println("taskStatusChanged " + dto);
    }

    @Override
    public ArrayList<SprintDTO> getSprints() {
/*        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SpringUser springUser;
        String userName;
        if(authentication instanceof String) {
            //throw new SecurityException("can't grant access to anonymus ");
            userName = "anonimous";
        } else {
            springUser = (SpringUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            userName = springUser.getUsername();
        }*/
        Iterable<Sprint> sprintIterable = sprintRepository.findByType(SprintType.ANONYMOUS);
        ArrayList<SprintDTO> sprints = new ArrayList<SprintDTO>();
        for (Sprint sprint : sprintIterable) {
            List<Task> tasks = taskRepository.findBySprintName(sprint.getName());
            sprints.add(SprintDtoMapper.cabinetDtoToExamine(tasks, sprint));
        }

        return sprints;
//        List<TaskDTO> tasks1 = getUserTasks();
//        List<TaskDTO> tasks2 = new ArrayList<TaskDTO>();
//        tasks2.add(new TaskDTO("1", "schema", "status", "0%\n qq"));
//
//        SprintDTO sprint1 = new SprintDTO("Core", false);
//        sprint1.setTasks(tasks1);
//
//        SprintDTO sprint2 = new SprintDTO("DB", true);
//        sprint2.setTasks(tasks2);
//
//        ArrayList<SprintDTO> sprints = new ArrayList<SprintDTO>();
//        sprints.add(sprint1);
//        sprints.add(sprint2);
//
//        System.out.println("Sprints: " + sprints);
//        return sprints;
    }

    @Override
    public String postForTest(TaskDTO taskDTO) {
        log.info("Examinator post for test: " + taskDTO.getCode());
        Map.Entry<String, String> resultEntry;
        try {
            resultEntry = classProcessor.processClass(taskDTO.getClassName(), taskDTO.getCode(), taskDTO.getName());
        } catch (CompilationException e) {
            resultEntry = e.getResult();
        } catch (Exception e) {
            log.error(e);
            throw new RuntimeException("Внутренняя ошибка. Обратитесь к разработчикам", e);
        }
        String testResult = resultEntry.getKey() + '\n' + resultEntry.getValue();
        log.info("Examinator test result is " + testResult);
        Task task = taskRepository.findOne(taskDTO.getId());
        task.setCode(taskDTO.getCode());
        task.setResult(testResult);
        taskRepository.save(task);

        return testResult;
    }
}

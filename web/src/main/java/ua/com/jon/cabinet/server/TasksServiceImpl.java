package ua.com.jon.cabinet.server;


import org.apache.log4j.Logger;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import ua.com.jon.auth.domain.SpringUser;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.Status;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.dto.mapper.SprintDtoMapper;
import ua.com.jon.common.dto.mapper.TaskDtoMapper;
import ua.com.jon.common.repository.SprintRepository;
import ua.com.jon.common.repository.TaskRepository;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@Service("tasksService")
public class TasksServiceImpl implements TasksService {
    private static final Logger log = Logger.getLogger(TasksServiceImpl.class);

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private SprintRepository sprintRepository;

    @Override
    public String greet(String name) {
        System.out.println(name);
        return "great";
    }

    @Override
    public ArrayList<TaskDTO> getUserTasks() {
        log.info("--- getUserTasks() ---");
        SpringUser springUser = (SpringUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = springUser.getUsername();
        List<Task> tasks = taskRepository.findByUserName(userName);
        ArrayList<TaskDTO> taskDtos = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
            taskDtos.add(TaskDtoMapper.domainToDto(task));
        }
        log.info("--- " + taskDtos + " ---");
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
        log.info("--- getSprints() ---");
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SpringUser springUser;
        if(authentication instanceof String) {
            throw new SecurityException("can't grant access to anonymus ");
        }
        springUser = (SpringUser)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = springUser.getUsername();
        Iterable<Sprint> sprintIterable = sprintRepository.findAll();
        ArrayList<SprintDTO> sprints = new ArrayList<SprintDTO>();
        for (Sprint sprint : sprintIterable) {
            List<Task> tasks = taskRepository.findByUserAndSprint(userName, sprint.getName());
            sprints.add(SprintDtoMapper.domainToDto(tasks, sprint));
        }
        log.info("--- " + sprints + " ---");
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
        log.info("Post for test: " + taskDTO.getCode());
        return new Random().nextInt(100)+"\n text test";
    }
}

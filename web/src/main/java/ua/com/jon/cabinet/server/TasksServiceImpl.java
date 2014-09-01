package ua.com.jon.cabinet.server;


import com.jon.tron.exception.CompilationException;
import com.jon.tron.service.processor.ClassProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;
import ua.com.jon.auth.domain.SpringUser;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.shared.GroupDTO;
import ua.com.jon.cabinet.shared.SprintDTO;
import ua.com.jon.cabinet.shared.TaskDTO;
import ua.com.jon.common.domain.Group;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.Status;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.domain.TaskTemplate;
import ua.com.jon.common.domain.TaskType;
import ua.com.jon.common.domain.User;
import ua.com.jon.common.dto.mapper.GroupDtoMapper;
import ua.com.jon.common.dto.mapper.SprintDtoMapper;
import ua.com.jon.common.dto.mapper.TaskDtoMapper;
import ua.com.jon.common.repository.GroupRepository;
import ua.com.jon.common.repository.SprintRepository;
import ua.com.jon.common.repository.TaskRepository;
import ua.com.jon.common.repository.TaskTemplateRepository;
import ua.com.jon.common.repository.UserRepository;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@Service("tasksService")
public class TasksServiceImpl implements TasksService, ServletContextAware {
    private static final Logger log = Logger.getLogger(TasksServiceImpl.class);

    @Autowired
    private ClassProcessor classProcessor;

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private UserRepository userRepository;

    @Resource
    private SprintRepository sprintRepository;

    @Resource
    private TaskTemplateRepository templateRepository;

    @Resource
    private GroupRepository groupRepository;

    private ServletContext servletContext;

    @Value("${core.jar}")
    private String coreJarName;

    @Value("${junit.jar}")
    private String junitJarName;

    @Override
    public String greet(String name) {
        log.info(name);
        return "great";
    }

    @Override
    public ArrayList<TaskDTO> getUserTasks() {
        log.info("--- getUserTasks() ---");
        SpringUser springUser = (SpringUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = springUser.getUsername();
        List<Task> tasks = taskRepository.findByUserName(userName);
        ArrayList<TaskDTO> taskDtos = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
            task.setResult("");
            taskDtos.add(TaskDtoMapper.domainToDto(task, 0.0));

        }
        log.debug("--- " + taskDtos + " ---");
        log.info("--- " + taskDtos.size() + " ---");
        return taskDtos;
    }

    @Override
    public String dispatchTaskChecking(TaskDTO dto) {
        String result = "";
        if (dto.getType().equals(TaskType.CLASS.name()) && dto.getStatus().equals("TEST")) {
            log.info("-== Cabinet dispatchTaskChecking: " + dto);
            result = postForTest(dto);
        } else if (dto.getType().equals(TaskType.SVN.name())) {
            result = taskStatusChanged(dto);
        }
//        log.info("dispatchTaskChecking " + dto);
        return result;
    }

    @Override
    public String taskStatusChanged(TaskDTO dto) {
        Task task = taskRepository.findOne(dto.getId());
        Status newStatus = Status.valueOf(dto.getStatus());
        task.setStatus(newStatus);
        taskRepository.save(task);

        log.info("taskStatusChanged " + dto);

        return "";
    }

    @Override
    public ArrayList<SprintDTO> getSprints(GroupDTO selectedGroup) {
        log.info("--- getSprints() ---");
        String userName = getSpringUserName();
        if (selectedGroup == null) {
            return new ArrayList<SprintDTO>();
        }
        Iterable<Sprint> sprintIterable = sprintRepository.findByUserAndGroup(userName, selectedGroup.getId());
        ArrayList<SprintDTO> sprints = new ArrayList<SprintDTO>();
        for (Sprint sprint : sprintIterable) {
            List<Task> tasks = taskRepository.findByUserAndSprintAndGroup(userName, sprint.getId(), selectedGroup.getId());
            sprints.add(SprintDtoMapper.domainToDto(tasks, sprint, 0.0));
        }
        log.info("--- Sent sprint to client " + sprints.size() + " ---");
        return sprints;
    }

    @Override
    public String postForTest(TaskDTO taskDTO) {
        URL resource = TasksServiceImpl.class.getResource("/forbid.policy");
        log.info(resource.getPath());

        Map.Entry<String, String> resultEntry;
        try {
            TaskTemplate template = templateRepository.findOne(taskDTO.getTaskTemplateId());
            String tronCoreJar = servletContext.getRealPath("/WEB-INF") + "/lib/" + coreJarName;
            String junitJar = servletContext.getRealPath("/WEB-INF") + "/lib/" + junitJarName;
            resultEntry = classProcessor.processClass(taskDTO.getCode(), template.getTestName(),
                    tronCoreJar, junitJar);
        } catch (CompilationException e) {
            resultEntry = e.getResult();
        } catch (Exception e) {
            log.error(e);
            return "Ошибка проверки " + e.getMessage() + ". Обратитесь к разработчикам";
        }
        String testResult = resultEntry.getKey() + '\n' + resultEntry.getValue();
        log.info("Cabinet test result is " + testResult);
        Task task = taskRepository.findOne(taskDTO.getId());
        task.setCode(taskDTO.getCode());
/*        if(testResult.length() > 750) {
            testResult = testResult.substring(0, 740);
        }*/
        task.setResult(testResult);
        taskRepository.save(task);
        return testResult;
    }

    @Override
    public ArrayList<TaskDTO> getTasksByUserGroup(Long taskTemplateId, Long selectedGroupId, Long selectedSprintId) {
        log.info("-== getTasksByUserGroup: " + taskTemplateId);
        ArrayList<TaskDTO> tasksList = new ArrayList<TaskDTO>();
        try {
            String userName = getSpringUserName();
            User user = userRepository.findWithGroupsByUserName(userName);
            if (user != null) {
//                for (GroupDTO group : user.getGroups()) {
//                    Long groupId = group.getId();
                List<Task> tasks = taskRepository.findByGroupIdAndSprintIdAndTaskId(selectedGroupId,
                        selectedSprintId, taskTemplateId);
//                    tasksList.addAll(TaskDtoMapper.domainsToDtos(tasks, getCourseRate(selectedGroupId, userName)));
                for (Task task : tasks) {
                    tasksList.add(TaskDtoMapper.domainToDto(task, getSprintRate(selectedGroupId, selectedSprintId, task.getUser().getLogin())));
                }
                removeTasksOfCurrentUser(tasksList, user.getLogin());
//                }
//        list.add(new TaskDTO(1L, "task1", "task1", "", "", "", "", "", ""));
//        list.add(new TaskDTO(1L, "task2", "task2", "", "", "", "", "", ""));
            }
            log.debug("Tasks for group " + tasksList);
            log.info("Tasks for group " + tasksList.size());
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return tasksList;
    }

    @Override
    public List<List<String>> getGroupInfo(Long selectedGroupId) throws Exception {
        try {
            List<Task> tasks = taskRepository.findByGroupId(selectedGroupId);
            Map<User, Map<Sprint, Integer>> groupInfo = new HashMap<User, Map<Sprint, Integer>>();
            for (Task task : tasks) {
                Map<Sprint, Integer> userSprints = groupInfo.get(task.getUser());
                if (userSprints == null) {
                    userSprints = new HashMap<Sprint, Integer>();
                    groupInfo.put(task.getUser(), userSprints);
                }
                Integer rate = userSprints.get(task.getSprint());
                if (rate == null) {
                    rate = 0;
                    userSprints.put(task.getSprint(), rate);
                }
                int index = task.getResult().indexOf("\n");
                String rateStr;
                if (index >= 0) {
                    rateStr = task.getResult().substring(0, index);
                    rate += Integer.parseInt(rateStr);
                }
                userSprints.put(task.getSprint(), rate);
            }

            for (Map.Entry<User, Map<Sprint, Integer>> users : groupInfo.entrySet()) {
                for (Map.Entry<Sprint, Integer> sprint : users.getValue().entrySet()) {
                    List<Task> userTasks = taskRepository.findByUserAndSprintAndGroup(users.getKey().getLogin(),
                            sprint.getKey().getId(), selectedGroupId);//sprint.getKey().getTasks().size();
                    sprint.setValue(sprint.getValue() / userTasks.size());
                }
            }

            List<List<String>> resultInfo = new ArrayList<List<String>>();
            for (Map.Entry<User, Map<Sprint, Integer>> sprints : groupInfo.entrySet()) {
                List<String> userSprints = new LinkedList<String>();
                String userName = sprints.getKey().getLogin();
                userSprints.add(userName);
                Long sprintId = sprints.getValue().entrySet().iterator().next().getKey().getId();
                // TODO avoid unnecessary DB query
                Long sum = 0L;
                for (Map.Entry<Sprint, Integer> sprint : sprints.getValue().entrySet()) {
                    sum += sprint.getValue();
                }
                double globalRate = getCourseRate(selectedGroupId, userName);//String.valueOf(sum / sprints.getValue().size());
                userSprints.add(String.valueOf((int)globalRate));


                for (Map.Entry<Sprint, Integer> sprint : sprints.getValue().entrySet()) {
                    userSprints.add(String.valueOf(sprint.getValue()));
                }
                resultInfo.add(userSprints);
            }

            return resultInfo;
        } catch (Exception e) {
            log.error(e);
            throw e;
        }
    }

    private void removeTasksOfCurrentUser(ArrayList<TaskDTO> list, String userName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(userName)) {
                list.remove(i);
            }
        }
    }

    @Override
    public double getSprintRate(Long groupId, Long sprintId, String userName) {
        log.info("-== getSprintRate: groupId = " + groupId + ", sprintId = " + sprintId + ", userName = " + userName);
        try {
            List<Task> tasks = taskRepository.findByUserAndSprintAndGroup(userName, sprintId, groupId);
            return calcTasksRate(tasks);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e);
        }
        return 0.0;
    }

    private double calcTasksRate(List<Task> tasks) {
        int doneCount = 0;
        int sumResult = 0;
        for (Task task : tasks) {
            String resultStr = "0";
            if (task.getResult() != null && !task.getResult().isEmpty()) {
                String[] lines = task.getResult().split("\n");
                if (lines.length > 0) {
                    resultStr = lines[0];
                } else {
                    resultStr = task.getResult();
                }
            }
            int result = 0;
            try {
                result = Integer.parseInt(resultStr);
            } catch (Exception e) {
                log.error(e);
            }
            sumResult += result;
            if (result >= 10) {
                doneCount++;
            }
        }
        if (tasks.size() * doneCount == 0) {
            return 0;
        } else {
//            return 100 / tasks.size() * doneCount;
            return sumResult / tasks.size();
        }
    }

    @Override
    public double getCourseRate(Long selectedGroupId, String userName) {
        Iterable<Sprint> sprintIterable = sprintRepository.findByUserAndGroup(userName, selectedGroupId);
        ArrayList<Task> allSprintsTasks = new ArrayList<Task>();
        for (Sprint sprint : sprintIterable) {
            List<Task> tasks = taskRepository.findByUserAndSprintAndGroup(userName, sprint.getId(), selectedGroupId);
            allSprintsTasks.addAll(tasks);
        }
        return calcTasksRate(allSprintsTasks);
    }

    @Override
    public String getSpringUserName() {
        Object authentication = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        org.springframework.security.core.userdetails.User springUser;
        if (authentication instanceof String) {
            throw new SecurityException("can't grant access to anonymous ");
        }
        springUser = (org.springframework.security.core.userdetails.User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return springUser.getUsername();
    }

    @Override
    public ArrayList<GroupDTO> getUserGroups() {
        String userName = getSpringUserName();
        List<Group> groups = groupRepository.findByUsersIn(userName);
        return GroupDtoMapper.domainToAdminDtos(groups);
    }

    @Transactional
    @Override
    public void setValidationResult(Long id, String statusStr, String result) {
        Task task = taskRepository.findOne(id);
        Status status = Status.valueOf(statusStr);
        task.setStatus(status);
        task.setResult(result);
        taskRepository.save(task);
    }

    @Override
    public List<TaskDTO> refreshTasks(List<Long> ids) {
        Iterable<Task> tasks = taskRepository.findAll(ids);
        List<TaskDTO> taskDTOs = TaskDtoMapper.domainsToDtos(tasks, 0.0);
        for (TaskDTO taskDTO : taskDTOs) {
            taskDTO.setClassName(null);
            taskDTO.setCode(null);
            taskDTO.setMaterial(null);
            taskDTO.setText(null);
            taskDTO.setUserName(null);
            taskDTO.setName(null);
        }

        return taskDTOs;
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

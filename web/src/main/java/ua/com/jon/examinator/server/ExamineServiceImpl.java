package ua.com.jon.examinator.server;


import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.jon.tron.exception.CompilationException;
import com.jon.tron.service.processor.ClassProcessor;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;
import ua.com.jon.auth.domain.SpringUser;
import ua.com.jon.common.domain.Sprint;
import ua.com.jon.common.domain.SprintType;
import ua.com.jon.common.domain.Status;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.domain.TaskHistory;
import ua.com.jon.common.domain.TaskTemplate;
import ua.com.jon.common.dto.mapper.SprintDtoMapper;
import ua.com.jon.common.repository.SprintRepository;
import ua.com.jon.common.repository.TaskHistoryRepository;
import ua.com.jon.common.repository.TaskRepository;
import ua.com.jon.common.repository.TaskTemplateRepository;
import ua.com.jon.examinator.client.ExamineService;
import ua.com.jon.examinator.shared.SprintDTO;
import ua.com.jon.examinator.shared.TaskDTO;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.SessionCookieConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/3/13
 */
@Service("examineService")
public class ExamineServiceImpl /*extends RemoteServiceServlet*/ implements ExamineService, ServletContextAware {
    private static final Logger log = Logger.getLogger(ExamineServiceImpl.class);
    public static final int TASK_PROCESSING_DELAY = 200;

    private long lastTime;

    @Resource
    private TaskRepository taskRepository;

    @Resource
    private SprintRepository sprintRepository;

    @Resource
    private TaskHistoryRepository taskHistoryRepository;

    @Autowired
    private ClassProcessor classProcessor;

    @Resource
    private TaskTemplateRepository templateRepository;

    @Autowired
    private HttpServletRequest request;

    private ServletContext servletContext;

    @Value("${core.jar}")
    private String coreJarName;

    @Override
    public String greet(String name) {
        System.out.println(name);
        return "great";
    }

    @Override
    public ArrayList<TaskDTO> getUserTasks() {
        log.info("-== getUserTasks() ==-");
        SpringUser springUser = (SpringUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String userName = springUser.getUsername();
        List<Task> tasks = taskRepository.findByUserName(userName);
        ArrayList<TaskDTO> taskDtos = new ArrayList<TaskDTO>();
        for (Task task : tasks) {
//            taskDtos.add(TaskDtoMapper.domainToDto(task));
        }
        log.info("-== " + taskDtos + " ==-");
        return taskDtos;
    }

    @Override
    public void taskStatusChanged(TaskDTO dto) {
        log.info("-== taskStatusChanged() ==-");
        Task task = taskRepository.findOne(dto.getId());
        Status newStatus = Status.valueOf(dto.getStatus());
        task.setStatus(newStatus);
        taskRepository.save(task);

        log.info("-== " + dto + " ==-");
    }

    @Override
    public ArrayList<SprintDTO> getSprints() {
        log.info("-== getSprints() ==-");
        Iterable<Sprint> sprintIterable = sprintRepository.findByType(SprintType.ANONYMOUS);
        ArrayList<SprintDTO> sprints = new ArrayList<SprintDTO>();
        for (Sprint sprint : sprintIterable) {
            List<Task> tasks = taskRepository.findBySprintName(sprint.getName());
            sprints.add(SprintDtoMapper.cabinetDtoToExamine(tasks, sprint, true));
        }
        log.info("-== " + sprints + " ==-");
        return sprints;
    }

    @Override
    public String postForTest(final TaskDTO taskDTO, final String userName) {
        if (System.currentTimeMillis() - lastTime < TASK_PROCESSING_DELAY) {
            return "Предыдущее задание еще не проверено, попробуйте позже";
        }
        if (userName != null && !userName.isEmpty()) {
            createSession(userName);
        }
        log.info("-== Cabinet post task for test: " + taskDTO.getCode());
        URL resource = this.getClass().getResource("/forbid.policy");
        System.out.println(resource.getPath());

        Map.Entry<String, String> resultEntry;
        final TaskTemplate template = templateRepository.findOne(taskDTO.getTaskTemplateId());
        try {
            resultEntry = classProcessor.processClass(taskDTO.getCode(), template.getTestName(),
                    servletContext.getRealPath("/WEB-INF") + "/lib/" + coreJarName);
        } catch (CompilationException e) {
            resultEntry = e.getResult();
        } catch (Exception e) {
            log.error(e);
            return "Ошибка проверки " + e.getMessage() + ". Обратитесь к разработчикам";
        }
        final String testResult = resultEntry.getKey() + '\n' + resultEntry.getValue();
        final Integer key = Integer.valueOf(resultEntry.getKey());
        log.info("Cabinet test result is " + testResult);

        new Thread(new Runnable() {
            @Override
            public void run() {
                Task task = taskRepository.findOne(taskDTO.getId());
                task.setCode(taskDTO.getCode());
                /*        if(testResult.length() > 750) {
                            testResult = testResult.substring(0, 740);
                        }*/
                try {
                    task.setResult(testResult);
                    taskRepository.save(task);
                    if (key >= 10) {
                        taskHistoryRepository.save(new TaskHistory(taskDTO.getCode(), template, userName, new Date(), testResult));
                    }
                } catch (Exception de) {
                    log.error(de);
                }
                lastTime = System.currentTimeMillis();

            }
        }).start();

        return testResult;
    }

    public void createSession(String username) {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") == null) {
            session.setAttribute("username", username);
        }
    }

    public String getUser() {
        HttpSession session = request.getSession();
        if (session.getAttribute("username") != null) {
            return (String)session.getAttribute("username");
        } else {
            return null;
        }
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        this.servletContext = servletContext;
    }
}

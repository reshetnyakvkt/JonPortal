package ua.com.jon.common.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ua.com.jon.admin.shared.TaskDTO;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.server.TasksServiceImpl;
import ua.com.jon.common.domain.Task;
import ua.com.jon.common.dto.GroupDTO;
import ua.com.jon.common.dto.TaskForTestDTO;
import ua.com.jon.common.service.RestService;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 8/8/13
 */
@Controller
//@RequestMapping("/tasks")
public class TasksRestController {
    private static Logger log = Logger.getLogger(TasksRestController.class);

    @Autowired
    private RestService restService;

    @Autowired
    private TasksService taskService;

    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public String getTask(@PathVariable Long id, ModelMap model) {
        log.info("Id = " + id);
        model.addAttribute("id", id);
        return "rest/task";
    }

    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public String getTask(ModelMap model) {
        log.info("tasks");
        return "rest/tasks";
    }

    @RequestMapping(value="/task", method = RequestMethod.POST)
    public String putTask(@RequestParam("id") Long id, @RequestParam("status") String status,
//                          @ModelAttribute TaskForTestDTO task,
                          @RequestParam("result") String result, ModelMap model) {
        log.info("put task, id=" + id + ", status=" + status + ", result=" + /*task.getTestResult()*/result);
/*
        if(task.getTestResult() == null) {
            task.setTestResult("null");
        }
*/
        taskService.setValidationResult(id, status, /*task.getTestResult()*/result);
        model.addAttribute("status", "true");
        return "rest/status";
    }

    @RequestMapping(value="/tasks/{id}/{status}", method = RequestMethod.POST)
    public String postTask(@PathVariable Long id, @PathVariable String status, ModelMap model) {
        log.info("tasks");
        model.addAttribute("status", "true");
        return "rest/status";
    }

    @RequestMapping(value = "/group/{groupName}", method = RequestMethod.GET)
    public String getTask(@PathVariable String groupName, ModelMap model) {
        log.info("Group = " + groupName);
        GroupDTO groupDTO = restService.getGroupDtoWithTasks(groupName);
        model.addAttribute("group", groupDTO);
        return "rest/tasks";
    }
}

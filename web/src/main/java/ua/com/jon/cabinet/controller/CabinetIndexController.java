package ua.com.jon.cabinet.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.cabinet.shared.GroupAndSprintsDTO;
import ua.com.jon.cabinet.shared.GroupDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: al1
 * Date: 4/5/13
 */

@Controller
public class CabinetIndexController {

    @Autowired
    private TasksService tasksService;

    /*
        @RequestMapping(value = "/cabinet/index.html", method = RequestMethod.GET)
        public String indexGWT(Model model) {
            return "cabinet/index";
        }
    */
    //
    @RequestMapping(value = "/cabinet/index.html", method = RequestMethod.GET)
    public String index(Model model) {
        return "cabinet/index1";
    }

    @RequestMapping(value = "/cabinet/tasks", method = RequestMethod.GET)
    public @ResponseBody List<GroupAndSprintsDTO> tasks() {
        List<GroupDTO> userGroups = tasksService.getUserGroups();
        List<GroupAndSprintsDTO> groups = new ArrayList<>(userGroups.size());
        for (GroupDTO userGroup : userGroups) {
            groups.add(new GroupAndSprintsDTO(userGroup, tasksService.getSprints(userGroup.getId())));
        }
        return groups;
/*
        return "{\n" +
                "  \"data\": [" +
                "{\"name\": \"Tiger Nixon\",\"mark\": \"4\",\"button\": \"-\"}," +
                "{\"name\": \"Tiger Nixon\",\"mark\": \"4\",\"button\": \"-\"}," +
                "{\"name\": \"Tiger Nixon\",\"mark\": \"4\",\"button\": \"-\"}" +
                "  ]\n" +
                "}";
*/
    }

    @RequestMapping(value = "/cabinet/sprintRate", method = RequestMethod.GET)
    public @ResponseBody long getSprintRate(@RequestParam("groupId") String groupIdParam, @RequestParam("sprintId") String sprintIdParam) {
        Long groupId = Long.parseLong(groupIdParam);
        Long templateId = Long.parseLong(sprintIdParam);
        return (long)tasksService.getSprintRate(groupId, templateId);
    }

    @RequestMapping(value = "/cabinet/courseRate", method = RequestMethod.GET)
    public @ResponseBody long getCourseRate(@RequestParam("groupId") String groupIdParam) {
        Long groupId = Long.parseLong(groupIdParam);
        return (long)tasksService.getCourseRate(groupId);
    }
}
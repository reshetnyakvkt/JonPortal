package ua.com.jon.quiz.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.jon.auth.domain.UserRole;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.common.domain.User;
import ua.com.jon.common.repository.SprintRepository;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by Reshetnyak Viktor on 21.01.2016.
 * Package quiz.controller
 */
@Controller
public class DashboardController {
    public static final Logger log = Logger.getLogger(DashboardController.class);

    @Resource
    private SprintRepository sprintRepository;

    @Autowired(required = true)
    private TasksService taskService;

    public DashboardController() {
    }

    @RequestMapping(value = "/quiz/dashboard", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String dashboard(HttpServletRequest request) {
        return "/quiz/dashboard";
    }

    @RequestMapping(value = "/quiz/quizPageTemplate", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String quizPageTemplate(HttpServletRequest request) {
        return "/quiz/quizPageTemplate";
    }

    @RequestMapping(value = "/quiz/authentication", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String authentication(HttpServletRequest request) {
        return "/quiz/authentication";
    }

    @RequestMapping(value = "/quiz/registerUser", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String registerUser(HttpServletRequest request) {
        return "/quiz/registerUser";
    }

    @RequestMapping(value = "/quiz/userData", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String userData(HttpServletRequest request) {
        return "/quiz/userData";
    }

    @RequestMapping(value = "/quiz/quizPage", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String quizPage(HttpServletRequest request) {
        return "/quiz/quizPage";
    }

    @RequestMapping(value = "/quiz/editQuiz", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String editQuiz(HttpServletRequest request) {
        return "/quiz/editQuiz";
    }

    @RequestMapping(value = "/quiz/index", method = {RequestMethod.GET, RequestMethod.HEAD})
    public String index(HttpServletRequest request) {
        return "/quiz/index";
    }


    @RequestMapping(value = "/getUser", method = {RequestMethod.GET, RequestMethod.HEAD})
    public @ResponseBody User getUser(@RequestParam String login, @RequestParam String password, HttpServletRequest request) {
        log.info("getUser(" + login + ", " + password + ")");
        User user = new User();
        //String userName = taskService.getSpringUserName();
        //User user = userRepository.findByUserName(userName);
        user.setLogin("viktor_reshetnyak");
        if (! user.getRoles().contains(UserRole.ROLE_ADMIN)){
            user.getRoles().add(UserRole.ROLE_ADMIN);
        }
        return user;
    }
}

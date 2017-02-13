package ua.com.jon.quiz.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ua.com.jon.cabinet.client.TasksService;
import ua.com.jon.quiz.domain.Quiz;
import ua.com.jon.quiz.domain.UserQuiz;
import ua.com.jon.quiz.service.QuizService;
import ua.com.jon.quiz.service.UserQuizService;

import java.util.List;

/**
 * Created by Олег on 15.01.2016.
 */
@RestController
public class QuizController {
    public static final Logger log = Logger.getLogger(QuizController.class);

    @Autowired
    private QuizService quizService;

    @Autowired
    private UserQuizService userQuizService;

    @Autowired
    private TasksService taskService;

    @RequestMapping(value = "/getQuiz/{id}", method = RequestMethod.GET)
    public String getQuiz(@PathVariable("id") long id) {
        String userName = taskService.getSpringUserName();
        String res = userQuizService.unParseUserQuizAnswers(quizService.read(id),
                userQuizService.findRunningByQuizIdAndLogin(id, userName), userName);
        return res;
    }

    @RequestMapping(value = "/getUserResults", method = RequestMethod.GET)
    public List<UserQuiz> getUserResults(@RequestParam Long userId) {
        String userName = taskService.getSpringUserName();
        log.info("getUserResults(): userName=" + userName);
        List<UserQuiz> resList = userQuizService.findByLogin(userName);
        log.info("getUserResults(): resList=[" + resList + "]");
        return resList;
    }

    @RequestMapping(value = "/submitUserQuiz", method = RequestMethod.GET)
    public Long createQuizResult(@RequestParam("UserQuizAnswersJson") String quizJson,
                                    @RequestParam("IsRunning") Boolean isRunning) {
        String userName = taskService.getSpringUserName();
        return userQuizService.saveUserQuizAnswers(userName, quizJson, isRunning);
    }

    @RequestMapping(value = "/getQuizzes", method = RequestMethod.GET)
    public @ResponseBody List<Quiz> getQuizzes() {
        return quizService.findAll();
    }

    @RequestMapping(value = "/goToQuiz/{id}", method = RequestMethod.GET)
    public String goToQuiz(@PathVariable("id") long id) {
        return "quizPageTemplate";
    }
}

package ua.com.jon.quiz.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import ua.com.jon.common.repository.*;
import ua.com.jon.quiz.domain.Quiz;
import ua.com.jon.quiz.service.QuizService;
import ua.com.jon.quiz.service.UserQuizService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Reshetnyak Viktor on 25.01.2016.
 * Package quiz.controller
 */
@Controller
public class ManageQuizzesController {
    public static final Logger log = Logger.getLogger(ManageQuizzesController.class);

    @Resource
    private QuizService quizService;

    @Resource
    private UserRepository userRepository;

    //@Autowired(required = true)
    //private SprintRepository sprintRepository;

    //@Autowired(required = true)
    //private TasksService taskService;

    @Autowired
    private UserQuizService userQuizService;


    public ManageQuizzesController() {
    }

    private List<Quiz> getQuizAll(){
        return quizService.findAll();
    }

    @RequestMapping(value = "/quiz/manageQuizzes", method = {RequestMethod.GET})
    public String manageQuizzes(Model model) {
        log.info("Start manageQuizzes(Model model)-->");
        return "/quiz/manageQuizzes";
    }

    @RequestMapping(value = "/quiz/editQuizzes", method = {RequestMethod.GET})
    public String editQuizzes(Model model) {
        log.info("Start editQuizzes(Model model)-->");
        return "/quiz/editQuizzes";
    }

    @RequestMapping(value = "/quiz/editQuestions", method = {RequestMethod.GET})
    public String editQuestion(Model model) {
        log.info("Start editQuestion(Model model)-->");
        return "/quiz/editQuestions";
    }

    @RequestMapping(value = "/quiz/j_quizzes", method = {RequestMethod.GET})
    public @ResponseBody
    List<Object> j_quizzes(Model model) {
        List<Object> res = new ArrayList<>();
        res.add(getQuizAll());
//        String userName = taskService.getSpringUserName();
//        User user = userRepository.findByUserName(userName);
//            res.add(sprintRepository.findAll());
        return res;
    }

    @RequestMapping(value = "/quiz/j_quiz_new", method = {RequestMethod.GET})
    public @ResponseBody
    List<Object> j_quiz_new(@RequestParam String text, @RequestParam Long limit, Model model){
        log.info("j_quiz_new(" + text + ", " + limit + ")");
        quizService.save(new Quiz(text, limit));
        List<Object> res = new ArrayList<>();
        res.add(getQuizAll());
        return res;
    }


    @RequestMapping(value = "/quiz/j_quiz_del", method = {RequestMethod.GET})
    public @ResponseBody
    List<Object> j_quiz_del(@RequestParam Long id, Model model) {
        List<Object> res = new ArrayList<>();
        Quiz quiz = null;
        if (id != null) {
            quiz = quizService.read(id);
        }
        if (quiz != null){
            res.add(quizService.delete(quiz));
        } else {
            res.add(true);
        }
        return res;
    }

    @RequestMapping(value = "/quiz/j_quiz_save", method = {RequestMethod.POST})
    public @ResponseBody
    List<Object> j_quiz_save(@RequestParam String quizJson) {
        Quiz quiz = userQuizService.parseQuiz(quizJson);
        List<Object> res = new ArrayList<>();
        res.add(quizService.save(quiz));
        return res;
    }
}

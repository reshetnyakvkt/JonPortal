package ua.com.jon.quiz.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import ua.com.jon.common.repository.UserRepository;
import ua.com.jon.quiz.domain.*;
import ua.com.jon.quiz.repository.AnswerRepository;
import ua.com.jon.quiz.repository.QuestionRepository;
import ua.com.jon.quiz.repository.QuizRepository;
import ua.com.jon.quiz.repository.UserQuizRepository;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

/**
 * Created by Reshetnyak Viktor on 10.02.2016.
 * Project JonGit
 */
@Service
public class UserQuizServiceImpl implements UserQuizService {
    public static final Logger log = Logger.getLogger(UserQuizServiceImpl.class);
    @Resource
    private UserRepository userRepository;

    @Resource
    private UserQuizRepository userQuizRepository;

    @Resource
    private QuizRepository quizRepository;

    @Resource
    private QuestionRepository questionRepository;

    @Resource
    private AnswerRepository answerRepository;

    public UserQuizServiceImpl() {
    }

    @Override
    public boolean save(UserQuiz userQuiz) {
        try {
            userQuizRepository.save(userQuiz);
        } catch(Exception ex){
            return false;
        }
        return true;
    }

    @Override
    public Long saveUserQuizAnswers(String userName, String json, Boolean isRunning) {
        UserQuizResult userQuizResult = parseUserQuizResult(json);
        log.info("saveUserQuizAnswers()\n\tjson: " + json);
        UserQuiz userQuiz = userQuizResult.getUserQuiz();
        if (userQuiz == null){
            return -1L;
        }
        compressAnsweredUserQuiz(userQuiz);
        userQuiz.setUser(userRepository.findByUserName(userName));
        userQuiz.setIsRunning(isRunning);
        userQuizRepository.save(userQuiz);
        return userQuiz.getId();

    }

    public void compressAnsweredUserQuiz(UserQuiz userQuiz){
        boolean mustNewIteration;
        userQuiz.setQuiz(quizRepository.findOne(userQuiz.getQuiz().getId()));
        do {
            mustNewIteration = false;
            for (UserQuizQuestion userQuizQuestion : userQuiz.getUserQuizQuestions()){
                if (userQuizQuestion.getAnswered()){
                    userQuizQuestion.setQuestion(questionRepository.findOne(userQuizQuestion.getQuestion().getId()));
                    for (UserQuizAnswer userQuizAnswer : userQuizQuestion.getUserQuizAnswers()){
                        userQuizAnswer.setUserQuizQuestion(userQuizQuestion);
                        if (!userQuizAnswer.getAnswered()){
                            userQuizQuestion.getUserQuizAnswers().remove(userQuizAnswer);
                            mustNewIteration = true;
                            break;
                        } else {
                            userQuizAnswer.setAnswer(answerRepository.findOne(userQuizAnswer.getAnswer().getId()));
                        }
                    }
                } else {
                    userQuiz.getUserQuizQuestions().remove(userQuizQuestion);
                    mustNewIteration = true;
                }
                if (mustNewIteration) break;
            }
        } while (mustNewIteration);
    }

    @Override
    public UserQuiz read(Long id) {
        return userQuizRepository.findOne(id);
    }

    @Override
    public List<UserQuiz> findByLogin(String login){
        return userQuizRepository.findByLogin(login);
    }

    @Override
    public List<UserQuiz> findByQuizIdAndLogin(Long quizId, String login){
        return userQuizRepository.findByQuizIdAndLogin(quizId, login);
    }

    @Override
    public UserQuiz findRunningByQuizIdAndLogin(Long quizId, String login) {
        List<UserQuiz> userQuizzes  = findByQuizIdAndLogin(quizId, login);
        if (userQuizzes != null && userQuizzes.size() > 0){
            for (UserQuiz userQuiz : userQuizzes){
                if (!checkEndUserQuiz(userQuiz)){
                    return userQuiz;
                }
            }
        }
        return null;
    }

    @Override
    public Quiz parseQuiz(String quizJson) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(quizJson, Quiz.class);
        } catch (IOException e) {
            log.error("Error parseQuiz(" + quizJson + ")");
            e.printStackTrace();
        }
        return null;
    }

    public UserQuizResult parseUserQuizResult(String json) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, UserQuizResult.class);
        } catch (IOException e) {
            log.error("Error parseUserQuizResult(" + json + ")");
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String unParseUserQuizAnswers(Quiz quiz, UserQuiz userQuiz, String userName) {
        ObjectMapper mapper = new ObjectMapper();
        String jsonString = null;
        try {
            if (userQuiz != null) {
                if (checkEndUserQuiz(userQuiz)) {
                    userQuiz = null;
                }
            }
            if (userQuiz == null) {
                userQuiz = new UserQuiz(userRepository.findByUserName(userName), quiz, 0);
            }
            fillUserQuizFromQuiz(userQuiz, quiz);

            jsonString = mapper.writeValueAsString(new UserQuizResult(quiz, userQuiz));
        } catch (JsonProcessingException e) {
            log.info("Error: " + e.getMessage());
            e.printStackTrace();
        }
        return jsonString;
    }

    public void fillUserQuizFromQuiz(UserQuiz toUserQuiz, Quiz fromQuizAnswers){
        if (fromQuizAnswers == null || toUserQuiz == null) return;
        try {
            for (Question question : fromQuizAnswers.getQuestions()) {
                UserQuizQuestion curUserQuizQuestion = null;
                for (UserQuizQuestion userQuizQuestion : toUserQuiz.getUserQuizQuestions()){
                    if (userQuizQuestion.getQuestion().getId() == question.getId()){
                        curUserQuizQuestion = userQuizQuestion;
                        break;
                    }
                }
                if (curUserQuizQuestion == null){
                    curUserQuizQuestion = new UserQuizQuestion(toUserQuiz, question, false);
                    toUserQuiz.getUserQuizQuestions().add(curUserQuizQuestion);
                }
                for (Answer answer : question.getAnswerList()) {
                    UserQuizAnswer curUserQuizAnswer = null;
                    answer.setIsCorrect(false);
                    for (UserQuizAnswer userQuizAnswer: curUserQuizQuestion.getUserQuizAnswers()){
                        if (userQuizAnswer.getAnswer() != null && userQuizAnswer.getId() == answer.getId()){
                            curUserQuizAnswer = userQuizAnswer;
                            curUserQuizAnswer.setAnswered(true);
                            break;
                        }
                    }
                    if (curUserQuizAnswer == null){
                        curUserQuizAnswer = new UserQuizAnswer(curUserQuizQuestion, answer);
                    }
                    curUserQuizQuestion.getUserQuizAnswers().add(curUserQuizAnswer);
                }
                question.setAnswerList(null);
            }
        } catch(Exception ex){
            log.error("Error importQuizAnswers(" + fromQuizAnswers + ", " + toUserQuiz + ")");
            ex.printStackTrace();
        }
    }

    public boolean checkEndUserQuiz(UserQuiz userQuiz){
        return checkEndUserQuiz(userQuiz, true);
    }

    public boolean checkEndUserQuiz(UserQuiz userQuiz, boolean checkCreateDate){
        if (userQuiz == null) return true;
        if (userQuiz.getIsRunning() != null && ! userQuiz.getIsRunning()){
            return true;
        }
        if (checkCreateDate && ! userQuiz.getMustComplete()){
            return false;
        }

        for (Question question : userQuiz.getQuiz().getQuestions()){
//            userQuiz.getUserQuizAnswers().
        }
        userQuiz.setIsRunning(false);
        save(userQuiz);
        return true;
    }
}

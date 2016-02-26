package ua.com.jon.quiz.service;

import ua.com.jon.common.domain.User;
import ua.com.jon.quiz.domain.Quiz;
import ua.com.jon.quiz.domain.UserQuiz;
import java.util.List;

/**
 * Created by Reshetnyak Viktor on 10.02.2016.
 * Project JonGit
 */
public interface UserQuizService {
    boolean save(UserQuiz userQuiz);
    Long saveUserQuizAnswers(String userName, String quizJson, Boolean isRunning);
    UserQuiz read(Long id);
    List<UserQuiz> findByLogin(String login);
    List<UserQuiz> findByQuizIdAndLogin(Long quizId, String login);
    UserQuiz findRunningByQuizIdAndLogin(Long quizId, String login);
    Quiz parseQuiz(String quizJson);
    String unParseUserQuizAnswers(Quiz _quiz, UserQuiz _userQuiz, String userName);
}

package ua.com.jon.quiz.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import ua.com.jon.quiz.domain.Quiz;
import ua.com.jon.quiz.domain.UserQuiz;

/**
 * Created by Reshetnyak Viktor on 18.02.2016.
 * Project JonGit
 */
public class UserQuizResult {
    @JsonProperty("quiz")
    private Quiz quiz = null;
    @JsonProperty("userQuiz")
    private UserQuiz userQuiz = null;

    public UserQuizResult() {
    }

    public UserQuizResult(Quiz quiz, UserQuiz userQuiz){
        this.quiz = quiz;
        this.userQuiz = userQuiz;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public UserQuiz getUserQuiz() {
        return userQuiz;
    }

    public void setUserQuiz(UserQuiz userQuiz) {
        this.userQuiz = userQuiz;
    }

    @Override
    public String toString() {
        return "UserQuizResult{" +
                "quiz=" + quiz +
                ", userQuiz=" + userQuiz +
                '}';
    }
}

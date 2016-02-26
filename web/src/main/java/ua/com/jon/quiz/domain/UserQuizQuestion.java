package ua.com.jon.quiz.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Reshetnyak Viktor on 17.02.2016.
 * Project JonGit
 */
@Entity
@Table(name = "USER_QUIZ_QUESTION")
public class UserQuizQuestion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userQuizQuestion")
    @JsonProperty("answers")
    private Set<UserQuizAnswer> userQuizAnswers = new HashSet<>();

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "QUESTION_ID")
    @JsonIgnoreProperties({"name", "quiz", "answerList", "answersCount"})
    private Question question;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_QUIZ_ID")
    @JsonIgnore
    private UserQuiz userQuiz;

    @Column(name = "ANSWERED")
    private Boolean answered;

    public UserQuizQuestion() {
    }

    public UserQuizQuestion(UserQuiz userQuiz, Question question, Boolean answered) {
        this.userQuiz = userQuiz;
        this.question = question;
        this.answered = answered;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<UserQuizAnswer> getUserQuizAnswers() {
        return userQuizAnswers;
    }

    public void setUserQuizAnswers(Set<UserQuizAnswer> userQuizAnswers) {
        this.userQuizAnswers = userQuizAnswers;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @JsonGetter("name")
    public String getQuestionName() {
        if (question != null){
            return question.getName();
        } else {
            return null;
        }
    }
    @JsonSetter("name")
    public void setName(String name) {
    }


    @JsonGetter("questionType")
    public Question.Type getQuestionType() {
        if (question != null){
            return question.getQuestionType();
        } else {
            return null;
        }
    }

    @JsonSetter("questionType")
    public void setQuestionType(Question.Type questionType) {
    }

    public UserQuiz getUserQuiz() {
        return userQuiz;
    }

    public void setUserQuiz(UserQuiz userQuiz) {
        this.userQuiz = userQuiz;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    @Override
    public String toString() {
        return "UserQuizQuestion{" +
                "id=" + id +
                ", answered=" + answered +
                '}';
    }
}

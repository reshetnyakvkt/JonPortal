package ua.com.jon.quiz.domain;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Reshetnyak Viktor on 19.01.2016.
 * Package quiz.domain
 */
@Entity
@Table(name = "USER_QUIZ_ANSWER")
public class UserQuizAnswer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_QUIZ_QUESTION_ID")
    @JsonIgnore
    private UserQuizQuestion userQuizQuestion;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ANSWER_ID")
    @JsonIgnore
    private Answer answer;

    @Column(name = "CREATE_DATE", length = 15)
    private Date createDate;

    public UserQuizAnswer() {
        this.createDate = Calendar.getInstance().getTime();
    }

    public UserQuizAnswer(UserQuizQuestion userQuizQuestion, Answer answer) {
        this();
        this.userQuizQuestion = userQuizQuestion;
        this.answer = answer;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @JsonGetter("name")
    public String getAnswerName() {
        if (answer != null){
            return answer.getName();
        } else {
            return null;
        }
    }

    @JsonSetter("name")
    public void setAnswerName(String name) {
    }

    @Transient
    private Boolean answered = false;

    @JsonGetter("answered")
    public Boolean getAnswered() {
        return answered;
    }

    @JsonSetter("answered")
    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public UserQuizQuestion getUserQuizQuestion() {
        return userQuizQuestion;
    }

    public void setUserQuizQuestion(UserQuizQuestion userQuizQuestion) {
        this.userQuizQuestion = userQuizQuestion;
    }
}

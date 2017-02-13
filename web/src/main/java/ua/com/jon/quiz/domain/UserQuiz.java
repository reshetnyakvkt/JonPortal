package ua.com.jon.quiz.domain;

import com.fasterxml.jackson.annotation.*;
import ua.com.jon.common.domain.User;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Олег on 16.01.2016.
 */
@Entity
@Table(name = "USERS_QUIZZES")
public class UserQuiz {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "QUIZ_ID")
    @JsonIgnoreProperties({"questions", "createDate"})
    private Quiz quiz;

    @Column(name = "CREATE_DATE")
    private Date createDate;

    @Column(name = "IS_RUNNING")
    private Boolean isRunning;

    @Column(name = "SCORE")
    private Integer score;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "userQuiz")
    @JsonProperty("questions")
    private Set<UserQuizQuestion> userQuizQuestions = new HashSet<>();

    public UserQuiz() {
        this.createDate = Calendar.getInstance().getTime();
    }

    public UserQuiz(User user, Quiz quiz, Integer score) {
        this();
        this.user = user;
        this.quiz = quiz;
        this.score = score;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setQuiz(Quiz quiz) {
        this.quiz = quiz;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getIsRunning() {
        return isRunning;
    }

    public Set<UserQuizQuestion> getUserQuizQuestions() {
        return userQuizQuestions;
    }

    public void setUserQuizQuestions(Set<UserQuizQuestion> userQuizQuestions) {
        this.userQuizQuestions = userQuizQuestions;
    }

    public void setIsRunning(Boolean isRunning) {
        this.isRunning = isRunning;
    }

    @Override
    public String toString() {
        String res = "UserQuiz{" +
                "id=" + id;
        if (user != null){
            res += ", user{login=" + user.getLogin() + "}";
        } else {
            res += ", user=null";
        }
        res += ", createDate=" + createDate +
                ", isRunning=" + isRunning +
                ", score=" + score + '\'' +
                '}';
        return res;
    }

    @JsonGetter("mustComplete")
    public Boolean getMustComplete(){
        if (quiz == null) return false;
        return (getTimeLeft() / 1000 > (quiz.getTimeLimit() - 3)); // 3 seconds
    }

    @JsonSetter("mustComplete")
    public void setMustComplete(Boolean mustComplete){}

    @JsonIgnore
    public Long getTimeLeft(){
        Date now = Calendar.getInstance().getTime();
        return now.getTime() - createDate.getTime();
    }

    @JsonIgnore
    public UserQuizQuestion findUserQuizQuestion(Question question){
        for (UserQuizQuestion userQuizQuestion : userQuizQuestions){
            if (question.equals(userQuizQuestion.getQuestion())){
                return userQuizQuestion;
            }
        }
        return null;
    }
}

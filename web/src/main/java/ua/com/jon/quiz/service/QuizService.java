package ua.com.jon.quiz.service;

import ua.com.jon.quiz.domain.*;
import java.util.List;

/**
 * Created by Олег on 15.01.2016.
 */
public interface QuizService {
    Quiz read(Long id);
    boolean save(Quiz quiz);
    void update(Quiz quiz);
    boolean delete(Quiz quiz);
    List<Quiz> findAll();
}

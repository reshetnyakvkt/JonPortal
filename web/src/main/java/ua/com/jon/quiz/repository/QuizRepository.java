package ua.com.jon.quiz.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.jon.quiz.domain.Quiz;

/**
 * Created by Reshetnyak Viktor on 06.02.2016.
 */
public interface QuizRepository extends CrudRepository<Quiz, Long> {

}

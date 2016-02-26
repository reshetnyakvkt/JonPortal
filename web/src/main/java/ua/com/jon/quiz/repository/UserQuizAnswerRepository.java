package ua.com.jon.quiz.repository;

import org.springframework.data.repository.CrudRepository;
import ua.com.jon.quiz.domain.UserQuizAnswer;

/**
 * Created by Reshetnyak Viktor on 10.02.2016.
 * Project JonGit
 */
public interface UserQuizAnswerRepository extends CrudRepository<UserQuizAnswer, Long> {
}

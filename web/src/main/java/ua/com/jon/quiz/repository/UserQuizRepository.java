package ua.com.jon.quiz.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import ua.com.jon.quiz.domain.UserQuiz;

import java.util.List;

/**
 * Created by Reshetnyak Viktor on 10.02.2016.
 * Project JonGit
 */
public interface UserQuizRepository extends CrudRepository<UserQuiz, Long> {
    @Query("select uq from UserQuiz uq join uq.user u where u.login = :login")
    List<UserQuiz> findByLogin(@Param("login") String login);

    @Query("select uq from UserQuiz uq join uq.user u where uq.quiz.id = :quizId and u.login = :login")
    List<UserQuiz> findByQuizIdAndLogin(@Param("quizId") Long quizId, @Param("login") String login);
}

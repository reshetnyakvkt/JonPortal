package ua.com.jon.quiz.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import ua.com.jon.quiz.domain.Answer;
import ua.com.jon.quiz.domain.Question;
import ua.com.jon.quiz.repository.*;
import ua.com.jon.quiz.domain.Quiz;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Created by Reshetnyak Viktor on 06.02.2016.
 */
@Service
public class QuizServiceImpl implements QuizService {
    @Resource
    private QuizRepository quizRepository;

    @Resource
    private QuestionRepository questionRepository;

    @Resource
    private AnswerRepository answerRepository;

    public QuizServiceImpl() {
    }

    public Quiz read(Long id) {
        return quizRepository.findOne(id);
    }

    public void update(Quiz quiz) {
        quizRepository.save(quiz);
    }

    public boolean delete(Quiz quiz) {
        try {
            quizRepository.delete(quiz);
        } catch(Exception ex){
            return false;
        }
        return true;
    }

    public List<Quiz> findAll() {
        ArrayList<Quiz> res = new ArrayList<>();
        try {
//            Iterable<Question> questions = questionRepository.findAll();
//            for (Question question : questions){
//
//                //
//                ObjectMapper mapper = new ObjectMapper();
//                String json = null;
//                json = mapper.writeValueAsString(question);
//                //
//                System.out.println(question + ", JSON: " + json);
//            }
//            //
            Iterable<Quiz> quizzes = quizRepository.findAll();
            for (Quiz quiz : quizzes){
                res.add(quiz);
            }
            return res;
        } catch(Exception ex){
            System.out.println("QuizServiceImpl.findAll() Error: ");
            ex.printStackTrace();
        }
        return null;
}

    @Override
    public boolean save(Quiz quiz) {
        System.out.println("QuizServiceImpl.save(" + quiz + ")");
        try {
            Quiz oldQuiz = null;
            if (quiz.getId() != null){
                oldQuiz = read(quiz.getId());
            }
            for (Question question : quiz.getQuestions()){
                question.setQuiz(quiz);
                for (Answer answer : question.getAnswerList()){
                    answer.setQuestion(question);
                }
            }
            update(quiz);
//            if (oldQuiz != null) {
//                saveQuestions(quiz, oldQuiz.getQuestions());
//            } else {
//                saveQuestions(quiz, null);
//            }
            return true;
        } catch(Exception ex){
            return false;
        }
    }

    public void saveQuestions(Quiz quiz, Set<Question> oldQuestions){

        for (Question question : quiz.getQuestions()){
            question.setQuiz(quiz);
            questionRepository.save(question);
            Question oldQuestion = null;
            if (oldQuestions != null){
                for (Question curQuestion: oldQuestions){
                    if (question.getId() == oldQuestion.getId()){
                        oldQuestion = curQuestion;
                        break;
                    }
                }
            }

            if (oldQuestion != null){
                saveAnswers(question, oldQuestion.getAnswerList());
            } else {
                saveAnswers(question, null);
            }
        }
    }

    public void saveAnswers(Question question, Set<Answer> oldAnswers){
//        for (Answer answer : question.getAnswerList()){
//            if (oldAnswers != null && oldAnswers.contains(answer)){
//                answerDao.update(answer);
//            } else {
//                answer.setQuestion(question);
//                answerDao.create(answer);
//            }
//        }
    }

}

package com.app.Quiz_Application.question.service;

import com.app.Quiz_Application.question.model.Question;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuizService {
    private Map<Long, Question> questions = new HashMap<>();

    public List<Question> getAllQuestions(){
        return questions.values().stream().toList();
    }

    public void addQuestion(Question question){
        questions.put(question.getId(),question);
    }

    public void updateQuestion(Long id,Question updated){
        Question existing = questions.get(id);
        existing.setId(updated.getId());
        existing.setOptions(updated.getOptions());
        existing.setQuestionText(updated.getQuestionText());
        existing.setCorrectAns(updated.getCorrectAns());
    }

    public void deleteQuestion(Long id){
        questions.remove(id);
    }
}
package com.app.Quiz_Application.question.service;

import com.app.Quiz_Application.question.model.Question;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class QuizService {
    private Map<UUID, Question> questions = new HashMap<>();

    public List<Question> getAllQuestions(){
        return questions.values().stream().toList();
    }

    public void addQuestion(Question question){
        questions.put(question.getId(),question);
    }

    public void updateQuestion(UUID id,Question updated){
        Question existing = questions.get(id);
        existing.setOptions(updated.getOptions());
        existing.setQuestionText(updated.getQuestionText());
        existing.setCorrectAns(updated.getCorrectAns());
    }

    public void deleteQuestion(Long id){
        questions.remove(id);
    }
}
package com.app.Quiz_Application.question.controller;

import com.app.Quiz_Application.question.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.Quiz_Application.question.service.QuizService;

import java.util.List;


@Controller
public class QuestionController {
    private final QuizService quizService;
    private static final Logger log = LoggerFactory.getLogger(QuestionController.class);

    public QuestionController(QuizService quizService){
        this.quizService = quizService;
    }

    @PostMapping("/addQuestion")
    public String addQuestion(
            @RequestParam String question,
            @RequestParam List<String> options,
            @RequestParam String correctAns
    ){
        Question questionObj = Question.builder()
                .questionText(question)
                .options(options)
                .correctAns(correctAns)
                .build();

        quizService.addQuestion(questionObj);
        log.info("added question successfully");
        return "admin";

    }



}
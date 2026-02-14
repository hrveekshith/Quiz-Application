package com.app.Quiz_Application.question.controller;

import com.app.Quiz_Application.question.model.Question;
import com.app.Quiz_Application.question.model.QuizSubmission;
import com.app.Quiz_Application.question.model.SubmitedAnswer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.Quiz_Application.question.service.QuizService;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;


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

    @GetMapping("/quizTaker")
    public String quizTaker(Model model){
        List<Question> questions = quizService.getAllQuestions();
        model.addAttribute("Questions",questions);
        return "quizTaker";
    }

    @PostMapping("/processAns")
    public String processAns(Model model,
        @ModelAttribute QuizSubmission submission){
        List<SubmitedAnswer> answers = submission.getAnswerList();
        int total = answers.size();
        int score = 0;
        Map<UUID,Question> questionsMap = quizService.getAllQuestions().stream().collect(Collectors.toMap(Question::getId, q -> q));
        for(SubmitedAnswer ans : answers){
            Question givenQuestion = questionsMap.get(ans.getId());
            if(givenQuestion != null && givenQuestion.getCorrectAns().equals(ans.getSelectedAns())){
                score++;
            }
        }
        int res = (score * 100)/ total;
        model.addAttribute("res",res);
        return "score";
    }






}
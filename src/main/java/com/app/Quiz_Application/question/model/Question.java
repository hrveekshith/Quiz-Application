package com.app.Quiz_Application.question.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Question {
    private Long id;
    private String questionText;
    private List<String> options;
    private String correctAns;
}
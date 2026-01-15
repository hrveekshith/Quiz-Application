package com.app.Quiz_Application.question.model;

import lombok.*;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Question {

    @Builder.Default
    private final UUID id = UUID.randomUUID();
    private String questionText;
    private List<String> options;
    private String correctAns;
}
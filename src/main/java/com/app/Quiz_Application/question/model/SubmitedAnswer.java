package com.app.Quiz_Application.question.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SubmitedAnswer {
    private UUID id;
    private String SelectedAns;
}
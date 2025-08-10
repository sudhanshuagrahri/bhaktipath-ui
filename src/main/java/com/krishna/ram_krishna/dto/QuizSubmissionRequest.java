package com.krishna.ram_krishna.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizSubmissionRequest {
    private String uniqueId;
    private Long quizId;
    private List<String> answers;
}
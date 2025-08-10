package com.krishna.ram_krishna.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizResultResponse {
    private Integer correctAnswers;
    private Integer totalQuestions;
    private Double percentage;
    private BigDecimal rewardEarned;
    private String message;
}

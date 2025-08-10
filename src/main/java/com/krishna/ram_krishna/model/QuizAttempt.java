package com.krishna.ram_krishna.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuizAttempt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userUniqueId;
    private Long userId;
    private Long quizId;
    private String quizTitle;
    
    @ElementCollection
    @CollectionTable(name = "quiz_user_answers")
    private List<String> userAnswers;
    
    private Integer correctCount;
    private Integer totalQuestions;
    private BigDecimal rewardEarned;
    private LocalDateTime attemptedAt = LocalDateTime.now();
}
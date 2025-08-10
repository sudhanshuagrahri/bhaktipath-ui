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
public class Quiz {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String description;
    
    @ElementCollection
    @CollectionTable(name = "quiz_questions")
    private List<String> questions;
    
    @ElementCollection
    @CollectionTable(name = "quiz_answers")
    private List<String> correctAnswers;
    
    private BigDecimal rewardPerCorrect;
}
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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(unique = true)
    private String mobileNumber;
    private String name;
    private String uniqueId;
    private BigDecimal rewards = BigDecimal.ZERO;
    private LocalDateTime createdAt = LocalDateTime.now();
}
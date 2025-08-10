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
public class ProgramRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userUniqueId;
    private Long userId;
    private String userName;
    private Long programId;
    private String programName;
    private String details;
    private LocalDateTime registeredAt = LocalDateTime.now();
}
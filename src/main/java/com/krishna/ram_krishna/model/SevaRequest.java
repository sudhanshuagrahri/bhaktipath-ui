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
public class SevaRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String userUniqueId;
    private Long userId;
    private String userName;
    private String details;
    private Long sevaId;
    private String sevaName;
    private BigDecimal money;
    private LocalDateTime createdAt = LocalDateTime.now();
}
package com.krishna.ram_krishna.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SevaRequest {
    private String uniqueId;
    private String name;
    private String details;
    private Long sevaId;
    private String sevaName;
    private BigDecimal money;
}

package com.krishna.ram_krishna.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private String uniqueId;
    private String name;
    private String message;
    private BigDecimal rewards;
}

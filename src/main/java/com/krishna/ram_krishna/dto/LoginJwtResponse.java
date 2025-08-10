package com.krishna.ram_krishna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginJwtResponse {
    private String uniqueId;
    private String name;
    private String message;
    private java.math.BigDecimal rewards;
    private String token;
}

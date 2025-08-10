package com.krishna.ram_krishna.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceCalculationResponse {
    private BigDecimal totalCartPrice;
    private BigDecimal totalSevaMoney;
    private BigDecimal grandTotal;
    private String message;
}

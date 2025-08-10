package com.krishna.ram_krishna.dto;

import java.math.BigDecimal;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {
    private List<CartItemDto> items;
    private BigDecimal totalPrice;
    private Integer totalItems;
}

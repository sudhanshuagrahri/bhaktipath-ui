package com.krishna.ram_krishna.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddToCartRequest {
    private String uniqueId;
    private Long productId;
    private Integer quantity;
}

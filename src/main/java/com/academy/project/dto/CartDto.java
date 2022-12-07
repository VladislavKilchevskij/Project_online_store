package com.academy.project.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDto {
    private Integer amount;
    private BigDecimal price;
    private Integer orderId;
    private Integer productId;
}

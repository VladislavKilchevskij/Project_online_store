package com.academy.project.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartProductDto {
    private Integer id;
    private Integer amount;
    private BigDecimal price;
    private ProductDto product;
}

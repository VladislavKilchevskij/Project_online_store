package com.academy.project.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Data
public class OrderDetailsDto {
    private Integer id;
    private String orderStatus;
    private BigDecimal totalPrice;
    private Date orderDate;
    private Date deliveryDate;
    private List<CartProductDto> carts;
    private Integer addressId;
}

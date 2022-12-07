package com.academy.project.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.sql.Date;

@Data
public class OrderDetailsSimpleDto {
    private Integer id;
    private String orderStatus;
    private BigDecimal totalPrice;
    private Date orderDate;
    private Date deliveryDate;
    private AddressDto address;
}

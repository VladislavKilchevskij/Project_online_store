package com.academy.project.service;

import com.academy.project.dto.OrderDetailsDto;
import com.academy.project.dto.OrderDetailsSimpleDto;
import com.academy.project.model.entity.OrderDetails;

import java.math.BigDecimal;
import java.util.List;

public interface OrderDetailsService {

    void createOrder(Integer userId);

    OrderDetails getByOrderId(Integer id);

    OrderDetails getInCartOrderByUserId(Integer userId);

    OrderDetailsDto getInCartOrderDetailsDtoByUserId(Integer userId);

    List<OrderDetailsSimpleDto> getAllOrdersByUserId(Integer userId);

    void updateOrderTotalPrice(OrderDetails orderDetails, BigDecimal price);

    void updateOrderStatus(OrderDetailsDto orderDetailsDto);
}

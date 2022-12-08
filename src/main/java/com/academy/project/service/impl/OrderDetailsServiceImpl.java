package com.academy.project.service.impl;

import com.academy.project.dto.OrderDetailsDto;
import com.academy.project.dto.OrderDetailsSimpleDto;
import com.academy.project.mapper.OrderDetailsMapper;
import com.academy.project.mapper.OrderDetailsSimpleListMapper;
import com.academy.project.model.entity.OrderDetails;
import com.academy.project.model.repository.OrderDetailsRepository;
import com.academy.project.model.repository.UserRepository;
import com.academy.project.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.Named;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderDetailsServiceImpl implements OrderDetailsService {
    private static final String STATUS_CART = "IN_CART";
    private static final String STATUS_DELIVERING = "DELIVERING";
    private static final String START_PRICE = "0.00";
    private static final int DAY_AMOUNT = 3;

    private final OrderDetailsRepository orderRepository;
    private final UserRepository userRepository;
    private final OrderDetailsMapper orderDetailsMapper;
    private final OrderDetailsSimpleListMapper orderDetailsSimpleListMapper;

    @Override
    public void createOrder(Integer userId) {
        var order = new OrderDetails();
        order.setOrderStatus(STATUS_CART);
        order.setTotalPrice(new BigDecimal(START_PRICE));
        order.setUser(userRepository.getReferenceById(userId));
        orderRepository.save(order);
    }

    @Override
    @Named("getOrderById")
    public OrderDetails getByOrderId(Integer id) {
        return orderRepository.getReferenceById(id);
    }

    @Override
    public OrderDetails getInCartOrderByUserId(Integer userId) {
        var user = userRepository.getReferenceById(userId);
        var order = orderRepository.getByUserAndOrderStatus(user, STATUS_CART);
        if (order == null) {
            createOrder(userId);
            return getInCartOrderByUserId(userId);
        }
        return order;
    }

    @Override
    public OrderDetailsDto getInCartOrderDetailsDtoByUserId(Integer userId) {
        return orderDetailsMapper.toDto(getInCartOrderByUserId(userId));
    }

    @Override
    public List<OrderDetailsSimpleDto> getAllOrdersByUserId(Integer userId) {
        var user = userRepository.getReferenceById(userId);
        var orders = orderRepository.getAllByUserAndOrderStatusIsNot(user, STATUS_CART);
        return orderDetailsSimpleListMapper.toDtoList(orders);
    }
    @Override
    public void updateOrderTotalPrice(OrderDetails orderDetails, BigDecimal price){
        orderDetails.setTotalPrice(orderDetails.getTotalPrice().add(price));
        orderRepository.updateTotalPrice(orderDetails);
    }

    @Override
    public void updateOrderStatus(OrderDetailsDto orderDetailsDto) {
        var orderDetails = orderDetailsMapper.toEntity(orderDetailsDto);
        orderDetails.setOrderStatus(STATUS_DELIVERING);
        orderDetails.setOrderDate(new Date());
        var cal = Calendar.getInstance();
        cal.add(Calendar.DATE, DAY_AMOUNT);
        var deliveryDate = cal.getTime();
        orderDetails.setDeliveryDate(deliveryDate);
        orderRepository.updateOrderStatus(orderDetails);
    }
}

package com.academy.project.service.impl;

import com.academy.project.dto.CartDto;
import com.academy.project.dto.CartProductDto;
import com.academy.project.mapper.CartMapper;
import com.academy.project.mapper.CartProductListMapper;
import com.academy.project.model.entity.Cart;
import com.academy.project.model.repository.CartRepository;
import com.academy.project.service.CartService;
import com.academy.project.service.OrderDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final OrderDetailsService orderService;
    private final CartRepository cartRepository;
    private final CartProductListMapper cartProductListMapper;
    private final CartMapper cartMapper;

    @Override
    public void create(CartDto cartDto) {
        cartRepository.save(cartMapper.toEntity(cartDto));
    }

    @Override
    public List<CartProductDto> getAllByUserId(Integer userId) {
        var orderDetails = orderService.getInCartOrderByUserId(userId);
        var cartList = orderDetails.getCarts();
        return cartProductListMapper.toDtoList(cartList);
    }

    @Override
    public Cart getById(Integer id) {
        return cartRepository.getReferenceById(id);
    }

    @Override
    public void addProductToCart(Integer userId, Integer productId, Integer amount, BigDecimal price) {
        var order = orderService.getInCartOrderByUserId(userId);
        var cartDto = new CartDto();
        cartDto.setProductId(productId);
        cartDto.setOrderId(order.getId());
        cartDto.setAmount(amount);
        cartDto.setPrice(price.multiply(new BigDecimal(amount)));
        create(cartDto);
        orderService.updateOrderTotalPrice(order, cartDto.getPrice());
    }
}
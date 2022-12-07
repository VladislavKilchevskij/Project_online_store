package com.academy.project.service;

import com.academy.project.dto.CartDto;
import com.academy.project.dto.CartProductDto;
import com.academy.project.model.entity.Cart;

import java.math.BigDecimal;
import java.util.List;

public interface CartService {

    void create(CartDto cartDto);

    List<CartProductDto> getAllByUserId(Integer userId);

    Cart getById(Integer id);
    void addProductToCart(Integer userId, Integer productId, Integer amount, BigDecimal price);
}

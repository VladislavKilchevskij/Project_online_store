package com.academy.project.mapper;

import com.academy.project.dto.CartDto;
import com.academy.project.mapper.defaultMappers.DefaultMapper;
import com.academy.project.model.entity.Cart;
import com.academy.project.service.ProductService;
import com.academy.project.service.impl.OrderDetailsServiceImpl;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;


@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {OrderDetailsServiceImpl.class, ProductService.class})
public interface CartMapper extends DefaultMapper<CartDto, Cart> {

    @Mapping(source = "orderId", target = "orderDetails", qualifiedByName = "getOrderById")
    @Mapping(source = "productId", target = "product")
    Cart toEntity(CartDto cartDto);
}

package com.academy.project.mapper;

import com.academy.project.dto.CartProductDto;
import com.academy.project.mapper.defaultMappers.DefaultListMapper;
import com.academy.project.model.entity.Cart;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = CartProductMapper.class)
public interface CartProductListMapper extends DefaultListMapper<CartProductDto, Cart> {
}

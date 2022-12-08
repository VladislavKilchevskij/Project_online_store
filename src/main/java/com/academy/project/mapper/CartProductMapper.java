package com.academy.project.mapper;

import com.academy.project.dto.CartProductDto;
import com.academy.project.mapper.defaultmappers.DefaultMapper;
import com.academy.project.model.entity.Cart;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {ProductMapper.class})
public interface CartProductMapper extends DefaultMapper<CartProductDto, Cart> {
}

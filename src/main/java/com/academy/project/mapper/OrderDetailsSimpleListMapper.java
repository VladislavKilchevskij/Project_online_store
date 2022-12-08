package com.academy.project.mapper;

import com.academy.project.dto.OrderDetailsSimpleDto;
import com.academy.project.mapper.defaultmappers.DefaultListMapper;
import com.academy.project.model.entity.OrderDetails;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = OrderDetailsSimpleMapper.class)
public interface OrderDetailsSimpleListMapper extends DefaultListMapper<OrderDetailsSimpleDto, OrderDetails> {
}

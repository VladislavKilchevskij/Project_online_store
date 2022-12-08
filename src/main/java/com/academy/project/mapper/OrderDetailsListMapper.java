package com.academy.project.mapper;

import com.academy.project.dto.OrderDetailsDto;
import com.academy.project.mapper.defaultmappers.DefaultListMapper;
import com.academy.project.model.entity.OrderDetails;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = OrderDetailsMapper.class)
public interface OrderDetailsListMapper extends DefaultListMapper<OrderDetailsDto, OrderDetails> {
}

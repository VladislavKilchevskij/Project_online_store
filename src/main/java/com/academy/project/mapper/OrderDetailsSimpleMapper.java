package com.academy.project.mapper;

import com.academy.project.dto.OrderDetailsSimpleDto;
import com.academy.project.mapper.defaultmappers.DefaultMapper;
import com.academy.project.model.entity.OrderDetails;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = AddressMapper.class)
public interface OrderDetailsSimpleMapper extends DefaultMapper<OrderDetailsSimpleDto, OrderDetails> {
}

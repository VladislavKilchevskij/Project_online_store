package com.academy.project.mapper;

import com.academy.project.dto.OrderDetailsDto;
import com.academy.project.mapper.defaultMappers.DefaultMapper;
import com.academy.project.model.entity.OrderDetails;
import com.academy.project.service.AddressService;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = {CartProductListMapper.class, AddressService.class})
public interface OrderDetailsMapper extends DefaultMapper<OrderDetailsDto, OrderDetails> {

    @Mapping(source = "addressId", target = "address")
    OrderDetails toEntity(OrderDetailsDto orderDetailsDto);
}

package com.academy.project.mapper;

import com.academy.project.dto.ProductDto;
import com.academy.project.mapper.defaultMappers.DefaultMapper;
import com.academy.project.model.entity.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {CategoryMapper.class, ManufacturerMapper.class, MediaListMapper.class})
public interface ProductMapper extends DefaultMapper<ProductDto, Product> {
}

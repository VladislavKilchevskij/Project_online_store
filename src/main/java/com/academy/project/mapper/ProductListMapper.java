package com.academy.project.mapper;

import com.academy.project.dto.ProductDto;
import com.academy.project.mapper.defaultMappers.DefaultListMapper;
import com.academy.project.model.entity.Product;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = ProductMapper.class)
public interface ProductListMapper extends DefaultListMapper<ProductDto, Product> {
}

package com.academy.project.mapper;

import com.academy.project.dto.ManufacturerDto;
import com.academy.project.mapper.defaultmappers.DefaultListMapper;
import com.academy.project.model.entity.Manufacturer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = ManufacturerMapper.class)
public interface ManufacturerListMapper extends DefaultListMapper<ManufacturerDto, Manufacturer> {
}

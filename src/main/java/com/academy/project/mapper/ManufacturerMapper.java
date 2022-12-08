package com.academy.project.mapper;

import com.academy.project.dto.ManufacturerDto;
import com.academy.project.mapper.defaultmappers.DefaultMapper;
import com.academy.project.model.entity.Manufacturer;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface ManufacturerMapper extends DefaultMapper<ManufacturerDto, Manufacturer> {
}

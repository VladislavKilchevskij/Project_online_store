package com.academy.project.mapper;

import com.academy.project.dto.MediaDto;
import com.academy.project.mapper.defaultMappers.DefaultMapper;
import com.academy.project.model.entity.Media;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR)
public interface MediaMapper extends DefaultMapper<MediaDto, Media> {
}

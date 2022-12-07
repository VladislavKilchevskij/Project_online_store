package com.academy.project.mapper;

import com.academy.project.dto.MediaDto;
import com.academy.project.mapper.defaultMappers.DefaultListMapper;
import com.academy.project.model.entity.Media;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;

@Mapper(injectionStrategy = InjectionStrategy.CONSTRUCTOR, uses = MediaMapper.class)
public interface MediaListMapper extends DefaultListMapper<MediaDto, Media> {
}

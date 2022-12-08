package com.academy.project.mapper.defaultmappers;


import java.util.List;

public interface DefaultListMapper<T, E> {

    List<T> toDtoList(List<E> entities);
    List<E> toEntityList(List<T> dtos);

}

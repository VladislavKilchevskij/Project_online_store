package com.academy.project.mapper.defaultmappers;

public interface DefaultMapper<T, E> {
    T toDto(E entity);
    E toEntity(T dto);
}

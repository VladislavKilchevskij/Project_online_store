package com.academy.project.mapper.defaultMappers;

public interface DefaultMapper<T, E> {
    T toDto(E entity);
    E toEntity(T dto);
}

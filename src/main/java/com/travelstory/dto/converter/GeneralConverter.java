package com.travelstory.dto.converter;

import com.travelstory.dto.BaseDTO;

import java.util.List;
import java.util.stream.Collectors;

public interface GeneralConverter<D extends BaseDTO, E> {
    E convertToEntity(D dto);

    D convertToDto(E entity);

    default List<E> convertToEntity(List<D> dtos) {
        return dtos.stream().map(this::convertToEntity).collect(Collectors.toList());
    }

    default List<D> convertToDto(List<E> entities) {
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}

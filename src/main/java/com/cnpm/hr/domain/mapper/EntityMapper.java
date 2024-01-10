package com.cnpm.hr.domain;

import java.util.List;

public interface EntityMapper<D, E>{
    E toEntity(D Dto);

    List<E> toEntities(List<D> dtoList);

    D toDTO(E entity);

    List<D> toDTOList(List<E> entities);
}

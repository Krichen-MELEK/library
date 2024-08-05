package com.clap.library.mappers;

import java.util.List;

public interface EntityMapper<T,D> {
    T toEntity(D d);
    D toDto(T t);
    default List<T> toEntityList(List<D> listD){
        return listD.stream().map(this::toEntity).toList();
    }
    default List<D> toDtoList(List<T> listT){
        return listT.stream().map(this::toDto).toList();
    }
}

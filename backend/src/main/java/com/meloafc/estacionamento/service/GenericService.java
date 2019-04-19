package com.meloafc.estacionamento.service;

import java.util.List;

public interface GenericService<T,I> {

    T saveOrUpdate(T entity);
    List<T> getAll();
    T get(I id);
    T add(T entity);
    T update(T entity);
    void remove(T entity);
    void removeById(I id);
    void validate(T entity);
    void validateUniqueKey(T entity);
    void validateId(T entity, boolean isUpdate);

}

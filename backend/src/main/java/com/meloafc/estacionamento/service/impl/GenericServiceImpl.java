package com.meloafc.estacionamento.service.impl;

import com.meloafc.estacionamento.exception.InvalidEntityException;
import com.meloafc.estacionamento.model.BaseModel;
import com.meloafc.estacionamento.service.GenericService;
import com.meloafc.estacionamento.utils.ValidateUtils;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.UnexpectedRollbackException;

import java.io.Serializable;
import java.util.List;

@Service
public abstract class GenericServiceImpl<T extends BaseModel<I>, I extends Serializable> implements GenericService<T, I> {

    @Autowired
    protected JpaRepository<T, I> repository;

    @Override
    public T saveOrUpdate(T entity) {
        try {
            return repository.save(entity);
        } catch (ConstraintViolationException e) {
            throw new InvalidEntityException(e.getMessage());
        }
    }

    @Override
    public List<T> getAll() {
        return repository.findAll();
    }

    @Override
    public T get(I id) {
        T entity = repository.findById(id).orElse(null);
        ValidateUtils.checkFound(entity, "object.not.found");
        return entity;
    }

    @Override
    public T add(T entity) {
        validateId(entity, false);
        validate(entity);
        return this.saveOrUpdate(entity);
    }

    @Override
    public T update(T entity) {
        validateId(entity, true);
        validate(entity);
        return this.saveOrUpdate(entity);
    }

    @Override
    public void remove(T entity) {
        try {
            repository.delete(entity);
        } catch (ConstraintViolationException | DataIntegrityViolationException | UnexpectedRollbackException e) {
            throw new InvalidEntityException("constraintViolationOnDelete");
        }
    }

    @Override
    public void removeById(I id) {
        try {
            repository.deleteById(id);
        } catch (ConstraintViolationException | DataIntegrityViolationException | UnexpectedRollbackException e) {
            throw new InvalidEntityException("constraintViolationOnDelete");
        }
    }

    @Override
    public void validate(T entity) {
        validateUniqueKey(entity);
    }

    @Override
    public void validateUniqueKey(T entity) {
    }

    @Override
    public void validateId(T entity, boolean isUpdate) {
        if (isUpdate) {
            ValidateUtils.checkBiggerThanZero((Long) entity.getId(), "id.mustBeFilled");
        } else {
            ValidateUtils.checkMustBeNullOrZero((Long) entity.getId(), "id.cannotBeFilled");
        }
    }

}

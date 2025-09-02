package com.stefano.draiver.services;

import com.stefano.draiver.domain.entities.BaseEntity;
import com.stefano.draiver.exceptions.ResourceNotFoundException;
import com.stefano.draiver.repositories.jpa.BaseRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

public abstract class BaseService<T extends BaseEntity> {

    protected abstract BaseRepository<T> getRepository();

    @Transactional
    public T create(T entity) {
        beforeCreate(entity);

        entity.validateOnSave();

        save(entity);

        afterCreate(entity);

        return entity;
    }

    protected void save(T entity) {
        getRepository().save(entity);
    }

    @Transactional
    public void delete(T entity) {
        beforeDelete(entity);

        entity.validateOnDelete();

        entity.inactivate();

        save(entity);

        afterDelete(entity);
    }

    @Transactional
    public void update(T entity) {
        beforeUpdate(entity);

        entity.beforeUpdate();

        entity.validateOnSave();

        save(entity);

        afterUpdate(entity);
    }

    @Transactional
    public void delete( UUID entityId) {
        T entity = getRepository().findById(entityId).filter(BaseEntity::isActive).orElseThrow(
                () -> new ResourceNotFoundException(entityId));

        delete(entity);
    }

    protected void beforeCreate(T entity) {}

    protected void afterCreate(T entity) {}

    protected void beforeUpdate(T entity) {}

    protected void beforeDelete(T entity) {}

    protected void afterDelete(T entity) {}

    protected void afterUpdate(T entity) {}
}

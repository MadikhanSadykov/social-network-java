package com.madikhan.app.dao;


import com.madikhan.app.model.Profile;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface DAO<K extends Serializable, E> {

    E create(E entity);

    void delete(K id);

    Profile update(E entity);

    Optional<E> findById(K id);

    Optional<List<E>> findAll();

}

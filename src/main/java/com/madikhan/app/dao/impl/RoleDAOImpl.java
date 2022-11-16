package com.madikhan.app.dao.impl;

import com.madikhan.app.dao.DAO;
import com.madikhan.app.model.Role;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class RoleDAOImpl implements DAO<Long, Role> {

    private Class<Role> entityClass;

    @Autowired
    private EntityManager entityManager;

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(Role role) {

    }

    @Override
    public Optional<Role> findById(Long id) {
        String query = "SELECT r FROM Role r WHERE r.id = :roleId";
        TypedQuery<Role> typedQuery = entityManager.createQuery(query, Role.class);
        typedQuery.setParameter("roleId", id);
        Optional<Role> role = Optional.empty();
        try {
            role = Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return role;
    }

    @Override
    public List findAll() {
        return null;
    }
}

package com.madikhan.app.dao.impl;

import com.madikhan.app.dao.DAO;
import com.madikhan.app.model.Profile;
import com.madikhan.app.model.Role;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@Slf4j
@Component
public class RoleDAOImpl implements DAO<Long, Role> {

    private EntityManager entityManager;

    @Autowired
    public RoleDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Role create(Role role) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Profile update(Role role) {

        return null;
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
    public Optional<List<Role>> findAll() {
        return Optional.empty();
    }
}

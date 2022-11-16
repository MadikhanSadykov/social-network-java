package com.madikhan.app.dao.impl;

import com.madikhan.app.dao.DAO;
import com.madikhan.app.model.Profile;
import com.madikhan.app.model.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class UserDAOImpl implements DAO<Long, User> {

    private Class<Profile> entityClass;

    @Autowired
    private EntityManager entityManager;

    public Optional<User> findUserByUsername(String email) {
        Optional<User> user = Optional.empty();
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            user = Optional
                    .ofNullable(entityManager.createQuery("From User where email='" + email + "'", User.class)
                            .getSingleResult());
            entityManager.flush();
            transaction.commit();
        } catch (NoResultException ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public User create(User user) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(user);
            entityManager.flush();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return user;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public void update(User entity) {

    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }
}

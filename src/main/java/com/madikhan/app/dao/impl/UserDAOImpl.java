package com.madikhan.app.dao.impl;

import com.madikhan.app.dao.DAO;
import com.madikhan.app.model.Profile;
import com.madikhan.app.model.User;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

@Data
@Component
public class UserDAOImpl implements DAO<Long, User> {

    private EntityManager entityManager;

    @Autowired
    public UserDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<User> findUserByUsername(String email) {
        Optional<User> user = Optional.empty();
        EntityTransaction transaction = null;

        String HQL = "from User where email =:email ";
        Query query = entityManager.createQuery(HQL);
        query.setParameter("email", email);
        query.setHint("org.hibernate.comment", String.format("email = %s", email));

        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            user = Optional.ofNullable((User) query.getSingleResult());
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
        EntityTransaction transaction;
        transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        entityManager.flush();
        transaction.commit();

        return user;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Profile update(User entity) {

        return null;
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<User>> findAll() {
        return Optional.empty();
    }
}

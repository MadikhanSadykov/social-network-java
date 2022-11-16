package com.madikhan.app.dao.impl;

import com.madikhan.app.dao.DAO;
import com.madikhan.app.model.Profile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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
@Slf4j
@Component
public class ProfileDAOImpl implements DAO<Long, Profile> {

    private Class<Profile> entityClass;

    @Autowired
    private EntityManager entityManager;

    public Optional<Profile> findByUserId(Long id) {
        String query = "SELECT p FROM Profile p WHERE p.userId = :id";
        TypedQuery<Profile> typedQuery = entityManager.createQuery(query, Profile.class);
        typedQuery.setParameter("id", id);
        Optional<Profile> profile = Optional.empty();
        try {
            profile = Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            log.info(ex.getMessage());
        }
        return profile;
    }

    @Override
    public Profile create(Profile profile) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.persist(profile);
            entityManager.flush();
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
        return profile;
    }

    @Override
    public void delete(Long id) {
        EntityTransaction transaction = null;
        Profile profile;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            profile = entityManager.find(Profile.class, id);
            entityManager.remove(profile);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public void update(Profile profile) {
        EntityTransaction transaction = null;
        try {
            transaction = entityManager.getTransaction();
            transaction.begin();
            entityManager.merge(profile);
            transaction.commit();
        } catch (Exception ex) {
            if (transaction != null) {
                transaction.rollback();
            }
            ex.printStackTrace();
        }
    }

    @Override
    public Optional<Profile> findById(Long id) {
        String query = "SELECT p FROM Profile p WHERE p.id = :profileId";
        TypedQuery<Profile> typedQuery = entityManager.createQuery(query, Profile.class);
        typedQuery.setParameter("profileId", id);
        Optional<Profile> profile = Optional.empty();
        try {
            profile = Optional.ofNullable(typedQuery.getSingleResult());
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return profile;
    }

    @Override
    public List<Profile> findAll() {
        String query = "SELECT p FROM Profile p WHERE p.id IS NOT NULL";
        TypedQuery<Profile> typedQuery = entityManager.createQuery(query, Profile.class);
        List<Profile> profiles = null;
        try {
            profiles = typedQuery.getResultList();
        } catch (NoResultException ex) {
            ex.printStackTrace();
        }
        return profiles;
    }

}

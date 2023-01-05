package com.madikhan.app.dao.impl;

import com.madikhan.app.dao.DAO;
import com.madikhan.app.model.Post;
import com.madikhan.app.model.Profile;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;
import java.util.Optional;

@Data
@Slf4j
@Component
public class PostDAOImpl implements DAO<Long, Post> {

    private final EntityManager entityManager;

    @Autowired
    public PostDAOImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Post create(Post post) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(post);
        entityManager.flush();
        transaction.commit();

        return post;
    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public Profile update(Post entity) {
        return null;
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<List<Post>> findAll() {
        return Optional.empty();
    }
}

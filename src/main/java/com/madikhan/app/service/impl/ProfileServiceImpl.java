package com.madikhan.app.service.impl;

import com.madikhan.app.dao.impl.ProfileDAOImpl;
import com.madikhan.app.model.Profile;
import com.madikhan.app.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private ProfileDAOImpl profileDAO;

    @Override
    public void save(Profile profile) {
        profileDAO.create(profile);
    }

    @Override
    public void remove(Long id) {
        profileDAO.delete(id);
    }

    @Override
    public void update(Profile profile) {
        profileDAO.update(profile);
    }

    @Override
    public Optional<List<Profile>> listAll() {
        return profileDAO.findAll();
    }

    @Override
    public Profile listById(Long id) {
        return profileDAO.findById(id).orElseGet(Profile::new);
    }

    @Override
    public Profile listByUserId(Long userId) {
        return profileDAO.findByUserId(userId).orElseGet(Profile::new);
    }

    @Override
    public Optional<Profile> listByUsername(String username) {
        return profileDAO.findByUsername(username);
    }
}

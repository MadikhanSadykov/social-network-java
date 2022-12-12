package com.madikhan.app.service;

import com.madikhan.app.model.Profile;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProfileService {

    void save(Profile profile);
    void remove(Long id);
    void update(Profile profile);
    Optional<List<Profile>> listAll();
    Profile listById(Long id);
    Profile listByUserId(Long userId);

    Optional<Profile> listByUsername(String username);
}

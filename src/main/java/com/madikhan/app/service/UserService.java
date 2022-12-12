package com.madikhan.app.service;

import com.madikhan.app.model.User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    Optional<User> listByUsername(String username);
    User save(User user);
    void remove(Long id);
    void update(User user);
    Optional<List<User>> listAll();
    User listById(Long id);

}

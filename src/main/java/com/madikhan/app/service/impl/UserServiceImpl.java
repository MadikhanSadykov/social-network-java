package com.madikhan.app.service.impl;

import com.madikhan.app.dao.impl.RoleDAOImpl;
import com.madikhan.app.dao.impl.UserDAOImpl;
import com.madikhan.app.model.Role;
import com.madikhan.app.model.User;
import com.madikhan.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static com.madikhan.app.constant.NameConstant.USER_ROLE_ID;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDAOImpl userDAO;

    @Autowired
    private RoleDAOImpl roleDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Optional<User> listByUsername(String username) {
        return userDAO.findUserByUsername(username);
    }

    @Override
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(roleDAO.findById(USER_ROLE_ID).orElseGet(Role::new));
        user.setRoles(roles);
        userDAO.create(user);
    }

    @Override
    public void remove(Long id) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public List<User> listAll() {
        return null;
    }

    @Override
    public User listById(Long id) {
        return null;
    }
}

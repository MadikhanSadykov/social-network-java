package com.madikhan.app.service.impl;

import com.madikhan.app.dao.impl.UserDAOImpl;
import com.madikhan.app.model.User;
import com.madikhan.app.security.AccountDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserDAOImpl userDAO;

    @Autowired
    public UserDetailsServiceImpl(UserDAOImpl userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userDAO.findUserByUsername(username);
        User user;
        if (optionalUser.isEmpty()) {
            log.error("Cannot find user with username: " + username);
            throw new UsernameNotFoundException("User not found!");
        } else {
            user = optionalUser.get();
        }

        return new AccountDetails(user);
    }
}

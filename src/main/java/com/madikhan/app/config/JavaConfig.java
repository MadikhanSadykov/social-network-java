package com.madikhan.app.config;

import com.madikhan.app.dao.impl.ProfileDAOImpl;
import com.madikhan.app.dao.impl.RoleDAOImpl;
import com.madikhan.app.dao.impl.UserDAOImpl;
import com.madikhan.app.model.Profile;
import com.madikhan.app.model.User;
import com.madikhan.app.service.ProfileService;
import com.madikhan.app.service.UserService;
import com.madikhan.app.service.impl.ProfileServiceImpl;
import com.madikhan.app.service.impl.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ResourceBundle;

@Configuration
@ComponentScan(basePackages = { "com.madikhan" } )
@Import({WebConfig.class, HibernateConfig.class})
public class JavaConfig {

    @Bean
    public Profile profile() {
        return new Profile();
    }

    @Bean
    public ProfileService profileService() {
        return new ProfileServiceImpl();
    }

    @Bean
    public ProfileDAOImpl profileDAO() {
        return new ProfileDAOImpl();
    }

    @Bean
    public User user() {
        return new User();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }

    @Bean
    public UserDAOImpl userDAO() {
        return new UserDAOImpl();
    }

    @Bean
    public RoleDAOImpl roleDAO() {
        return new RoleDAOImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}

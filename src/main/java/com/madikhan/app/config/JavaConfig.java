package com.madikhan.app.config;

import com.madikhan.app.dao.impl.ImageDAOImpl;
import com.madikhan.app.dao.impl.PostDAOImpl;
import com.madikhan.app.dao.impl.ProfileDAOImpl;
import com.madikhan.app.dao.impl.RoleDAOImpl;
import com.madikhan.app.dao.impl.UserDAOImpl;
import com.madikhan.app.dto.mapper.ProfileDTOMapper;
import com.madikhan.app.dto.mapper.UserDTOMapper;
import com.madikhan.app.model.Profile;
import com.madikhan.app.model.User;
import com.madikhan.app.service.ImageService;
import com.madikhan.app.service.ProfileService;
import com.madikhan.app.service.UserService;
import com.madikhan.app.service.impl.ImageServiceImpl;
import com.madikhan.app.service.impl.ProfileServiceImpl;
import com.madikhan.app.service.impl.UserDetailsServiceImpl;
import com.madikhan.app.service.impl.UserServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import javax.persistence.EntityManager;


@Configuration
@ComponentScan(basePackages = { "com.madikhan" } )
@Import({WebConfig.class, HibernateConfig.class})
@PropertySource({"classpath:application.properties"})
public class JavaConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer props = new PropertySourcesPlaceholderConfigurer();
        props.setLocations(new ClassPathResource("persistence.properties"));
        return props;
    }

    @Bean
    public Profile profile() {
        return new Profile();
    }

    @Bean
    public ProfileDAOImpl profileDAO(EntityManager entityManager) {
        return new ProfileDAOImpl(entityManager);
    }

    @Bean
    public PostDAOImpl postDAO(EntityManager entityManager) {
        return new PostDAOImpl(entityManager);
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
    public UserDAOImpl userDAO(EntityManager entityManager) {
        return new UserDAOImpl(entityManager);
    }

    @Bean
    public RoleDAOImpl roleDAO(EntityManager entityManager) {
        return new RoleDAOImpl(entityManager);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserDAOImpl userDAO) {
        return new UserDetailsServiceImpl(userDAO);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ProfileDTOMapper profileDTOMapper(ModelMapper modelMapper) {
        return new ProfileDTOMapper(modelMapper);
    }

    @Bean
    public UserDTOMapper userDTOMapper(ModelMapper modelMapper) {
        return new UserDTOMapper(modelMapper);
    }

    @Bean
    public ImageDAOImpl imageDAO(Environment environment) {
        return new ImageDAOImpl(environment);
    }

    @Bean
    public ProfileService profileService(ProfileDAOImpl profileDAO) {
        return new ProfileServiceImpl(profileDAO);
    }

    @Bean
    public ImageService imageService(ImageDAOImpl imageDAO) {
        return new ImageServiceImpl(imageDAO);
    }

}

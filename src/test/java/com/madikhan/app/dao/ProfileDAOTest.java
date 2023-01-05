package com.madikhan.app.dao;

import com.madikhan.app.config.HibernateConfig;
import com.madikhan.app.config.JavaConfig;
import com.madikhan.app.config.WebConfig;
import com.madikhan.app.dao.impl.ProfileDAOImpl;
import com.madikhan.app.model.Profile;
import com.madikhan.app.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { HibernateConfig.class, JavaConfig.class, WebConfig.class })
@WebAppConfiguration
public class ProfileDAOTest {

    @Autowired
    private ProfileDAOImpl profileDAO;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Test
    void testListAllProfilesIfAdded() {
        Assertions.assertTrue(profileDAO.findAll().isPresent());
    }

    @Test
    public void testSaveWithNoExceptions() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("example@gmail.com");
        user.setPassword(passwordEncoder.encode("testtest"));

        Profile profile = new Profile();
        profile.setUsername("username");
        profile.setFirstName("firstName");
        profile.setLastName("lastName");
        profile.setUser(user);

        Assertions.assertEquals("username", profileDAO.create(profile).getUsername());

    }

    @Test
    public void testListByProfileId() {
        Assertions.assertTrue(profileDAO.findById(1L).isPresent());
    }

    @Test
    public void testListByUsername() {
        Assertions.assertTrue(profileDAO.findByUsername("username").isPresent());
    }

    @Test
    public void testUpdateImageByProfileId() {
        String imageUrl = "resources/img/image.png";
        profileDAO.updateAvatarByProfileId(2L, imageUrl);
        Assertions.assertEquals(imageUrl, profileDAO.findById(2L).get().getImageUrl());
    }

}

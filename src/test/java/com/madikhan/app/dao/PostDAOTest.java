package com.madikhan.app.dao;

import com.madikhan.app.config.HibernateConfig;
import com.madikhan.app.config.JavaConfig;
import com.madikhan.app.config.WebConfig;
import com.madikhan.app.dao.impl.PostDAOImpl;
import com.madikhan.app.dao.impl.ProfileDAOImpl;
import com.madikhan.app.model.Post;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { HibernateConfig.class, JavaConfig.class, WebConfig.class })
@WebAppConfiguration
public class PostDAOTest {

    @Autowired
    private PostDAOImpl postDAO;

    @Autowired
    private ProfileDAOImpl profileDAO;

    @Test
    void testSaveWithNoExceptions() {
        Post post = new Post();
        post.setTitle("Title");
        post.setCaption("This is the Caption of the post");
        post.setLocation("Kazakhstan");
        post.setProfile(profileDAO.findById(1L).get());
//        post.setStatus();
    }
}

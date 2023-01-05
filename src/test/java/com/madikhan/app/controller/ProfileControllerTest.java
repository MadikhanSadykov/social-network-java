package com.madikhan.app.controller;

import com.madikhan.app.config.HibernateConfig;
import com.madikhan.app.config.JavaConfig;
import com.madikhan.app.config.WebConfig;
import com.madikhan.app.model.Profile;
import com.madikhan.app.service.ProfileService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { HibernateConfig.class, JavaConfig.class, WebConfig.class })
@WebAppConfiguration
public class ProfileControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Mock
    private ProfileController profileController;

    @Mock
    private ProfileService profileService;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
    }

    @Test
    public void testListAllProfiles() throws Exception {
        List<Profile> profiles = new ArrayList<>();
        profiles.add(new Profile());
        profiles.add(new Profile());

        when(profileService.listAll()).thenReturn(Optional.of(profiles));

        mockMvc.perform(get("/profiles"))
                .andExpect(status().isOk())
                .andExpect(view().name("profiles"))
                .andExpect(model().attribute("profiles", hasSize(2)));
    }

}

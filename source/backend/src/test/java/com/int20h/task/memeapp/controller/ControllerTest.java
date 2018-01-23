package com.int20h.task.memeapp.controller;

import com.int20h.task.memeapp.MemeAppApplication;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = MemeAppApplication.class)
@WebAppConfiguration
/*@TestPropertySource(locations="classpath:test.properties")*/
public abstract class ControllerTest {
    @Autowired
    private WebApplicationContext context;

    protected MockMvc mockMvc;

    @Before
    public void init() throws Exception {
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .build();
    }
}

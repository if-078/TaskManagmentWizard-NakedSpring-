package it.com.softserve.academy.tmw.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.controller.StatusController;
import com.softserve.academy.tmw.entity.Status;
import it.com.softserve.academy.tmw.configuration.TestConfig;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class StatusControllerTest {


    @Autowired
    private StatusController controller;
    private ObjectMapper jsonMapper = new ObjectMapper();
    private MockMvc mockMvc;
    private List<Status> statuses;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void shouldDoSomething(){

    }

}

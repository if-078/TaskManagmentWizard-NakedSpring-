package it.com.softserve.academy.tmw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.controller.UserController;
import com.softserve.academy.tmw.entity.User;
import it.com.softserve.academy.tmw.configuration.TestConfig;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_CLASS)
public class UserControllerItTest {

  @Autowired
  private UserController controller;
  private ObjectMapper jsonMapper = new ObjectMapper();
  private MockMvc mockMvc;
  private List<User> userList;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    userList = new ArrayList();
    User user = new User();
    user.setPass("1234567890z");
    user.setEmail("someMail@Mail.com");
    user.setId(1);
    user.setName("someName");
    user.setActivated(true);
    User user2 = new User();
    user2.setPass("1234567890z2");
    user2.setEmail("someMail2@Mail.com");
    user2.setId(1);
    user2.setName("someName2");
    user2.setActivated(true);
    userList.add(user);
    userList.add(user2);

  }

  @Test
  public void createFewUsersAndGetByEMail() throws Exception {
    mockMvc.perform(post("/api/users/add/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(userList.get(0))))
        .andExpect(status().isCreated());

    mockMvc.perform(post("/api/users/add/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(userList.get(1))))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/api/users/email/" + userList.get(0).getEmail())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

  }

}

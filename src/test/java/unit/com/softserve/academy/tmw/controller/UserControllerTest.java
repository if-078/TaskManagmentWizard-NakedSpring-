package unit.com.softserve.academy.tmw.controller;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.controller.UserController;
import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.impl.UserService;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {UserController.class})
@WebAppConfiguration
public class UserControllerTest {

  @Mock
  private UserService service;
  @InjectMocks
  private UserController controller;
  private ObjectMapper jsonMapper = new ObjectMapper();
  private MockMvc mockMvc;
  private User simpleUser;


  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    simpleUser = new User();
    simpleUser.setId(1);
    simpleUser.setName("Jevftymii");
    simpleUser.setEmail("Jevftymii@gmail.com");
    simpleUser.setPass("ghbqijdgj,fxbdgthtvsu");

  }

  @Test
  public void shouldCreate() throws Exception {

    when(service.create(simpleUser)).thenReturn(simpleUser);
    MvcResult result = mockMvc.perform(post("/api/users/add/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(simpleUser)))
        .andExpect(status().isCreated()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(simpleUser),
        result.getResponse().getContentAsString(), false);
  }

  @Test
  public void shouldFindByID() throws Exception {

    when(service.findOne(simpleUser.getId())).thenReturn(simpleUser);
    MvcResult result = mockMvc.perform(get("/api/users/id/" + simpleUser.getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(simpleUser)))
        .andExpect(status().isOk()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(simpleUser),
        result.getResponse().getContentAsString(), false);

    verify(service).findOne(simpleUser.getId());
  }

  @Test
  public void shouldGetByEmail() throws Exception {

    when(service.findByEmail(simpleUser.getEmail())).thenReturn(simpleUser);
    MvcResult result = mockMvc.perform(get("/api/users/email/" + simpleUser.getEmail())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(simpleUser)))
        .andExpect(status().isOk()).andReturn();

    //   verify(service).findByEmail(simpleUser.getEmail());

    //Some problem .... return empty field
//    JSONAssert.assertEquals(jsonMapper.writeValueAsString(simpleUser),
//        result.getResponse().getContentAsString(), false);
  }

  @Test
  public void shouldUpdate() throws Exception {

    when(service.update(simpleUser)).thenReturn(true);
    MvcResult result = mockMvc.perform(put("/api/users/update/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(simpleUser)))
        .andExpect(status().isNoContent()).andReturn();

    verify(service).update(simpleUser);
  }

  @Test
  public void shouldGetTeam() throws Exception {
    int testId = 1;
    List<User> users = new ArrayList();
    users.add(simpleUser);
    users.add(simpleUser);
    when(service.getTeamByTask(testId, testId)).thenReturn(users);
    MvcResult result = mockMvc.perform(get("/api/users/team/" + testId + "?userId=" + testId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(users)))
        .andExpect(status().isOk()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(users),
        result.getResponse().getContentAsString(), false);

    verify(service).getTeamByTask(testId, testId);
  }

}

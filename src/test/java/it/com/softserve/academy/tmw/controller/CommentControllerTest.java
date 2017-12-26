package it.com.softserve.academy.tmw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.controller.CommentController;
import com.softserve.academy.tmw.entity.Comment;
import it.com.softserve.academy.tmw.configuration.TestConfig;
import it.com.softserve.academy.tmw.dao.utility.TaskPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
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
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class CommentControllerTest {

  @Autowired
  private UserPopulator userPopulator;
  @Autowired
  private TaskPopulator taskPopulator;
  @Autowired
  private CommentController controller;
  private ObjectMapper jsonMapper = new ObjectMapper();
  private MockMvc mockMvc;
  private List<Comment> comments;
  private int testId;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    userPopulator.createDefaultEntity();
    taskPopulator.createDefaultEntity();
    testId = 1;
    comments = new ArrayList();
    comments.add(new Comment("Comment1", testId, testId));

    comments.add(new Comment("Comment2", testId, testId));
    comments.add(new Comment("Comment3", testId, testId));

  }

  @Test
  public void shouldCreateFewFindDelete() throws Exception {

    mockMvc.perform(post("/api/comment/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(comments.get(0))))
        .andExpect(status().isCreated());

    mockMvc.perform(post("/api/comment/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(comments.get(1))))
        .andExpect(status().isCreated());

    mockMvc.perform(post("/api/comment/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(comments.get(2))))
        .andExpect(status().isCreated());

    mockMvc.perform(get("/api/comment/task/" + comments.get(0).getTaskId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());

    mockMvc.perform(delete("/api/comment/" + comments.get(0).getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());


  }


}

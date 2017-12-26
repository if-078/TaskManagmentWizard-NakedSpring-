package it.com.softserve.academy.tmw.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.controller.TagController;
import com.softserve.academy.tmw.entity.Tag;
import it.com.softserve.academy.tmw.configuration.TestConfig;
import it.com.softserve.academy.tmw.dao.utility.TaskPopulator;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class TagControllerItTest {

  @Autowired
  private UserPopulator userPopulator;
  @Autowired
  private TaskPopulator taskPopulator;
  @Autowired
  private TagController controller;
  private ObjectMapper jsonMapper = new ObjectMapper();
  private MockMvc mockMvc;
  private List<Tag> tags;

  @Before
  public void setUp() {

    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    userPopulator.createDefaultEntity();
    taskPopulator.createDefaultEntity();
    tags = new ArrayList();
    tags.add(new Tag(1, "#FirstTag", 1, 1));
    tags.add(new Tag(2, "#SecondTag", 1, 1));
    tags.add(new Tag(3, "#ThirdTag", 1, 1));

  }

  @Test
  public void shouldCreateFewFindDeleteAndFindAll() throws Exception {

    mockMvc.perform(post("/api/tags/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(tags.get(0))))
        .andExpect(status().isCreated());

    mockMvc.perform(post("/api/tags/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(tags.get(1))))
        .andExpect(status().isCreated());

    mockMvc.perform(post("/api/tags/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(tags.get(2))))
        .andExpect(status().isCreated());

    MvcResult result = mockMvc.perform(get("/api/tags/" + tags.get(0).getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(tags.get(0)),
        result.getResponse().getContentAsString(), false);

    mockMvc.perform(delete("/api/tags/" + tags.get(0).getId())
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    tags.remove(0);

    result = mockMvc.perform(get("/api/tags?projectId=" + 1)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(tags),
        result.getResponse().getContentAsString(), false);

  }

}

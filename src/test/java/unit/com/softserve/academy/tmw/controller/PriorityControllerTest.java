package unit.com.softserve.academy.tmw.controller;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.controller.PriorityController;
import com.softserve.academy.tmw.controller.StatusController;
import com.softserve.academy.tmw.entity.Priority;
import com.softserve.academy.tmw.service.impl.PriorityService;
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
@ContextConfiguration(classes = {StatusController.class})
@WebAppConfiguration
public class PriorityControllerTest {

  @Mock
  private PriorityService service;
  @InjectMocks
  private PriorityController controller;
  private ObjectMapper jsonMapper = new ObjectMapper();
  private MockMvc mockMvc;
  private Priority simplePriority;
  private int testId;

  @Before
  public void setUp() {
    testId = 1;
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    simplePriority = new Priority();
    simplePriority.setName("someName");
    simplePriority.setId(testId);
  }

  @Test
  public void shouldFindAll() throws Exception {
    List<Priority> priorities = new ArrayList();
    priorities.add(simplePriority);
    priorities.add(simplePriority);

    when(service.getAll()).thenReturn(priorities);
    MvcResult result = mockMvc.perform(get("/api//priority")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(priorities),
        result.getResponse().getContentAsString(), false);

    verify(service).getAll();
  }

}

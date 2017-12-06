package com.softserve.academy.tmw.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.dto.TaskDTO;
import com.softserve.academy.tmw.entity.Task;
import com.softserve.academy.tmw.service.impl.TaskService;
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
@ContextConfiguration(classes = {TaskController.class})
@WebAppConfiguration
public class TaskControllerTest {

  @Mock
  private TaskService service;
  @InjectMocks
  private TaskController controller;
  private ObjectMapper jsonMapper = new ObjectMapper();
  private MockMvc mockMvc;
  private Task simpleTask;
  private TaskDTO simpleDTO;
  private int testId;

  @Before
  public void setUp() {
    testId = 1;
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    simpleTask = new Task();
    simpleTask.setId(testId);
    simpleTask.setName("DoSomething");
    simpleTask.setPriorityId(testId);
    simpleTask.setStatusId(testId);
  }

  @Test
  public void shouldDelete() throws Exception {
    when(service.delete(testId)).thenReturn(true);
    mockMvc.perform(delete("/api/tasks/" + testId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent()).andReturn();

    verify(service).delete(testId);
  }

  @Test
  public void shouldGetOne() throws Exception {
    when(service.findOne(testId)).thenReturn(simpleTask);
    MvcResult result = mockMvc.perform(get("/api/tasks/" + testId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(simpleTask),
        result.getResponse().getContentAsString(), false);

    verify(service).findOne(testId);
  }

  @Test
  public void shouldGetSubTasks() throws Exception {
    List<Task> tasks = new ArrayList();
    tasks.add(simpleTask);
    tasks.add(simpleTask);

    when(service.getSubtasks(testId)).thenReturn(tasks);
    MvcResult result = mockMvc.perform(get("/api/tasks/" + testId + "/subtasks")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(tasks),
        result.getResponse().getContentAsString(), false);

    verify(service).getSubtasks(testId);
  }


}

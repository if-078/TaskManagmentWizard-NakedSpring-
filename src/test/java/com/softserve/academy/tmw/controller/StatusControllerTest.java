package com.softserve.academy.tmw.controller;


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.entity.Status;
import com.softserve.academy.tmw.service.impl.StatusService;
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
@WebAppConfiguration
public class StatusControllerTest {

  @Mock
  private StatusService service;
  @InjectMocks
  private StatusController controller;
  private ObjectMapper jsonMapper = new ObjectMapper();
  private MockMvc mockMvc;
  private Status simpleStatus;
  private int testId;

  @Before
  public void setUp() {
    testId = 1;
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    simpleStatus = new Status();
    simpleStatus.setName("someName");
    simpleStatus.setId(testId);
  }

  @Test
  public void shouldFindAll() throws Exception {
    List<Status> statuses = new ArrayList();
    statuses.add(simpleStatus);
    statuses.add(simpleStatus);

    when(service.getAll()).thenReturn(statuses);
    MvcResult result = mockMvc.perform(get("/api/status")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(statuses),
        result.getResponse().getContentAsString(), false);

    verify(service).getAll();
  }

}

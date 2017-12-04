/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package unit.com.softserve.academy.tmw.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.softserve.academy.tmw.controller.TagController;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.service.impl.TagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
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
@ContextConfiguration(classes = {TagController.class})
@WebAppConfiguration
public class TagControllerTest {

  @Mock
  private TagService service;
  @InjectMocks
  private TagController controller;
  private ObjectMapper jsonMapper = new ObjectMapper();
  private MockMvc mockMvc;
  private Tag simpleTag;
  private int testId;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    testId = 1;
    simpleTag = new Tag();
    simpleTag.setName("#SimpleName");
    simpleTag.setUserId(testId);
    simpleTag.setId(testId);

  }

  @Test
  public void shouldCreate() throws Exception {

    when(service.create(simpleTag)).thenReturn(simpleTag);
    MvcResult result = mockMvc.perform(post("/api/tags/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(simpleTag)))
        .andExpect(status().isCreated()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(simpleTag),
        result.getResponse().getContentAsString(), false);

    verify(service).create(simpleTag);

  }

  @Test
  public void shouldFind() throws Exception {

    when(service.findOne(Mockito.anyInt())).thenReturn(simpleTag);
    MvcResult result = mockMvc.perform(get("/api/tags/" + testId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk()).andReturn();

    JSONAssert.assertEquals(jsonMapper.writeValueAsString(simpleTag),
        result.getResponse().getContentAsString(), false);

    verify(service).findOne(testId);

  }

  @Test
  public void shouldDelete() throws Exception {

    when(service.delete(Mockito.anyInt())).thenReturn(true);
    mockMvc.perform(delete("/api/tags/" + testId)
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    verify(service).delete(testId);
  }

  @Test
  public void shouldUpdate() throws Exception {

    when(service.update(simpleTag)).thenReturn(true);
    mockMvc.perform(put("/api/tags/")
        .accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(jsonMapper.writeValueAsString(simpleTag)))
        .andExpect(status().isNoContent());

    verify(service).update(simpleTag);
  }

}

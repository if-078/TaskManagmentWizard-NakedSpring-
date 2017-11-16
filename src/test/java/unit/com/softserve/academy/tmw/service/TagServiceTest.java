package unit.com.softserve.academy.tmw.service;


import com.softserve.academy.tmw.dao.impl.TagDao;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.service.api.TagServiceInterface;
import com.softserve.academy.tmw.service.impl.TagService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class TagServiceTest {

    @InjectMocks
    private TagServiceInterface tagService = new TagService();

    @Mock
    private TagDao dao;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTagMockCreation() {
        assertNotNull(tagService);
        assertNotNull(dao);
    }

    @Test
    public void shouldCreateTag(){
        int testId=1;
        Tag simpleTag = new Tag();
        simpleTag.setName("#SimpleName");
        simpleTag.setUserId(testId);
        simpleTag.setId(testId);

        when(dao.create(any(Tag.class))).thenReturn(simpleTag);
        Tag testTag = tagService.create(simpleTag);

        assertThat(testTag).isNotNull();
        assertThat(testTag).isEqualTo(simpleTag);
        verify(dao).create(testTag);
    }

    @Test
    public void shouldDeleteTagById(){
        int testId=1;
        when(dao.delete(testId)).thenReturn(true);
        boolean isDeleted = tagService.delete(testId);
        assertThat(isDeleted).isTrue();
        verify(dao).delete(testId);
    }

    /*@Test
    public void addTags() {
        int[] tagsList = new int[4];
        for (int i = 0; i < tagsList.length; i++) {
            tagsList[i] = i + 1;
        }
        for (int i = 0; i < tagsList.length; i++) {
            System.out.println(tagsList[i]);
        }
        boolean added = dao.setTagsToTask(tagsList, 3);
        assertThat(added).isTrue();

    }*/



}
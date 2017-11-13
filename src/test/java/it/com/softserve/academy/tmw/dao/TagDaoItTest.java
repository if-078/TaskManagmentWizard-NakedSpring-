package it.com.softserve.academy.tmw.dao;

import static org.assertj.core.api.Assertions.assertThat;

import com.softserve.academy.tmw.dao.impl.TagDao;
import com.softserve.academy.tmw.entity.Tag;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class})
public class TagDaoItTest {

    @Autowired
    public TagDao tagDao;
    @Autowired
    public UserPopulator userPopulator;


    @Before
    public void createFewUsers() {
        userPopulator.createDefaultEntity();
        userPopulator.createDefaultEntity();
        userPopulator.createDefaultEntity();

    }

    @Test
    public void shouldCreateFindOneAndAllUpdateAndDelete() {
        tagDao.create(new Tag(1, "#Cat", 1));
        tagDao.create(new Tag(2, "#bicycle", 1));
        tagDao.create(new Tag(3, "#Books", 1));
        tagDao.create(new Tag(4, "#Sword", 2));
        tagDao.create(new Tag(5, "#Bow", 2));
        tagDao.create(new Tag(6, "#Axe", 2));
        tagDao.create(new Tag(7, "#Knife", 3));
        tagDao.create(new Tag(8, "#Searching", 3));
        int tagId [] = {1,2,3,4};
        //tagDao.setTagsToTask(tagId, 1);
        assertThat(tagDao.findOne(8).getName()).isEqualTo("#Searching");
        assertThat(tagDao.create(new Tag(9, "#Books", 3)).getId()).isEqualTo(9);
        assertThat(tagDao.delete(4)).isTrue();
        assertThat(tagDao.getAllByUserId(3)).hasSize(3);
        assertThat(tagDao.update(new Tag(1, "#Cat2", 2))).isTrue();
        assertThat(tagDao.deleteAllByUserId(1)).isTrue();
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void shouldNOTCreateFindOneAndAllUpdateAndDelete() {
        assertThat(tagDao.getAllByUserId(9999)).isEmpty();
        assertThat(tagDao.deleteAllByUserId(9999)).isFalse();
        assertThat(tagDao.update(new Tag(2, "atata", 9999))).isFalse();
        assertThat(tagDao.delete(100)).isFalse();
        assertThat(tagDao.findOne(1000).getId()).isIn(0);
    }


}
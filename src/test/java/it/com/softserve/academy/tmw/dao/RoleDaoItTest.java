package it.com.softserve.academy.tmw.dao;

import com.softserve.academy.tmw.dao.impl.RoleDao;
import com.softserve.academy.tmw.entity.Role;
import it.com.softserve.academy.tmw.dao.utility.RolePopulator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class})
public class RoleDaoItTest {

    @Autowired
    public RoleDao roleDao;
    @Autowired
    public RolePopulator rolePopulator;

    @Before
    public void getObcetsFromContext() throws SQLException {
        rolePopulator.initTable();
    }

    @After
    public void dropObjectsFromContext() throws SQLException {
        rolePopulator.deleteRecordsOfTable();
    }

    @Test
    public void ShouldCreateAndUpdateAndFind() throws Exception {
        Role role = roleDao.create(rolePopulator.initOneEntity("asdf"));
        assertEquals("asdf", roleDao.findOne(role.getId()).getName());
        role.setName("fdsa");
        roleDao.update(role);
        assertEquals("fdsa", roleDao.findOne(role.getId()).getName());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void ShouldCreateAndFindAndDeleteAndTrowException() throws Exception {
        Role role = roleDao.create(rolePopulator.initOneEntity("asdf"));
        assertNotNull(roleDao.findOne(role.getId()));
        roleDao.delete(role.getId());
        roleDao.findOne(role.getId());
    }

    @Test
    public void ShouldCreateAndGetAllAndDeleteAll() throws Exception {
        List<Role> list = roleDao.addBatch(rolePopulator.initOneEntity("zxcv1"),
                rolePopulator.initOneEntity("zxcv2"), rolePopulator.initOneEntity("zxcv3"));
        assertEquals(3, list.size());
        int id = 1;
        for (Role role : roleDao.getAll()) {
            if (role.getId() == id)
                assertEquals("zxcv" + id++, role.getName());
        }
        roleDao.deleteAll();
        assertEquals(0, roleDao.getAll().size());
    }
}
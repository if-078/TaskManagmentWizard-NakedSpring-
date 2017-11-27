package it.com.softserve.academy.tmw.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.softserve.academy.tmw.dao.impl.RoleDao;
import com.softserve.academy.tmw.entity.Role;
import it.com.softserve.academy.tmw.dao.utility.RolePopulator;
import java.sql.SQLException;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestDaoConfig.class})
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
public class RoleDaoItTest {

  @Autowired
  public RoleDao roleDao;
  @Autowired
  public RolePopulator rolePopulator;

  @Before
  public void getObcetsFromContext() throws SQLException {
    rolePopulator.initTable();
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
  public void ShouldCreateAndDeleteAll() throws Exception {
    List<Role> list = roleDao.addBatch(rolePopulator.initOneEntity("zxcv1"),
        rolePopulator.initOneEntity("zxcv2"), rolePopulator.initOneEntity("zxcv3"));
    assertEquals(3, list.size());
    int id = 1;
    roleDao.deleteAll();
    assertEquals(0, roleDao.getAll().size());
  }
}
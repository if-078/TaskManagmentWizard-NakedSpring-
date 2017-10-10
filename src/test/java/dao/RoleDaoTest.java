package dao;

import static org.junit.Assert.*;
import com.softserve.academy.entity.Role;
import org.junit.*;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import java.sql.SQLException;
import java.util.List;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import utility.RolePopulator;
import com.softserve.academy.service.interfaces.RoleServiceInterface;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class RoleDaoTest {

  @Autowired
  public RoleServiceInterface roleService;
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
  public void testCreateAndUpdateAndFind() throws Exception {
    Role role = roleService.create(rolePopulator.initOneEntity("asdf"));
    assertEquals("asdf", roleService.findOne(role.getId()).getName());
    role.setName("fdsa");
    roleService.update(role);
    assertEquals("fdsa", roleService.findOne(role.getId()).getName());
  }

  @Test(expected = EmptyResultDataAccessException.class)
  public void testCreateAndFindAndDeleteAndFindEmpty() throws Exception {
    Role role = roleService.create(rolePopulator.initOneEntity("asdf"));
    assertNotNull(roleService.findOne(role.getId()));
    roleService.delete(role.getId());
    roleService.findOne(role.getId());
  }

  @Test
  public void testCreateAndGetAllAndDeleteAll() throws Exception {
    List<Role> list = roleService.addBatch(rolePopulator.initOneEntity("zxcv1"),
        rolePopulator.initOneEntity("zxcv2"), rolePopulator.initOneEntity("zxcv3"));
    assertEquals(3, list.size());
    int id = 1;
    for (Role role : roleService.getAll()) {
      if (role.getId() == id)
        assertEquals("zxcv" + id++, role.getName());
    }
    roleService.deleteAll();
    assertEquals(0, roleService.getAll().size());
  }
}

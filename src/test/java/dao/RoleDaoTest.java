package dao;

import static org.junit.Assert.*;

import com.softserve.academy.entity.Role;
import com.softserve.academy.service.implementation.RoleService;
import org.junit.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;
import java.util.List;

@ContextConfiguration(classes = {TestConfig.class})
public class RoleDaoTest {

    public RoleService roleService;
    Role role;

    @Before
    public void getObcetsFromContext() throws SQLException {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);
        roleService = applicationContext.getBean(RoleService.class);
        role = new Role("asdf");
    }

    @Test
    public void testCreateAndUpdateAndGet() throws Exception {
        role = roleService.create(role);
        assertEquals("asdf", roleService.findOne(role.getId()).getName());
        role.setName("fdsa");
        roleService.update(role);
        assertEquals("fdsa", roleService.findOne(role.getId()).getName());
    }

    @Test(expected = EmptyResultDataAccessException.class)
    public void testCreateAndGetAndDelete() throws Exception {
        role = roleService.create(role);
        assertNotNull(roleService.findOne(role.getId()));
        roleService.delete(role.getId());
        roleService.findOne(role.getId());
    }

    @Test
    public void testCreateAndGetAllAndDeleteAll() throws Exception {
        List<Role> list = roleService.addBatch(new Role("zxcv1"), new Role("zxcv2"), new Role("zxcv3"));
        assertEquals(3, list.size());
/*        int id = list.get(0).getId();
        for (Role role : roleService.getAll()) {
            if (role.getId() == id) assertEquals("zxcv" + id++, role.getName());
        }*/
        roleService.deleteAll();
        //assertEquals(0, roleService.getAll().size());
    }
}
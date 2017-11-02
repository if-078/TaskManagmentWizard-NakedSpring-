package it.com.softserve.academy.tmw.dao;

import com.softserve.academy.tmw.dao.api.UserDaoInterface;
import com.softserve.academy.tmw.entity.User;
import it.com.softserve.academy.tmw.dao.utility.UserPopulator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.SQLException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TestConfig.class})
public class UserDaoIntTest {

    @Autowired
    private UserDaoInterface userDao;
    @Autowired
    private UserPopulator populator;

    @Test
    public void iTshouldInsertAndGetOneAndDelete() throws SQLException {
        // Given
        User userNew = new User();
        userNew.setName("if-078");
        userNew.setEmail("softServeAcademy@gmail.test");

        User userFindOne;
        // When
        userNew = userDao.create(userNew);
        userFindOne = userDao.findOne(userNew.getId());
        // Then
        assertThat(userNew.getEmail()).isEqualTo(userFindOne.getEmail());
        assertThat(userDao.delete(userFindOne.getId())).isTrue();
    }

    @Test
    public void iTshouldInsertAndGetByEmailAndGetAll() throws SQLException {
        // Given
        User userByEmail;
        User userNew = populator.createCustomUser("Academy", "soft@serve.com");
        // When
        userByEmail = userDao.findByEmail(userNew.getEmail());
        // Then
        assertThat(userNew.getEmail()).isEqualTo(userByEmail.getEmail());
        assertEquals(true, userDao.getAll().size() >= 1);
    }
}

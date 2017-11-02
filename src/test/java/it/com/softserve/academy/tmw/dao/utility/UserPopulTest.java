package it.com.softserve.academy.tmw.dao.utility;

import com.softserve.academy.tmw.entity.User;
import it.com.softserve.academy.tmw.dao.TestDaoConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;


@ContextConfiguration(classes = {TestDaoConfig.class})
public class UserPopulTest {

    @Test
    public void defaultUserCreated() throws SQLException {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TestDaoConfig.class);
        UserPopulator populator = applicationContext.getBean(UserPopulator.class);
        User user = populator.createDefaultEntity();
        Assert.assertTrue(user.getId() > 0);
    }
}
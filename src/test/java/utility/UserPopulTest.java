package utility;

import com.softserve.academy.entity.User;
import dao.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;


@ContextConfiguration(classes = {TestConfig.class})
public class UserPopulTest {

    @Test
    public void defaultUserCreated() throws SQLException {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);
        UserPopulator populator = applicationContext.getBean(UserPopulator.class);
        User user = populator.createDefaultEntity();
        Assert.assertTrue(user.getId() > 0);
    }
}
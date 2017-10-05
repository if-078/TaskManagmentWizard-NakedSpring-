package utility;

import com.softserve.academy.entity.Task;
import dao.TestConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import java.sql.SQLException;


@ContextConfiguration(classes = {TestConfig.class})
public class UserPopulTest {

    @Test
    public void testo () throws SQLException {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);
        TaskPopulator populator = applicationContext.getBean(TaskPopulator.class);
        Task status= populator.createDefaultHeadTask();
        System.out.println(status.getName());
        System.out.println(String.valueOf(status.getId()));
    }
}
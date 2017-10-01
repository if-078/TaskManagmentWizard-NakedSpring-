import com.softserve.academy.configuration.MainAppConfig;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MainAppConfig.class})
public class TestConteiner {
    @Test
    public void getContextBeans() {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainAppConfig.class);
        DataSource dataSource = (DataSource) applicationContext.getBean("getDataSource");
        assertNotNull(dataSource);
    }
}

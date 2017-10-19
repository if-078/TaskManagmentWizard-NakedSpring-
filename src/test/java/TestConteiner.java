import com.softserve.academy.configuration.MainAppConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import javax.sql.DataSource;

import static org.junit.Assert.assertNotNull;

@ContextConfiguration(classes = {MainAppConfig.class})
public class TestConteiner {

    @Test
    public void getContextBeans() {
        ApplicationContext applicationContext =
                new AnnotationConfigApplicationContext(MainAppConfig.class);
        DataSource dataSource = (DataSource) applicationContext.getBean(DataSource.class);
        assertNotNull(dataSource);

    }
}

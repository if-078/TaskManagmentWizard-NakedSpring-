package utility;

import com.softserve.academy.entity.Priority;
import dao.TestConfig;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TestConfig.class})
public class PriorityTest {
    private PriorityPopulator priorityPopulator;

    @Test
    public void Testo(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        priorityPopulator = context.getBean(PriorityPopulator.class);
        Priority p =  priorityPopulator.initOnePriority("LOW");
        System.out.println(p.getId());
        System.out.println(p.getName());
    }

}

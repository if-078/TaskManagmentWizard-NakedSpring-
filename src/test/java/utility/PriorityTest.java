package utility;

import com.softserve.academy.dao.implementation.PriorityDao;
import com.softserve.academy.entity.Priority;
import dao.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TestConfig.class})
public class PriorityTest {
    private PriorityPopulator priorityPopulator;

    @Test
    public void priorityEntityCreatedAndWrittenInDb(){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        priorityPopulator = context.getBean(PriorityPopulator.class);
        PriorityDao dao = context.getBean(PriorityDao.class);
        Priority target =  priorityPopulator.initOnePriority("LOW");
        Priority expected = dao.findOne(1);
        Assert.assertTrue(expected.getId() == target.getId());
    }
}
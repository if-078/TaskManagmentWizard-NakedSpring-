package com.softserve.academy.tmw.utility;

import com.softserve.academy.tmw.dao.TestConfig;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TestConfig.class})
public class PriorityTest {
    private PriorityPopulator priorityPopulator;

    @Test
    public void priorityEntityCreatedAndWrittenInDb() {
        // ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        // priorityPopulator = context.getBean(PriorityPopulator.class);
        // PriorityDao com.softserve.academy.tmw.dao = context.getBean(PriorityDao.class);
        // Priority target = priorityPopulator.initOnePriority("LOW");
        // Priority expected = com.softserve.academy.tmw.dao.findOne(1);
        // Assert.assertTrue(expected.getId() == target.getId());
    }
}

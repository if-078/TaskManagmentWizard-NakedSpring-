package utility;

import com.softserve.academy.dao.implementation.CommentDaoImpl;
import com.softserve.academy.entity.Comment;
import dao.TestConfig;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

@ContextConfiguration(classes = {TestConfig.class})
public class CommentPopulTest {

    @Test
    public void commentCreatedAndInserted (){
        ApplicationContext context = new AnnotationConfigApplicationContext(TestConfig.class);
        CommentPopulator populator = context.getBean(CommentPopulator.class);
        CommentDaoImpl dao = context.getBean(CommentDaoImpl.class);
        Comment actual = populator.createDefaultEntity();
        Comment result = dao.findOne(actual.getId());
        Assert.assertEquals(actual.getCommentText(), result.getCommentText());
        Assert.assertEquals(actual.getCreatedDate(), result.getCreatedDate());
        Assert.assertEquals(actual.getTaskId(), result.getTaskId());
        Assert.assertEquals(actual.getUserId(), result.getUserId());
    }
}
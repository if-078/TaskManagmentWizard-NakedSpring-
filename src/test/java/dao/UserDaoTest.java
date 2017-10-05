package dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.softserve.academy.entity.User;
import com.softserve.academy.service.interfaces.UserService;
import utility.UserPopulator;

@ContextConfiguration(classes = {TestConfig.class})
public class UserDaoTest {
  
  private UserService userService;
  private UserPopulator populator;
  
  @Before
  public void getObcetsFromContext() throws SQLException {
      ApplicationContext applicationContext =
              new AnnotationConfigApplicationContext(TestConfig.class);
      userService = applicationContext.getBean(UserService.class);
      populator = applicationContext.getBean(UserPopulator.class);
  }
  
  @Test
  public void iTShoudExecuteNegativeTest() throws SQLException {
	 int testId = 50;
	 assertThat(userService.getAll().isEmpty());
	 assertThat(userService.delete(testId)).isFalse();
  }
  
  @Test
  public void iTShouldInsertAndGetOneAndDelete() throws SQLException {
	// Given
	User userNew = new User();
	userNew.setName("if-078");
	userNew.setEmail("softServeAcademy@gmail.test");
	userNew.setPass("academypassword");
	User userFindOne;
	// When
	userNew = userService.create(userNew);
	userFindOne = userService.findOne(userNew.getId());
	// Then
	assertThat(userNew.getEmail()).isEqualTo(userFindOne.getEmail());
	assertThat(userService.delete(userFindOne.getId())).isTrue();	
  }
  
  @Test
  public void iTShouldInsertAndGetByEmailAndGetAll() throws SQLException {
	// Given
	User userByEmail;
	User userNew = populator.createCustomUser("Academy", "soft@serve.com");
	// When
	userByEmail = userService.findByEmail(userNew.getEmail());
	// Then
	assertThat(userNew.getEmail()).isEqualTo(userByEmail.getEmail());
	assertEquals(true, userService.getAll().size() >= 1);	}
}
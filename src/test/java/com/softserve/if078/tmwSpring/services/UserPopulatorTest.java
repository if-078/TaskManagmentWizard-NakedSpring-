package com.softserve.if078.tmwSpring.services;

import com.softserve.if078.tmwSpring.configurations.H2DbConfig;
import com.softserve.if078.tmwSpring.entities.User;
import com.softserve.if078.tmwSpring.utility.UserPopulator;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;
import java.sql.SQLException;
import org.springframework.beans.factory.annotation.Autowired;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.softserve.if078.tmwSpring")
@SpringBootTest(classes = {H2DbConfig.class})
@EnableConfigurationProperties
public class UserPopulatorTest {
  @Autowired
  UserPopulator populator;

  @Test
  public void checkIdAutoIncremented() throws SQLException {
    // UserPopulator populator = new UserPopulator();
    User user = populator.createDefaultUser();
    Assert.assertNotEquals(-1, user.getId());
  }
}

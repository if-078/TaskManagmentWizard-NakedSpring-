package com.softserve.if078.tmwSpring.services;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.junit4.SpringRunner;

//import com.softserve.if078.tmwSpring.TmwSpringApplicationTests;
import com.softserve.if078.tmwSpring.configurations.H2DbConfig;
import com.softserve.if078.tmwSpring.entities.User;

@RunWith(SpringRunner.class)
@ComponentScan(basePackages = "com.softserve.if078.tmwSpring")
@SpringBootTest(classes = {H2DbConfig.class })
@EnableConfigurationProperties
public class UserServiceIntTest {

	@Autowired
	UserService userService;

	@Test
	public void iTShouldInsertAndGetOneAndDelete() throws SQLException {
		// Given
		User userNew = new User();
		userNew.setName("if-078");
		userNew.setEmail("softServeAcademy@gmail.test");
		userNew.setPass("academypassword");
		User userGetOne;
		// When
		userNew = userService.create(userNew);
		userGetOne = userService.get(userNew.getId());
		boolean isDeleted = userService.delete(userNew.getId());
		// Then
		assertEquals(userNew, userGetOne);
		assertEquals(true, isDeleted);
	}

	@Test
	public void iTShouldInsertAndGetByNamePasswordAndGetAll() throws SQLException {
		// Given
		User userNew = new User();
		userNew.setName("if-078");
		userNew.setEmail("soft@email");
		userNew.setPass("pass");
		User userGetEmailPass;
		// When
		userNew = userService.create(userNew);
		userGetEmailPass = userService.findByEmail(userNew.getEmail());
		int sizeUsers = 1;
		// Then
		assertEquals(userNew, userGetEmailPass);
		assertEquals(sizeUsers, userService.getAll().size());
	}

}

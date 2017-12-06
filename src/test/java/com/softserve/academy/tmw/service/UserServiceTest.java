package com.softserve.academy.tmw.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.softserve.academy.tmw.dao.impl.UserDao;
import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.api.UserServiceInterface;
import com.softserve.academy.tmw.service.impl.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

  @InjectMocks
  private UserServiceInterface userService = new UserService();

  @Mock
  private UserDao userDao;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testUserMockCreation() {
    assertNotNull(userService);
    assertNotNull(userDao);
  }

  @Test
  public void shouldCreateUser() {
    String email = "test@email.com";

    User persistedUser = new User();
    persistedUser.setEmail(email);

//    when(userDao.create(any(User.class))).thenReturn(persistedUser);
//    User newUser = userService.create(persistedUser);
//
//    assertThat(newUser).isNotNull();
//    assertThat(email).isEqualTo(newUser.getEmail());
//
//    verify(userDao).create(newUser);

  }

  @Test
  public void shouldGetUserByEmail() {
    String email = "test@email.com";

    User persistedUser = new User();
    persistedUser.setEmail(email);

    when(userDao.findByEmail(email)).thenReturn(persistedUser);

    User userByEmail = userService.findByEmail(email);

    assertThat(userByEmail).isNotNull();
    assertThat(email).isEqualTo(userByEmail.getEmail());

    verify(userDao).findByEmail(userByEmail.getEmail());

  }

  @Test
  public void shouldDeleteUserById() {
    Integer userId = 5;

    when(userDao.delete(userId)).thenReturn(true);

    boolean isDeleted = userService.delete(userId);

    assertThat(isDeleted).isTrue();

    verify(userDao).delete(userId);
  }

  @Test
  public void shouldUpdateUser() {
    String userName = "SoftServe";
    Integer userId = 5;

    User updatedUser = new User();
    updatedUser.setName(userName);
    updatedUser.setId(userId);

    when(userDao.update(updatedUser)).thenReturn(true);

    boolean isUpdated = userService.update(updatedUser);

    assertThat(isUpdated).isTrue();

    verify(userDao).update(updatedUser);
  }

}

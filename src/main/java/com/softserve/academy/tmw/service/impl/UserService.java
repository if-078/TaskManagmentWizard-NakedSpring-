package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.impl.UserDao;
import com.softserve.academy.tmw.dao.impl.UsersTasksDao;
import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.api.UserServiceInterface;
import java.util.List;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.hashids.Hashids;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserServiceInterface {

  @Autowired
  private UserDao userDao;
  @Autowired
  private UsersTasksDao usersTasksDao;
  @Autowired
  private PasswordEncoder passwordEncoder;
  @Autowired
  private JavaMailSender mailSender;

  @Override
  public List getAll() {
    return userDao.getAll();
  }

  @Override
  public User findOne(int id) {
    User user = userDao.findOne(id);
    if (user.isActivated())
    return user;
    throw new IllegalArgumentException("Un active user");
  }

  @Override
  public boolean update(User user) {
    return userDao.update(user);
  }

  @Override
  public boolean delete(int id) {
    return userDao.delete(id);
  }

  @Override
  public User create(User user) {
    user.setPass(passwordEncoder.encode(user.getPass()));
    User user1 = userDao.create(user);
    Hashids hashids = new Hashids();
    hashids.encode(user1.getId());
    String verifecationEmailMessage =
        "http://18.221.173.209:8585//register/verify/" + hashids.encode(user1.getId());
    sendEmailToUser(user1, verifecationEmailMessage, "Task Management Wizard email verification");
    return user1;
  }

  @Override
  public boolean verify(String key) {
    long id = new Hashids().decode(key)[0];
    return userDao.verifyUser(id);
  }

  @Override
  public User findByEmail(String email) {
    User user = null;
    try {
      user = userDao.findByEmail(email);
    } catch (Exception e) {
      e.getCause();
    }
    return user;
  }

  @Override
  public boolean acceptProjectInvitation(String key){
    long userId, projectId;
    Hashids hashids = new Hashids();
    userId =hashids.decode(key)[0];
    projectId =hashids.decode(key)[1];
    return userDao.attachUserToProject(userId, projectId);
  }

  @Override
  public List<User> getTeamByTask(int taskId, int userId) {
    return usersTasksDao.getTeamByTask(taskId, userId);
  }

  @Override
  public boolean sendEmailToUser(User user, String message, String subject) {
    MimeMessage mimeMessage = mailSender.createMimeMessage();
    try {
      mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(user.getEmail()));
      mimeMessage.setSubject(subject);
      mimeMessage.setContent(message, "text/html");
      mailSender.send(mimeMessage);
      return true;
    } catch (MessagingException e) {
      e.printStackTrace();
      return false;
    }
  }
}
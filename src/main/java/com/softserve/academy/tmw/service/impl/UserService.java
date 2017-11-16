package com.softserve.academy.tmw.service.impl;

import com.softserve.academy.tmw.dao.impl.UserDao;
import com.softserve.academy.tmw.entity.User;
import com.softserve.academy.tmw.service.api.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService implements UserServiceInterface {

    @Autowired
   private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public List getAll() {
        return userDao.getAll();
    }

    @Override
    public User findOne(int id) {
        return userDao.findOne(id);
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
        return userDao.create(user);
    }

    @Override
    public User findByEmail(String email) {
        User user=null;
        try{
            user=userDao.findByEmail(email);
        }catch (Exception e){
            e.getCause();
        }
        return user;
    }

}

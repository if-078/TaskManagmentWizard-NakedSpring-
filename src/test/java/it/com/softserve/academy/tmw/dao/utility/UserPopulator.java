package it.com.softserve.academy.tmw.dao.utility;

import com.softserve.academy.tmw.dao.impl.UserDao;
import com.softserve.academy.tmw.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class UserPopulator {

    private UserDao userDao;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public User createDefaultEntity() {
        User user = new User();
        user.setName("default name");
        user.setPass(passwordEncoder.encode("1111111"));
        user.setEmail("default@email.com");
        user.setId(-1);
        return userDao.create(user);
    }

    public User createCustomUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPass(passwordEncoder.encode("1111111"));
        return userDao.create(user);

    }
}

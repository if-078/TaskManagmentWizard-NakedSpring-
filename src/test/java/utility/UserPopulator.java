package utility;

import com.softserve.academy.dao.implementation.UserDaoImpl;
import com.softserve.academy.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import java.sql.SQLException;


public class UserPopulator {


    private UserDaoImpl userDao;

    @Autowired
    public void setUserDao(UserDaoImpl userDao) {
        this.userDao = userDao;
    }

    public User createDefaultUser() throws SQLException {
        User user = new User();
        user.setName("default name");
        user.setPass("******");
        user.setEmail("default@email.com");
        user.setId(-1);
        return userDao.create(user);
    }

    public User createCustomUser(String name, String email) throws SQLException {
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPass("******");
        return userDao.create(user);
    }
}
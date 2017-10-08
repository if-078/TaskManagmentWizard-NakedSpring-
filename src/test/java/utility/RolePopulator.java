package utility;

import com.softserve.academy.dao.implementation.RoleDao;
import com.softserve.academy.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;


public class RolePopulator implements Populator<Role>{


    private RoleDao dao;

    @Autowired
    public void setDao(RoleDao dao) {
        this.dao = dao;
    }

    @Override
    public Role createDefaultEntity() {
        Role role = new Role();
        role.setId(-1);
        role.setName("WIZARD");
        return dao.create(role);
    }
}
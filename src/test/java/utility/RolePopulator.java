package utility;

import com.softserve.academy.dao.implementation.RoleDao;
import com.softserve.academy.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;


public class RolePopulator {


    private RoleDao dao;

    @Autowired
    public void setDao(RoleDao dao) {
        this.dao = dao;
    }

    public Role createDefaultStatus(){
        Role role = new Role();
        role.setId(-1);
        role.setName("WIZARD");
        return dao.create(role);
    }
}
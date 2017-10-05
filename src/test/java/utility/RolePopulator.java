package utility;

import com.softserve.academy.dao.implementation.RoleDaoImpl;
import com.softserve.academy.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;


public class RolePopulator {


    private RoleDaoImpl dao;

    @Autowired
    public void setDao(RoleDaoImpl dao) {
        this.dao = dao;
    }

    public Role createDefaultStatus(){
        Role role = new Role();
        role.setId(-1);
        role.setName("WIZARD");
        return dao.create(role);
    }
}
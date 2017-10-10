package utility;

import com.softserve.academy.dao.implementation.PriorityDao;
import com.softserve.academy.entity.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;


public class PriorityPopulator implements Populator<Priority>{
    private NamedParameterJdbcTemplate operations;
    private PriorityDao priorityDao;

    @Autowired
    public void setOperations(DataSource dataSource) {
        this.operations = new NamedParameterJdbcTemplate(dataSource);
    }
    @Autowired
    public void setPriorityDao(PriorityDao dao){
        this.priorityDao = dao;
    }

    public void initPriorityTable (){
        initOnePriority("NONE");
        initOnePriority("LOW");
        initOnePriority("NORMAL");
        initOnePriority("HIGH");

    }

    public Priority initOnePriority(String name){
        MapSqlParameterSource param = new MapSqlParameterSource();
        KeyHolder keyHolder = new GeneratedKeyHolder();
        Priority priority = new Priority(-1, name);
        param.addValue("name", name);
        operations.update("INSERT INTO " + "tmw.priority" + " (name) VALUES (:name)", param, keyHolder);
        priority.setId((int) keyHolder.getKey());
        return priority;
    }

    @Override
    public Priority createDefaultEntity() {
        initPriorityTable();
        return priorityDao.findOne(1);
    }
}
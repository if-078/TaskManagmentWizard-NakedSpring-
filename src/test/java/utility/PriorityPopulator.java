package utility;

import com.softserve.academy.entity.Priority;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;


public class PriorityPopulator {
    private NamedParameterJdbcTemplate operations;

    @Autowired
    public void setOperations(DataSource dataSource) {
        this.operations = new NamedParameterJdbcTemplate(dataSource);
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
}
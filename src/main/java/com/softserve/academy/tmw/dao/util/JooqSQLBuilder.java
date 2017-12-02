package com.softserve.academy.tmw.dao.util;

import static org.jooq.impl.DSL.field;
import static org.jooq.impl.DSL.table;
import static org.jooq.impl.DSL.using;

import com.softserve.academy.tmw.dao.util.wrapper.FilterStateWrapper;
import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.jooq.Condition;
import org.jooq.DSLContext;
import org.jooq.Field;
import org.jooq.SQLDialect;
import org.jooq.Select;
import org.jooq.Table;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;

public class JooqSQLBuilder {

  private Settings settings = new Settings().withParamType(ParamType.INLINED);
  private DSLContext creator = using(SQLDialect.MYSQL, settings);
  private Field id = field("task.id");
  private Field name = field("task.name");
  private Field createdDate = field("task.created_date");
  private Field startDate = field("task.start_date");
  private Field endDate = field("task.end_date");
  private Field estimateTime = field("task.estimate_time");
  private Field assignTo = field("task.assign_to");
  private Field statusId = field("task.status_id");
  private Field priorityId = field("task.priority_id");
  private Field parentId = field("task.parent_id");
  private Field planningDate = field("task.planning_date");
  private FilterStateWrapper sto;

  public JooqSQLBuilder(FilterStateWrapper sto) {
    this.sto = sto;
  }

  public Select buildSql() {

    Table table = table("task").
        join(table("priority")).on(priorityId.eq(field("priority.id"))).
        join(table("status")).on(statusId.eq(field("status.id"))).
        join(table("user")).on(assignTo.eq(field("user.id")));
    Condition condition = field(id).in(sto.getIdS());
    Select selectConditionStep;



    if (sto.isPlanned()) condition = condition.and(planningDate.isNotNull());
    else condition = condition.and(planningDate.isNull());

    if (sto.getStatus().length > 0) {

      List<Integer> list = Arrays.stream(sto.getStatus()).boxed().collect(Collectors.toList());
      condition = condition.and(field(statusId).in(list));

    }

    if (sto.getPriority().length > 0) {

      List<Integer> list = Arrays.stream(sto.getPriority()).boxed().collect(Collectors.toList());
      condition = condition.and(field(priorityId).in(list));

    }

    long longDateFrom = Long.parseLong(sto.getDates()[0]);
    long longDateTo = Long.parseLong(sto.getDates()[1]);
    Date dateFrom = new Date(Long.parseLong(sto.getDates()[0]));
    Date dateTo = new Date(Long.parseLong(sto.getDates()[1]));

    if (longDateFrom > 0 && longDateTo > 0) {

      condition = condition.and(field("start_date").between(dateFrom, dateTo));

    }

    if (sto.getTag().length > 0) {

      List<Integer> list = Arrays.stream(sto.getTag()).boxed().collect(Collectors.toList());
      table = table.join(table("tags_tasks")).on(field(id).eq(field("tags_tasks.task_id")))
          .join(table("tag")).on(field("tags_tasks.tag_id").eq(field("tag.id")));
      selectConditionStep = creator.select(id, name, createdDate, startDate, endDate, estimateTime,
          field("user.name").as("user"), field("status.name").as("status"),
          field("priority.name").as("priority"), parentId).from(table)
          .where(condition).and(field("tags_tasks.tag_id").in(list))
          .groupBy(field("task_id"))
          .having(field("tag_id").countDistinct().eq(sto.getTag().length));
    } else {

      selectConditionStep = creator.select(id, name, createdDate, startDate, endDate, estimateTime,
          field("user.name").as("user")
          , field("status.name").as("status"), field("priority.name").as("priority"), parentId)
          .from(table).where(condition);

    }

    return selectConditionStep;
  }

  public static void main(String[] args) {
    FilterStateWrapper wrapper = new FilterStateWrapper();
    int[] status = new int[]{1, 2, 3};
    int[] priority = new int[]{1, 2, 3};
    wrapper.setPriority(priority);
    wrapper.setStatus(status);
    wrapper.setParentId(1);
    wrapper.setDates(new String[]{"0", "0"});
    wrapper.setTag(new int[]{1, 2});
    JooqSQLBuilder builder = new JooqSQLBuilder(wrapper);
    System.out.println(builder.buildSql().getSQL());
  }

}
/*        String query = "SELECT task.id, task.name, task.created_date, task.planning_date, task.start_date,
                    task.end_date, task.estimate_time,\n"
           + "  user.name as user, status.name as status, priority.name as priority, task.parent_id\n"
           + "  FROM task\n"
           + "  LEFT JOIN priority ON task.priority_id = priority.id\n"
           + "  LEFT JOIN status ON task.status_id = status.id\n"
           + "  LEFT JOIN user ON task.assign_to = user.id"
           + "WHERE task.id=:id"; */
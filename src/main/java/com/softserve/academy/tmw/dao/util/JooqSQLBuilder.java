package com.softserve.academy.tmw.dao.util;

import com.softserve.academy.tmw.dao.util.wrapper.FilterStateWrapper;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.*;

public class JooqSQLBuilder {

    private Settings settings = new Settings().withParamType(ParamType.INLINED);
    private DSLContext creator = using(SQLDialect.MYSQL, settings);
    private Field id = field("task.id");
    private Field name = field("task.name");
    private Field createdDate = field("task.created_date");
    private Field planningDate = field("task.planning_date");
    private Field startDate = field("task.start_date");
    private Field endDate = field("task.end_date");
    private Field estimateTime = field("task.estimate_time");
    private Field assignTo = field("task.assign_to");
    private Field statusId = field("task.status_id");
    private Field priorityId = field("priority_id");
    private Field parentId = field("parent_id");
    private FilterStateWrapper sto;

    public JooqSQLBuilder(FilterStateWrapper sto) {
        this.sto = sto;
    }

    public Select buildSql() {

        Table table;
        Condition condition = field(parentId).eq(sto.getId());
        Select selectConditionStep;

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
        Date dateTo = new Date( Long.parseLong(sto.getDates()[1]));

        if (longDateFrom>0 && longDateTo >0){

            condition = condition.and(field("start_date").between(dateFrom, dateTo));

        }

        if (sto.getTag().length > 0) {

            List<Integer> list = Arrays.stream(sto.getTag()).boxed().collect(Collectors.toList());
            table = table("task").join(table("tags_tasks")).on(field(id).eq(field("tags_tasks.task_id")))
                    .join(table("tag")).on(field("tags_tasks.tag_id").eq(field("tag.id")));
            selectConditionStep = creator.select(id, name, createdDate, planningDate, startDate,endDate, estimateTime, assignTo, statusId, priorityId, parentId).from(table)
                    .where(condition).and(field("tags_tasks.tag_id").in(list))
                    .groupBy(field("task_id"))
                    .having(field("tag_id").countDistinct().eq(sto.getTag().length));
        } else {

            table = table("task");
            selectConditionStep = creator.select(id, name, createdDate, planningDate, startDate,endDate, estimateTime, assignTo, statusId, priorityId, parentId)
                    .from(table).where(condition);

        }
        return selectConditionStep;
    }

}
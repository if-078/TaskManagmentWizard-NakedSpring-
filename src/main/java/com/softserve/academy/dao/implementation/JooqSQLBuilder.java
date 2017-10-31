package com.softserve.academy.dao.implementation;

import com.softserve.academy.DTO.FilterStateWrapper;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;

import static org.jooq.impl.DSL.*;

public class JooqSQLBuilder {

    private Settings settings = new Settings().withParamType(ParamType.NAMED);
    private DSLContext creator = using(SQLDialect.MYSQL, settings);
    private Field id = field("task.id");
    private Field name = field("task.name");
    private Field crDate = field("task.created_date");
    private Field startDate = field("task.start_date");
    private Field endDate = field("task.end_date");
    private Field estimateTime = field("task.estimate_time");
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

            condition = condition.and(field(statusId).in(sto.getStatus()));

        }

        if (sto.getPriority().length > 0) {

            condition = condition.and(field(priorityId).in(sto.getPriority()));

        }

        if (sto.getTag().length > 0) {

            table = table("task").join(table("tags_tasks")).on(field(id).eq(field("tags_tasks.task_id")))
                    .join(table("tag")).on(field("tags_tasks.tag_id").eq(field("tag.id")));
            selectConditionStep = creator.select(id, name, crDate, startDate, endDate, estimateTime, statusId).from(table).where(condition).and(field("tags_tasks.tag_id").in(sto.getTag())).groupBy(field("task_id"))
                    .having(field("tag_id").countDistinct().eq(sto.getTag().length));
        } else {

            table = table("task");
            selectConditionStep = creator.select(id, name, crDate, startDate, endDate, estimateTime, statusId).from(table).where(condition);

        }

        return selectConditionStep;
    }

}

/*
select task.id, task.name, status.name, priority_id from task
join tags_tasks on task.id = tags_tasks.task_id
join tag on tags_tasks.tag_id = tag.id
where tag.name in ('tag1' , 'tag2')group by task_id having count(distinct tag_id) =2 and status.name = 'to do';*/
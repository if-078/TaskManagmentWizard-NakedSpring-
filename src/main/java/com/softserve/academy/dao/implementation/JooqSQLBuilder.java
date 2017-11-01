package com.softserve.academy.dao.implementation;

import com.softserve.academy.DTO.FilterStateWrapper;
import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.jooq.impl.DSL.*;

public class JooqSQLBuilder {

    private Settings settings = new Settings().withParamType(ParamType.INLINED);
    private DSLContext creator = using(SQLDialect.MYSQL, settings);
    private Field id = field("task.id");
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
//
        }

        if (sto.getPriority().length > 0) {

            List<Integer> list = Arrays.stream(sto.getPriority()).boxed().collect(Collectors.toList());
            condition = condition.and(field(priorityId).in(list));

        }

        if (sto.getTag().length > 0) {

            List<Integer> list = Arrays.stream(sto.getTag()).boxed().collect(Collectors.toList());
            table = table("task").join(table("tags_tasks")).on(field(id).eq(field("tags_tasks.task_id")))
                    .join(table("tag")).on(field("tags_tasks.tag_id").eq(field("tag.id")));
            selectConditionStep = creator.select().from(table)
                    .where(condition).and(field("tags_tasks.tag_id").in(list))
                    .groupBy(field("task_id"))
                    .having(field("tag_id").countDistinct().eq(sto.getTag().length));
        } else {

            table = table("task");
            selectConditionStep = creator.select().from(table).where(condition);

        }

        return selectConditionStep;
    }

}

/*
select task.id, task.name, status.name, priority_id from task
join tags_tasks on task.id = tags_tasks.task_id
join tag on tags_tasks.tag_id = tag.id
where tag.name in ('tag1' , 'tag2')group by task_id having count(distinct tag_id) =2 and status.name = 'to do';*/
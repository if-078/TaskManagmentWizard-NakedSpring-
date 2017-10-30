package com.softserve.academy.dao.implementation;

import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;

import java.util.Iterator;
import java.util.Map;

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

    public Select buildSql(int[] priorities, int[] statuses, int[] tags, int userId) {

        Table table;
        Condition condition = field("assign_to").eq(userId);
        Select selectConditionStep;

        if (statuses.length > 0) {

            condition = condition.and(field("status_id").in(statuses));

        }

        if (priorities.length > 0) {

            condition = condition.and(field("priority_id").in(priorities));

        }

        if (tags.length > 1) {

            table = table("task").join(table("tags_tasks")).on(field("task.id").eq(field("tags_tasks.task_id")))
                    .join(table("tag")).on(field("tags_tasks.tag_id").eq(field("tag.id")));
            selectConditionStep = creator.select(id, name, crDate, startDate, endDate, estimateTime, statusId).from(table).where(condition).and(field("tags_tasks.tag_id").in(tags)).groupBy(field("task_id"))
                    .having(field("tag_id").countDistinct().eq(tags.length));
        } else {

            table = table("task");
            selectConditionStep = creator.select(id, name, crDate, startDate, endDate, estimateTime, statusId).from(table).where(condition);

        }

        return selectConditionStep;
    }

    public static void main(String[] args) {
        JooqSQLBuilder builder = new JooqSQLBuilder();
        int[] statuses = new int[]{1,3,4};
        int[] priorities = new int[]{};
        int[] tags = new int[]{9, 6, 34, 52};
        Select sql = builder.buildSql(priorities, statuses, tags, 1);
        Map<String, Param<?>> valuse = sql.getParams();
        Iterator<Map.Entry<String, Param<?>>> iterator = valuse.entrySet().iterator();
        System.out.println(sql.getSQL());
        while (iterator.hasNext()) {
            Map.Entry<String, Param<?>> pair = iterator.next();
            System.out.println(pair.getKey() + " : " + pair.getValue().getName() + " ");
        }


    }
}

/*
select task.id, task.name, status.name, priority_id from task
join tags_tasks on task.id = tags_tasks.task_id
join tag on tags_tasks.tag_id = tag.id
where tag.name in ('tag1' , 'tag2')group by task_id having count(distinct tag_id) =2 and status.name = 'to do';*/
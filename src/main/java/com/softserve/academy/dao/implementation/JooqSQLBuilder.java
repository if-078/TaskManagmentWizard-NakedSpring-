package com.softserve.academy.dao.implementation;

import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;

import java.util.*;

import static org.jooq.impl.DSL.*;

public class JooqSQLBuilder {
    Settings settings = new Settings().withParamType(ParamType.NAMED);
    DSLContext creator = using(SQLDialect.MYSQL, settings);
    Field id = field("task.id");
    Field name = field("task.name");
    Field crDate = field("task.created_date");
    Field startDate = field("task.start_date");
    Field endDate = field("task.end_date");
    Field estimateTime = field("task.estimate_time");
    Field statusId = field("task.status_id");

    public Select buildSql (int[] priorities, int[] statuses, int[] tags, int userId) {
        Table table;
        Condition condition = field("assign_to").eq(userId);
        Select selectConditionStep;
        if (statuses.length > 0 && priorities.length == 0) {
            condition = and(field("status_id").in(statuses));


        } else if (statuses.length == 0 && priorities.length > 0) {
            condition = and(field("priority_id").in(priorities));


        } else if (statuses.length > 0 && priorities.length > 0) {
            condition = and(field("status_id").in(statuses).and(field("priority_id").in(priorities)));

        }

        if (tags.length > 1) {

            table = table("task").join(table("tags_tasks")).on(field("task.id").eq(field("tags_tasks.task_id")))
                    .join(table("tag")).on(field("tags_tasks.tag_id").eq(field("tag.id")));
            selectConditionStep = creator.select(id, name, crDate, startDate, endDate, estimateTime, statusId).from(table).where(condition).and(field("tags_tasks.tag_id").in(tags)).groupBy(field("task_id"))
                    .having(field("tag_id").countDistinct().eq(tags.length));
        } else {
            table = table("task");
            selectConditionStep =creator.select(id, name, crDate, startDate, endDate, estimateTime, statusId).from(table).where(condition);

        }
        return selectConditionStep;
    }

    public Map getMappedValues (int[] priorities, int[] statuses, int[] tags, int userId){
        Map<String, List> map = new HashMap();
        map.put(":1", Arrays.asList(userId));
        if (tags.length == 0 && priorities.length > 0 && statuses.length > 0) {
            map.put(":2", Arrays.asList(statuses));
            map.put(":3", Arrays.asList(priorities));
        }

        map.put(":4", Arrays.asList(tags));
        map.put(":5", Arrays.asList(tags.length));
        return map;
    }

    public static void main(String[] args) {
        JooqSQLBuilder builder = new JooqSQLBuilder();
        int [] statuses = new int[]{};
        int [] priorities = new int[] {1,2,3};
        int [] tags = new int[]{9,6,34, 52};
        Select sql = builder.buildSql(priorities, statuses, tags, 1);
        Map<String, Param<?>> valuse =  sql.getParams();
        Iterator<Map.Entry<String, Param<?>>> iterator = valuse.entrySet().iterator();
        System.out.println(sql.getSQL());
        while (iterator.hasNext()){
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
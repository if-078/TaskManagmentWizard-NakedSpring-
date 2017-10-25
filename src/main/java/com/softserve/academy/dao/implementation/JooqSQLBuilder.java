package com.softserve.academy.dao.implementation;

import org.jooq.*;
import org.jooq.conf.ParamType;
import org.jooq.conf.Settings;

import static org.jooq.impl.DSL.*;

public class JooqSQLBuilder {
    Settings s = new Settings().withParamType(ParamType.NAMED);
    DSLContext creator = using(SQLDialect.MYSQL, s);
    Field id = field("task.id");
    Field name = field("task.name");
    Field crDate = field("task.created_date");
    Field startDate = field("task.start_date");
    Field endDate = field("task.end_date");
    Field estimateTime = field("task.estimate_time");
    Field statusId = field("task.status_id");

    public SelectJoinStep initialCondition (int id){
        SelectJoinStep s = select(name, crDate, startDate, endDate, estimateTime).from("task");
        return s;
    }

    public SelectOnConditionStep addTagsJoin(SelectJoinStep selectSelectStep){
        SelectOnConditionStep step = selectSelectStep.join("tags_tasks")
                .on(field(id).eq(field("tags_tasks.task_id")));
        return step;
    }

    public Condition addPriorityCondition(String[] priorities, Condition condition){
        for (String s : priorities){
            condition = condition.and(field("PRIORITY.NAME").eq(s));
        }
        return condition;
    }

    public static void main(String[] args) {
        JooqSQLBuilder builder = new JooqSQLBuilder();
        System.out.println(builder.initialCondition(2).getSQL());

    }
}
/* select task_id from tags_tasks where tag_id in(1,3)
group by task_id having count(distinct tag_id) = 2;
select task.id, task.name, status.name, priority_id from task
join tags_tasks on task.id = tags_tasks.task_id
join tag on tags_tasks.tag_id = tag.id
join status on status.id = task.status_id
where tag.name in ('tag1' , 'tag2')group by task_id having count(distinct tag_id) =2 and status.name = 'to do';*/
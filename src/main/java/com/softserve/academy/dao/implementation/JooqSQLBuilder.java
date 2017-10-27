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

    public SelectJoinStep initialCondition (){
        SelectJoinStep s = select(name, crDate, startDate, endDate, estimateTime).from("task");
        return s;
    }

    public SelectOnConditionStep addTagsJoin(SelectJoinStep selectSelectStep){
        SelectOnConditionStep step = selectSelectStep.join("tags_tasks")
                .on(field(id).eq(field("tags_tasks.task_id")));
        return step;
    }

//    public Condition addPriorityCondition(int [] priorities){
//
//            Condition condition = field("priority_id").eq(priorities[0]);
//
//        for (int i = 1; i < priorities.length; i++) {
//            int p = priorities[i];
//            condition = condition.and(field("priority_id").eq(p));
//        }
//        return condition;
//    }

    public String buildSql (int[] priorities, int[] statuses, int[] tags, int UserId){
        SelectField selectField = id.concat(name, crDate, startDate, endDate, estimateTime);
        Table table = table("task").join(table("tags_task")).on(field("task.id").eq(field("tags_tasks.task_id")))
                .join(table("tag")).on(field("tags_tasks.tag_id").eq(field("tag.id")));
        return select(selectField).from(table).where(field("tags_task.tag_id").in(tags)).groupBy(field("task_id"))
                .having(field("tag_id").countDistinct().eq(tags.length))
                .and(field("priority_id").in(priorities)).and(field("status_id").in(statuses)).getSQL();

    }

//    public SelectConditionStep addWhereClause (SelectJoinStep selectJoinStep, int id){
//        SelectConditionStep selectConditionStep = selectJoinStep.where(field("assign_to").eq(id)).and(addPriorityCondition(new int[]{1, 2,3}));
//        return selectConditionStep;
//    }

//    public SelectConditionStep buildTagCondition (String [] tags){
//        SelectConditionStep selectConditionStep = field("tag.name").in(tags);
//    }

    public static void main(String[] args) {
        JooqSQLBuilder builder = new JooqSQLBuilder();
        int [] statuses = new int[]{1, 2};
        int [] priorities = new int[] {2};
        int [] tags = new int[]{1,2,3,4};
        System.out.println(builder.buildSql(priorities, statuses, tags, 1));
    }
}
/*
select task.id, task.name, status.name, priority_id from task
join tags_tasks on task.id = tags_tasks.task_id
join tag on tags_tasks.tag_id = tag.id
where tag.name in ('tag1' , 'tag2')group by task_id having count(distinct tag_id) =2 and status.name = 'to do';*/
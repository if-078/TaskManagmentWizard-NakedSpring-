package com.softserve.academy.dao.implementation;

import org.jooq.*;

import static org.jooq.impl.DSL.*;

public class JooqSQLBuilder {
    DSLContext creator = using(SQLDialect.MYSQL);

    public Condition initialCondition (int id){
        Condition condition = (field("USER.ID").eq(id));
        return condition;
    }

    public Condition addStatusCondition(String[] statuses, Condition condition){
        for (String s : statuses) {
            condition = condition.and(field("STATUS.NAME").eq(s));
        }
        return condition;
    }

    public Condition addPriorityCondition(String[] priorities, Condition condition){
        for (String s : priorities){
            condition = condition.and(field("PRIORITY.NAME").eq(s));
        }
        return condition;
    }

    public static void main(String[] args) {
        String[] statuses = new String[]{"st1", "st2", "st3"};
        String[] priorities = new String[]{"P1", "p2"};
        JooqSQLBuilder builder = new JooqSQLBuilder();
        Condition condition = builder.initialCondition(2);
        condition = builder.addPriorityCondition(priorities, condition);
        condition = builder.addStatusCondition(statuses, condition);
        String res =  builder.creator.select(field("TASK.ID")).from("Task").join("Users").on(field("USER.ID").eq("USER_ID")).where(condition).getSQL();

        System.out.println(res);
    }
}
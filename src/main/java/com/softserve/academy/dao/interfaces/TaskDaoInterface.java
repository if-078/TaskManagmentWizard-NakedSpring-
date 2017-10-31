package com.softserve.academy.dao.interfaces;

import com.softserve.academy.dao.implementation.JooqSQLBuilder;
import com.softserve.academy.entity.Comment;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.Task;

import java.util.List;

public interface TaskDaoInterface extends EntityDaoInterface<Task> {

    List<Task> getTasksForToday();

    List<Tag> getTagsOfTask(int taskId);

    List<Comment> getCommentsOfTask(int taskId);

    List<Task> getSubTasks(int id);

    List<Task> getTasksByTag(int tagId);

    List<Task> getFilteredTasks(JooqSQLBuilder builder);
}

package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.dao.util.JooqSQLBuilder;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.entity.Task;
import java.util.List;

public interface TaskDaoInterface extends EntityDaoInterface<Task>{

    List<Task> getTasksForToday();

    List<Tag> getTagsOfTask(int taskId);

    List<Comment> getCommentsOfTask(int taskId);

    List<Task> getSubtasks(int id);

    List<Task> getPageOfTasks(int page, int amount);

    List<Task> getSprint();

    List<Task> getTasksAssignToUser(int userId);

    List<Task> getFilteredTasks(JooqSQLBuilder builder);

    List<Task> getPlannedTasks();

}
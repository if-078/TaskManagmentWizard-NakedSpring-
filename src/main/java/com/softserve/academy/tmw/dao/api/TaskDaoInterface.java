
package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.dao.util.JooqSQLBuilder;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.entity.Task;
import java.util.List;

public interface TaskDaoInterface extends EntityDaoInterface<Task>{

    List<Tag> getTagsOfTask(int taskId);

    List<Comment> getCommentsOfTask(int taskId);

    List<Task> getSubtasks(int id);

    List<Task> getTasksAssignToUser(int userId);

    List<Task> getFilteredTasks(JooqSQLBuilder builder);

    List<Task> getPlannedTasks();

}
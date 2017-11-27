
package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.dao.util.JooqSQLBuilder;
import com.softserve.academy.tmw.dto.TaskTableDTO;
import com.softserve.academy.tmw.dto.TaskTreeDTO;
import com.softserve.academy.tmw.entity.Comment;
import com.softserve.academy.tmw.entity.Tag;
import com.softserve.academy.tmw.entity.Task;
import java.util.List;

public interface TaskDaoInterface extends EntityDaoInterface<Task>{

    List<Tag> getTagsOfTask(int taskId);

    List<Comment> getCommentsOfTask(int taskId);

    List<Task> getSubtasks(int id);

    List<Task> getTasksAssignToUser(int userId);

    List<TaskTableDTO> getFilteredTasks(JooqSQLBuilder builder);

    List<Task> getPlannedTasks();

    List<TaskTreeDTO> findTaskByTree(int id, int userId);

}
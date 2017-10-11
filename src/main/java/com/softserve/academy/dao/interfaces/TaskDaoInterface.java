package com.softserve.academy.dao.interfaces;

import com.softserve.academy.entity.Comment;
import com.softserve.academy.entity.Tag;
import com.softserve.academy.entity.Task;

import java.util.List;

public interface TaskDaoInterface extends EntityDao<Task>{

    public List<Task> getTasksForToday();

    public List<Tag> getTagsOfTask(int taskId);

    public List<Comment> getCommentsOfTask(int taskId);

    public List<Task> getSubtasks(int id);

}

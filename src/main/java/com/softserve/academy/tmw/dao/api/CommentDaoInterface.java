package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.entity.Comment;

import java.util.List;

public interface CommentDaoInterface extends EntityDaoInterface<Comment> {

  boolean deleteCommentsOfTask(int taskId);

  boolean setCommentsToTask(List<Comment> comments, int taskId);

  List<Comment> getCommentsByTaskId(int taskId);

}

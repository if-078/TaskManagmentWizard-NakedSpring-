package com.softserve.academy.tmw.service.api;

import com.softserve.academy.tmw.entity.Comment;

import java.util.List;

public interface CommentServiceInterface extends EntityServiceInterface<Comment>{

    boolean setCommentsToTask(List<Comment> commentList);

    boolean deleteCommentsOfTask(int taskId);

    List<Comment> getCommentsByTaskId (int taskId);
}

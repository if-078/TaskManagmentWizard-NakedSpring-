package com.softserve.academy.tmw.service.api;

import com.softserve.academy.tmw.entity.Tag;
import java.util.List;

public interface TagServiceInterface extends EntityServiceInterface<Tag> {

  List<Tag> getAllByUserId(int userId);

  List<Tag> getAllByTaskId(int taskId);

  boolean deleteAllByUserId(int userId);

  boolean deleteTagsOfTask(int taskId);

  boolean setTagsToTask(List<Tag> tags, int taskId);
}

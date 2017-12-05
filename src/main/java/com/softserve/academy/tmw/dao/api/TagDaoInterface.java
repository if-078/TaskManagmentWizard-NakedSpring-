package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.entity.Tag;
import java.util.List;

public interface TagDaoInterface extends EntityDaoInterface<Tag> {

  List<Tag> getAllByProject(int projectId);

  List<Tag> getAllByTaskId(int taskId);

  boolean deleteAllByProject(int projectId);

  boolean deleteTagsOfTask(int taskId);

  boolean setTagsToTask(List<Tag> tags, int taskId);

}

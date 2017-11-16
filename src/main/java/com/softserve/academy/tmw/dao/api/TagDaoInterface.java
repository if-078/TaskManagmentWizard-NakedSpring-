package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.entity.Tag;
import java.util.List;

public interface TagDaoInterface extends EntityDaoInterface<Tag> {
    List<Tag> getAllByUserId(int userId);
    List<Tag> getAllByTaskId(int taskId);
    boolean deleteAllByUserId(int userId);
    boolean deleteTagsOfTask(int taskId);
    boolean setTagsToTask(List<Integer> tags, int taskId);


}

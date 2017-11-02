package com.softserve.academy.tmw.dao.api;

import com.softserve.academy.tmw.entity.Tag;

import java.util.List;

public interface TagDaoInterface extends EntityDaoInterface<Tag> {
    List<Tag> getAllByUserId(int userId);

    boolean deleteAllByUserId(int userId);

}

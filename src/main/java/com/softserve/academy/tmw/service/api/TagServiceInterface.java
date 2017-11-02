package com.softserve.academy.tmw.service.api;

import com.softserve.academy.tmw.entity.Tag;

import java.util.List;

public interface TagServiceInterface extends EntityServiceInterface<Tag> {

    List<Tag> getAllByUserId(int userId);

    boolean deleleAllByUserId(int userId);
}

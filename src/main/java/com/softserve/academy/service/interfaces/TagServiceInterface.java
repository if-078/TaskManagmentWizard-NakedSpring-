package com.softserve.academy.service.interfaces;

import com.softserve.academy.entity.Tag;
import java.util.List;

public interface TagServiceInterface extends EntityServiceInterface<Tag> {

  List<Tag> getAllByUserId(int userId);

  boolean deleleAllByUserId(int userId);
}

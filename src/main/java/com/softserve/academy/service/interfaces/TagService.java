/*
 * To change this license header, choose License Headers in Project Properties. To change this
 * template file, choose Tools | Templates and open the template in the editor.
 */
package com.softserve.academy.service.interfaces;

import com.softserve.academy.entity.Tag;
import java.util.List;

/**
 *
 * @author Oleg
 */
public interface TagService extends EntityService<Tag> {

  List<Tag> getAllByUserId(int userId);

  boolean deleleAllByUserId(int userId);
}

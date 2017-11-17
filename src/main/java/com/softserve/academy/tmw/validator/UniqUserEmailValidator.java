package com.softserve.academy.tmw.validator;

import com.softserve.academy.tmw.service.api.UserServiceInterface;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UniqUserEmailValidator implements ConstraintValidator<UniqUserEmail, String> {

  @Autowired
  UserServiceInterface userService;

  int equal;

  @Override
  public void initialize(UniqUserEmail userEmail) {
  }

  @Override
  public boolean isValid(String userEmail, ConstraintValidatorContext context) {
    equal = 0;
    if (userEmail == null) {
      return false;
    }
    userService.getAll().stream().forEach(user -> {
      if (user.getEmail().equals(userEmail)) {
        equal++;
        return;
      }
    });

    return equal == 0;
  }

}

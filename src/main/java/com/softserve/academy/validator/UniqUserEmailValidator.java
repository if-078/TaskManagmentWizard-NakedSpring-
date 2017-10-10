package com.softserve.academy.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.softserve.academy.service.interfaces.UserService;

@Component
public class UniqUserEmailValidator implements ConstraintValidator <UniqUserEmail , String> {
	
	@Autowired
	UserService userService;
	
	int equal = 0;

	@Override
    public void initialize(UniqUserEmail userEmail) { }
	
	@Override
	public boolean isValid(String userEmail, ConstraintValidatorContext context) {
		
		if(userEmail == null) {
			return false;
		}
		
		userService.getAll().stream().forEach(user -> {if(user.getEmail().equals(userEmail)){equal++;}});
		
		return equal == 0;
	}

}

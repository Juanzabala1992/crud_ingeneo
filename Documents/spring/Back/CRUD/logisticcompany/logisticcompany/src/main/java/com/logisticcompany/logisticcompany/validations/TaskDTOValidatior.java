package com.logisticcompany.logisticcompany.validations;


import java.util.Arrays;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class TaskDTOValidatior implements ConstraintValidator<ValidTaskDTO, String>{
	
	List<String> tipo = Arrays.asList("C", "P");

	@Override
	public void initialize (ValidTaskDTO constraintAnnotation) {
		
	}
	
	@Override
	public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
		
		return tipo.contains(value); 
	}
}

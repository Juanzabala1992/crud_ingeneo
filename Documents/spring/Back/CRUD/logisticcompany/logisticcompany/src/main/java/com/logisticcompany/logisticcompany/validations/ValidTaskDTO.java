package com.logisticcompany.logisticcompany.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.RetentionPolicy;
import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy= {TaskDTOValidatior.class})
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidTaskDTO {
	String message() default "Tipo de documento no valido";
	Class<?>[]groups()default{};
	Class<?extends Payload>[] payload() default{};
}

package com.castrillon.validation;


import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanWrapperImpl;

import java.lang.reflect.InvocationTargetException;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SamePropertiesValidator implements ConstraintValidator<SameProperties, Object> {
	
	private static final Logger logger = LoggerFactory.getLogger(SamePropertiesValidator.class);
	private String property1;
	private String property2;
	
	@Override
	public void initialize(SameProperties constraintAnnotation) {
		this.property1 = constraintAnnotation.property1();
		this.property2 = constraintAnnotation.property2();
	}
	
	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		if(object == null) {
			return true;
		}
//		try {
		
		Object value1 = new BeanWrapperImpl(object).getPropertyValue(property1);
		Object value2 = new BeanWrapperImpl(object).getPropertyValue(property2);
		boolean isValid = (value1 != null && value1.equals(value2)) || (value1 == null && value2 == null);
		if(!isValid) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
				.addPropertyNode(property1)
				.addConstraintViolation();
			context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
				.addPropertyNode(property2)
				.addConstraintViolation();
		}
		return isValid;
		
//		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
//			logger.error("An exception has be caught in the same properties validator???????????????" + e.getMessage());
//			return false;
//		}
	}
	
//	private String capitalize(String s) {
//		if(s == null | s.isEmpty()) {
//			return s;
//		}
//		
//		return s.substring(0,1).toUpperCase() + s.substring(1);
// 
//	}

	public SamePropertiesValidator() {
		
		// TODO Auto-generated constructor stub
	}

}
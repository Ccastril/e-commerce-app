package com.castrillon.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SamePropertiesValidator.class)
@Documented
public @interface SameProperties {
	String message() default "Properties must be the same";
	Class<?>[] groups() default {};
	Class<? extends Payload>[] payload() default{};
	String property1();
	String property2();
}

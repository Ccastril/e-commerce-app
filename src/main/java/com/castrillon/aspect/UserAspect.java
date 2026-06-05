package com.castrillon.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class UserAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(UserAspect.class);
	public UserAspect() {
		// TODO Auto-generated constructor stub
	}
	
	@Before("execution(* com.castrillon.controller.HomeController.home(..))")
	public void logHomePageFetch() {
		logger.info("Fetching Home Page");
	}
	
	@After("execution(* com.castrillon.controller.HomeContoller.home(..))")
	public void logHomePageLoad() {
		logger.info("Home Page Loaded");
	}
	
	@Before("execution(* com.castrillon.controller.RegistrationController.getRegister(..))")
	public void registerPageFetch() {
		logger.info("Fetching Registration Page");
		
	}
	
	@After("execution(* com.castrillon.controller.RegistrationController.registerUser(..))")
	public void registerUser() {
		logger.info("Attempting to register user");
	}
	
	@AfterThrowing(pointcut="execution(* com.castrillon.controller.RegistrationController.registerUser(..))", throwing ="e")
	public void logErrorInRegisterUser(JoinPoint joinPoint, Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		String className = joinPoint.getTarget().getClass().getSimpleName();
		
		logger.error("Exception occured in {}.{}(): {}", className, methodName, ex.getMessage(), ex);
	}

			
}


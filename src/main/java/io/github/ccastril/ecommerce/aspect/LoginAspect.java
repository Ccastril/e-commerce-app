package io.github.ccastril.ecommerce.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component //this is required to make it a bean
public class LoginAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(LoginAspect.class);
	 
	public LoginAspect() {
		// TODO Auto-generated constructor stub
	}
	
	@Before("execution(* io.github.ccastril.ecommerce.ECommerceDemo.controller.LoginController.login(..))")
	public void logLogin() {
		logger.info("A User had logged in");
	}

}

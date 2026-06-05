package com.castrillon.aspect;

import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.castrillon.exception.RegistrationException;
import com.castrillon.exception.ErrorInfo;
import com.castrillon.template.AccountTemplate;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class ViewExceptionHandlerAdvice {

	@Autowired
	Environment environment;
	private static final Logger LOGGER = LoggerFactory.getLogger(ViewExceptionHandlerAdvice.class);

	@ExceptionHandler({
		Exception.class,
		MethodArgumentNotValidException.class,
		ConstraintViolationException.class
	})
	public ModelAndView validationExceptionHandler(Exception exception) {

		ModelAndView modelAndView = new ModelAndView("register");
		String errorMessage;
		if(exception instanceof MethodArgumentNotValidException) {
			MethodArgumentNotValidException mException = (MethodArgumentNotValidException) exception;
			errorMessage = mException.getBindingResult().getAllErrors().stream().map(ObjectError::getDefaultMessage)
					.collect(Collectors.joining(", "));

		} else {
			ConstraintViolationException cvException = (ConstraintViolationException) exception;
			errorMessage = cvException.getConstraintViolations().stream().map(ConstraintViolation::getMessage)
					.collect(Collectors.joining(", "));
		}
		LOGGER.error("THIS IS THE ERROR MESSAGE AS FORMATTED " + errorMessage);
		LOGGER.error("THIS IS THE PROPERRTY " + environment.getProperty(errorMessage));
		ErrorInfo errorInfo = new ErrorInfo(environment.getProperty(errorMessage), exception.getClass().toString(), HttpStatus.BAD_REQUEST.value());
		modelAndView.addObject("accTemp", new AccountTemplate("","","",""));
		modelAndView.addObject("errorInfo", errorInfo);
		return modelAndView;

	}
	@ExceptionHandler(RegistrationException.class)
	public ModelAndView registrationExceptionHandler(RegistrationException exception) {
		System.out.println("this exception handler should bem callleddddddddddd");
		LOGGER.error("this exception handler should bem callleddddddddddd " + exception.getMessage());
		ModelAndView modelAndView = new ModelAndView("register");
		String errorMessage = exception.getMessage();
		LOGGER.info("THIS IS THE MESSAGE FROM ENV :" + environment.getProperty(errorMessage));
		ErrorInfo errorInfo = new ErrorInfo(environment.getProperty(errorMessage), exception.getClass().toString(), HttpStatus.BAD_REQUEST.value());
		modelAndView.addObject("accTemp", new AccountTemplate("","","",""));
		modelAndView.addObject("errorInfo", errorInfo);
		return modelAndView;

	}
}

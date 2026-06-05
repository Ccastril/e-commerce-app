package com.castrillon.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.servlet.ModelAndView;

import com.castrillon.exception.RegistrationException;
import com.castrillon.service.RegistrationService;
import com.castrillon.template.AccountTemplate;
import jakarta.validation.Valid;

//import org.springframework.web.bind.annotation.GetMapping
//import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class RegistrationController {

	@Autowired
	private RegistrationService registrationService;
	//this is used to fetch the view for registration
//	@GetMapping(value="/register")
//	public ModelAndView register(Model model) {
//		return new ModelAndView(register);
//	}
	@GetMapping("/register")
	public String getRegister(Model model) {
		model.addAttribute("accTemp", new AccountTemplate("","","",""));
		return "register";
	}
	@PostMapping("/register")
	public String registerUser(@Valid @ModelAttribute AccountTemplate accTemp, Model model) throws RegistrationException {

			registrationService.registerAccount(accTemp);
			model.addAttribute("submittedUser", accTemp);

			return "registerResult";
	}


 }

package com.castrillon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CartController {
	
	@GetMapping("/cart")
	public String cart() {
		return "cart";
	}
	public CartController() {
		// TODO Auto-generated constructor stub
	}

}

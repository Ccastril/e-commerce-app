package io.github.ccastril.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import io.github.ccastril.ecommerce.security.AccountDetails;
import io.github.ccastril.ecommerce.service.ProductService;
import io.github.ccastril.ecommerce.template.ProductTemplate;


@Controller
public class HomeController {

	/*
	 * @AuthenticationPrincipal(expression="account") tries to get an Account and assign it to the parameter, which must be an Account.
	 * If you are using the AccountDetailsPrincipal you only need @AuthenticationPrincipal AccountDetails userDetails
	 * if you want to use the Account, use @AuthenticationPrincipal(expression="account") Account userDetails
	 *
	 */
	@Autowired
	private ProductService productService;
	@GetMapping("/home")
	public String home(@AuthenticationPrincipal AccountDetails userDetails, Model model) {

		if(userDetails != null) {
			model.addAttribute("currentUser", userDetails.getUsername());
		}
//
		try {
			List<ProductTemplate> products = productService.getAllProducts();

			model.addAttribute("products", products);
			//if there are no active carts then create a new one
			System.out.println("FOUND PRODUCTS " + products.size());
			return "home";
		} catch(Exception e) {
			System.out.println("Exception " + e.toString());
		}

		return "login";

	}
	public HomeController() {
		// TODO Auto-generated constructor stub
	}

}

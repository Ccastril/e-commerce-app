package io.github.ccastril.ecommerce;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.github.ccastril.ecommerce.entity.Account;
import io.github.ccastril.ecommerce.entity.Cart;
import io.github.ccastril.ecommerce.entity.CartItem;
import io.github.ccastril.ecommerce.entity.CartStatus;
import io.github.ccastril.ecommerce.entity.Product;
import io.github.ccastril.ecommerce.exception.RegistrationException;
import io.github.ccastril.ecommerce.repository.AccountRepository;
import io.github.ccastril.ecommerce.repository.CartItemRepository;
import io.github.ccastril.ecommerce.repository.CartRepository;
import io.github.ccastril.ecommerce.service.ProductService;
import io.github.ccastril.ecommerce.service.RegistrationService;
import io.github.ccastril.ecommerce.template.AccountTemplate;

@SpringBootApplication
public class ECommerceAppApplication implements CommandLineRunner {
	
	@Autowired
	private ProductService productService;
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private CartItemRepository cartItemRepo;
	@Autowired
	private CartRepository cartRepo;
	@Autowired
	private AccountRepository accountRepo; 
	

	public static void main(String[] args) {
		SpringApplication.run(ECommerceAppApplication.class, args);
	}
	
public void run(String... args) throws Exception {
		
		productService.insertAllProducts();
		
		
		Set<String> names = nameGenerator(100);;
		Map<String, String> namesAndEmails = (HashMap<String, String>) emailGenerator(names);
		Set<AccountTemplate> newAccountTemplates = new HashSet<>();
		//create new accounts
		for(Map.Entry<String, String> e : namesAndEmails.entrySet()) {
			newAccountTemplates.add(new AccountTemplate(0L, e.getKey(), "Password123", "Password123", e.getValue(), new HashSet<>(), new HashSet<>(), new HashSet<>()));
		}
		
		Set<Account> newAccounts = new HashSet<>();
		
		//register new acount
		newAccountTemplates.forEach(a -> {
			try {
				newAccounts.add(registrationService.registerAccount(a));
			} catch (RegistrationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		newAccounts.forEach(a -> {
			Set<Cart> newCarts = createCarts(a, productService, cartRepo, cartItemRepo);
			a.setCart(newCarts);
			accountRepo.save(a);
			
		});
		
		System.out.println("newAccounts final size is " + newAccounts.size());
		
	}
	
	public static Set<Cart> createCarts(Account a, ProductService productService, CartRepository cartRepo, CartItemRepository cartItemRepo) {
		System.out.println("in Create Carts");
		Cart activeCart = new Cart();
		Cart abandondedCart = new Cart();
		Cart checkedOutCart = new Cart();
		try {
			activeCart = populateCart(activeCart, productService, cartItemRepo);
			activeCart.setStatus(CartStatus.ACTIVE);
			activeCart.setAccount(a);
			abandondedCart = populateCart(abandondedCart, productService, cartItemRepo);
			abandondedCart.setStatus(CartStatus.ABANDONDED);
			abandondedCart.setAccount(a);
			checkedOutCart = populateCart(checkedOutCart, productService, cartItemRepo);
			checkedOutCart.setStatus(CartStatus.CHECKED_OUT);
			checkedOutCart.setAccount(a);
			cartRepo.save(activeCart);
			cartRepo.save(abandondedCart);
			cartRepo.save(checkedOutCart);
		} catch(Exception e) {
			System.out.println(e.toString());
			System.out.println("Error populating carts");
		}
	
		
		return Set.of(activeCart, abandondedCart, checkedOutCart);
		//create more accounts
		// add carts to accounts
	}
	
	
	
	public static Cart populateCart(Cart newCart,ProductService productService, CartItemRepository cartItemRepo) throws Exception {
		System.out.println("in populate cart======================================================================");
		Random random = new Random();
		Set<Product> products = new HashSet<>();
		for(int i = 0; i <= random.nextInt(1,10); i++) {
			try {
				Long id = random.nextLong(1,10);
				Product p = productService.getProductById(id).get();
				products.add(p);
			} catch (Exception e) {
				System.out.println("FAILURE GETTING PRODUCT");
			}
		}
		products.forEach(p -> {
			newCart.addOrIncrement(p, random.nextInt(1,10), p.getPrice());
		});
		
		newCart.setCartOfferPrice();
		System.out.println("this is the offer price " + newCart.getCartOfferPrice());
		return newCart;
		
		
	}
	
	public static Set<String> nameGenerator(int count) {
		String prefix = "User";
		Set<String> names = new HashSet<>();
		Random randomGenerator = new Random();
		while(names.size() < count) {
			names.add(prefix + String.valueOf(randomGenerator.nextInt(1000)));
		}
		return names;
	}
	
	public static Map<String, String> emailGenerator(Set<String> names) {
		Map<String, String> nameAndEmails = new HashMap<>();
		String emailSuffix = "@testMail.com";
		names.forEach(s -> {
			nameAndEmails.put(s, s.concat(emailSuffix));
		});
		return nameAndEmails;
	}

}

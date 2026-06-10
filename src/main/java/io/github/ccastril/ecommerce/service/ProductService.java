package io.github.ccastril.ecommerce.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import io.github.ccastril.ecommerce.dto.ProductResponse;
import io.github.ccastril.ecommerce.entity.Product;
import io.github.ccastril.ecommerce.repository.ProductRepository;
import jakarta.transaction.Transactional;

@Service
@Transactional
public class ProductService {
	List<Product> products;
	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private Environment environment;

	@Autowired
	private ModelMapper modelMapper;

	public ProductService() {
		// TODO Auto-generated constructor stub
	}
	public void insertAllProducts() {
		Product p1 = new Product("Macbook", "macbook_img.jpeg", "Laptop", 3, "Apple", new BigDecimal(3000.0), .15);
		Product p2 = new Product("Dell X123","", "Laptop", 4, "Dell", new BigDecimal(1500.0), .25);
		Product p3 = new Product("iPad","", "Tablet", 4, "Apple", new BigDecimal(3000.0), .5);
		Product p4 = new Product("AirPods", "", "Headphones", 3, "Apple", new BigDecimal(200.0), .0);
		Product p5 = new Product("Jabra Headphones", "", "Headphones", 4, "Jabra", new BigDecimal(155.0), .10);
		Product p6 = new Product("Monitor", "", "Monitor", 4, "Samsung", new BigDecimal(120.00), .25);
		Product p7 = new Product("Monitor", "", "Monitor", 2, "Dell", new BigDecimal(80.0), .1);
		Product p8 = new Product("Dock", "", "Accessories", 4, "Anker", new BigDecimal(25.0), 0.0);
		Product p9 = new Product("USB-C Cable", "", "Accessories", 2, "CableCorp", new BigDecimal(15.00), 0.0);
		Product p10 = new Product("HDMI Cable", "", "Accessories", 5, "CableCorp", new BigDecimal(20.00), .35);




		products = new ArrayList<>();
		products.addAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10));
		productRepo.saveAll(products);
	}

	public List<ProductResponse> getAllProducts() throws Exception {
		List<Product> prods = productRepo.findAll();
		List<ProductResponse> products = prods.stream().map(p -> modelMapper.map(p, ProductResponse.class)).collect(Collectors.toList());
		if(products.isEmpty()) {
			throw new Exception(environment.getProperty("Service.NO_PRODUCTS_FOUND"));
		}
		return products;
	}

	public Optional<Product> getProductById(Long id) throws Exception {
		Optional<Product> prod = productRepo.findById(id);
		return prod;
	}


}
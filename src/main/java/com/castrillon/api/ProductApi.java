package com.castrillon.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.castrillon.service.ProductService;
import com.castrillon.template.ProductTemplate;

@RestController
@RequestMapping("/api/products")
public class ProductApi {

	@Autowired
	public ProductService productService;
	@Autowired
	public ModelMapper modelMapper;

	@GetMapping("/{id}")
	public ProductTemplate getProductById(@PathVariable("id") Long id) throws Exception {
		try {
			ProductTemplate p = modelMapper.map(productService.getProductById(id), ProductTemplate.class);
			return p;
		} catch (Exception e) {
			System.out.println("Exception caught");
			System.out.println(e.toString());
			throw e;
		}

	}

}

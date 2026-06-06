package io.github.ccastril.ecommerce.api;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.ccastril.ecommerce.dto.ProductResponse;
import io.github.ccastril.ecommerce.service.ProductService;

@RestController
@RequestMapping("/api/products")
public class ProductApi {

	@Autowired
	public ProductService productService;
	@Autowired
	public ModelMapper modelMapper;

	@GetMapping("/{id}")
	public ProductResponse getProductById(@PathVariable("id") Long id) throws Exception {
		try {
			ProductResponse p = modelMapper.map(productService.getProductById(id), ProductResponse.class);
			return p;
		} catch (Exception e) {
			System.out.println("Exception caught");
			System.out.println(e.toString());
			throw e;
		}

	}

}

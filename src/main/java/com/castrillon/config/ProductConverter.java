package com.castrillon.config;

import org.modelmapper.AbstractConverter;

import com.castrillon.entity.Product;
import com.castrillon.template.ProductTemplate;


public class  ProductConverter extends AbstractConverter<Product, ProductTemplate> {


	public ProductConverter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected ProductTemplate convert(Product source) {
		// TODO Auto-generated method stub

		return new ProductTemplate(source.getId(), source.getProductName(), source.getProductImage(), source.getDescription(), source.getAverageRating(), source.getSellerName(), source.getPrice(), source.getDiscount());

	}

}

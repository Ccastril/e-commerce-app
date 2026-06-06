package io.github.ccastril.ecommerce.config;

import org.modelmapper.AbstractConverter;

import io.github.ccastril.ecommerce.dto.ProductResponse;
import io.github.ccastril.ecommerce.entity.Product;


public class  ProductConverter extends AbstractConverter<Product, ProductResponse> {


	public ProductConverter() {
		// TODO Auto-generated constructor stub
	}
	@Override
	protected ProductResponse convert(Product source) {
		// TODO Auto-generated method stub

		return new ProductResponse(source.getId(), source.getProductName(), source.getProductImage(), source.getDescription(), source.getAverageRating(), source.getSellerName(), source.getPrice(), source.getDiscount());

	}

}

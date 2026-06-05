package io.github.ccastril.ecommerce.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ModelMapperConfig {
	
	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.addConverter(new ProductConverter());
		modelMapper.addConverter(new CartConverter());
		modelMapper.addConverter(new AccountConverter());
		return modelMapper;
	}
	
	public ModelMapperConfig() {
		// TODO Auto-generated constructor stub
	}

}

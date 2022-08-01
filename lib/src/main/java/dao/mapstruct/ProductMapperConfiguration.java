package dao.mapstruct;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ProductMapperConfiguration {
	
	@Bean
	public ProductMapper getProductMapper() {
		return ProductMapper.INSTANCE;
	}

}

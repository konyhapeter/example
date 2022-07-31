package dao;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import productservice.api.ProductsApi;
import productservice.model.Product;

@RestController
public class ProductController implements ProductsApi {

	@Autowired
	private ProductRepository productRepository;
	
	@Override
	public ResponseEntity<Void> deleteProduct(Long id) {
		productRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Product>> listProducts() {
		List<Product> results = productRepository.findAll().stream().map(entity -> mapProduct(entity))
				.collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(results, HttpStatus.OK);
	}

	private static Product mapProduct(ProductEntity entity) {
		Product product = new Product();
		product.setDescription(entity.getDescription());
		product.setId(entity.getId());
		product.setName(entity.getName());
		product.setPrice(entity.getPrice());
		product.setSku(entity.getSku());
		return product;
	}

}

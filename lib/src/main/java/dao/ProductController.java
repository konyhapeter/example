package dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import dao.mapstruct.ProductMapper;
import productservice.api.ProductsApi;
import productservice.model.Product;

@RestController
public class ProductController implements ProductsApi {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public ResponseEntity<Void> deleteProduct(Long id) {
		productRepository.deleteById(id);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<List<Product>> listProducts() {
		List<Product> results = productRepository.findAll().stream().map(entity -> productMapper.entityToModel(entity))
				.collect(Collectors.toList());
		return new ResponseEntity<List<Product>>(results, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> addProduct(@Valid Product product) {
		productRepository.save(productMapper.modelToEntity(product));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

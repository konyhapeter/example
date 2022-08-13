package dao;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dao.mapstruct.ProductMapper;
import productservice.api.ProductsApi;
import productservice.model.GetProductResponse;
import productservice.model.Product;

@RestController
@RequestMapping(value = "/api/products")
public class ProductController implements ProductsApi {

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public ResponseEntity<Void> deleteProduct(String name) {
		productRepository.deleteProductByName(name);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Product> getProduct(String name) {
		ProductEntity entity = productRepository.getProductByName(name);

		return new ResponseEntity<Product>(productMapper.entityToModel(entity), HttpStatus.OK);
	}

//	@Override
//	@CrossOrigin(origins = "http://localhost:4200")    
//	public ResponseEntity<List<Product>> listProducts() {
//		List<Product> results = productRepository.findAll().stream().map(entity -> productMapper.entityToModel(entity))
//				.collect(Collectors.toList());
//		
//		
//		return new ResponseEntity<List<Product>>(results, HttpStatus.OK);
//	}

	@Override
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<GetProductResponse> listProducts() {
		List<Product> results = productRepository.findAll().stream().map(entity -> productMapper.entityToModel(entity))
				.collect(Collectors.toList());

		GetProductResponse getProductResponse = new GetProductResponse();
		getProductResponse.setProducts(results);

		return new ResponseEntity<GetProductResponse>(getProductResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<Void> addProduct(@Valid Product product) {
		productRepository.save(productMapper.modelToEntity(product));
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}

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

//	private static ObjectMapper mapper = new ObjectMapper();
//	private static ObjectWriter objectWriter = mapper.writer().withDefaultPrettyPrinter();

	@Autowired
	private ProductMapper productMapper;

//	static {
//		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
//	}

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

//	private static Product mapProductEntityToModel(ProductEntity entity) {
//		return mapObjects(entity, Product.class);
//	}
//
//	private static ProductEntity mapProductModelToEntity(Product model) {
//		return mapObjects(model, ProductEntity.class);
//
//	}
//
//	private static <T, S> T mapObjects(S object, Class classOfObject) {
//
//		try {
//			String jsonString = objectWriter.writeValueAsString(object);
//			return (T) mapper.readValue(jsonString, classOfObject);
//		} catch (JsonProcessingException e) {
//			throw new RuntimeException("Cannot read json class", e);
//		}
//
//	}

}

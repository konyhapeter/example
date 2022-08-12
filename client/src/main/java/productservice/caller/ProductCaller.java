package productservice.caller;

import productservice.api.ProductApi;
import productservice.client.ApiClient;
import productservice.model.GetProductResponse;
import productservice.model.Product;

public class ProductCaller {

	public static void main(String[] args) {
		ApiClient apiClient = new ApiClient();
		apiClient.setBasePath("http://localhost:8081/resource-server/api/products");
		ProductApi api = new ProductApi(apiClient);
		// ctrl + 1

		GetProductResponse getProductResponse = api.listProducts();
		System.out.println(getProductResponse.getProducts());

//		api.deleteProduct(1l);
		
		Product product = new Product();
		
		product.setDescription("Second description");
		product.setPrice(123.0f);
		product.setName("Second name");
		product.setSku("Second sku");
		
		api.addProduct(product);
//
		getProductResponse = api.listProducts();
		System.out.println(getProductResponse.getProducts());
	}
}

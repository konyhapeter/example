package productservice.caller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpHeaders;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import productservice.api.ProductApi;
import productservice.client.ApiClient;
import productservice.model.GetProductResponse;
import productservice.model.Product;

public class ProductCaller {
	private static final String tokenUrl = "http://localhost:8080/realms/SpringBootKeycloak/protocol/openid-connect/token";

	public static void main(String[] args) {

		String token = obtainAccessToken();

		ApiClient apiClient = new ApiClient();
		apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
		apiClient.setBasePath("http://localhost:8081/resource-server/api/products");
		ProductApi api = new ProductApi(apiClient);
		// ctrl + 1

		GetProductResponse getProductResponse = api.listProducts();
		System.out.println(getProductResponse);

		Product returnedProduct = api.getProduct("Coffee");

		System.out.println(returnedProduct);

		api.deleteProduct("Coffee");

		Product product = new Product();

		product.setDescription("Second description");
		product.setPrice(123.0f);
		product.setName("Second name");
		product.setSku("Second sku");

		api.addProduct(product);

		getProductResponse = api.listProducts();
		System.out.println(getProductResponse);

		api.deleteProduct("Second name");
	}

	private static String obtainAccessToken() {

		// get access token
		Map<String, String> params = new HashMap<String, String>();
		params.put("grant_type", "client_credentials");
		params.put("client_secret", "gG3ID1HDikbvaHaa8kTnMhvHUQmginqw");
		params.put("client_id", "login-app");
		
		Response response = RestAssured.given().contentType("application/x-www-form-urlencoded").formParams(params)
				.post(tokenUrl);
		List<Integer> responses = Arrays.asList(404, 401, 400);
		if (responses.contains(response.getStatusCode())) {
			throw new RuntimeException("error: " + response.statusCode());
		}
		return response.jsonPath().getString("access_token");
	}
}

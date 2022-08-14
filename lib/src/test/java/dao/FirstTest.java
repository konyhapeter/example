package dao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import productservice.model.GetProductResponse;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@Transactional
public class FirstTest {

	private static final String tokenUrl = "http://localhost:8080/realms/SpringBootKeycloak/protocol/openid-connect/token";

	@Autowired
	private MockMvc mockMvc;

	private static ObjectMapper mapper = new ObjectMapper();

	private static String AUTH_TOKEN = "";

	@BeforeAll
	public static void before() {
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
		AUTH_TOKEN = response.jsonPath().getString("access_token");
	}

	@Test
	public void testGetProducts() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/api/products/products").header(HttpHeaders.AUTHORIZATION, "Bearer " + AUTH_TOKEN))
				.andExpect(status().isOk()).andReturn();

		GetProductResponse response = mapObjects(result.getResponse().getContentAsString(), GetProductResponse.class);
		Assertions.assertTrue( response.getProducts().size() == 3, "response should contain 3 elements");
	}

	private static <T, S> T mapObjects(String jsonString, Class classOfObject) {

		try {
			return (T) mapper.readValue(jsonString, classOfObject);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Cannot read json class", e);
		}

	}

}

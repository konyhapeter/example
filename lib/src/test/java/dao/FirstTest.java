package dao;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.json.JSONException;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

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
	public static void before() throws JSONException {
		// get access token
		RestTemplate restTemplate = new RestTemplate();

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

		MultiValueMap<String, String> paramsForRestTemplate = new LinkedMultiValueMap<>();

		paramsForRestTemplate.add("grant_type", "client_credentials");
		paramsForRestTemplate.add("client_secret", "gG3ID1HDikbvaHaa8kTnMhvHUQmginqw");
		paramsForRestTemplate.add("client_id", "login-app");

		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(paramsForRestTemplate, headers);

		ResponseEntity<String> responseEntity = restTemplate.postForEntity(tokenUrl, request, String.class);

		JSONObject jsonObject = new JSONObject(responseEntity.getBody());

		AUTH_TOKEN = jsonObject.getString("access_token");
	}

	@Test
	public void testGetProducts() throws Exception {
		MvcResult result = mockMvc
				.perform(get("/api/products/products").header(HttpHeaders.AUTHORIZATION, "Bearer " + AUTH_TOKEN))
				.andExpect(status().isOk()).andReturn();

		GetProductResponse response = readJsonString(result.getResponse().getContentAsString(),
				GetProductResponse.class);
		Assertions.assertTrue(response.getProducts().size() == 3,
				"response should contain 3 elements, but was: " + response.getProducts().size());
	}

	private static <T> T readJsonString(String jsonString, Class classOfObject) {

		try {
			return (T) mapper.readValue(jsonString, classOfObject);
		} catch (JsonProcessingException e) {
			throw new RuntimeException("Cannot read json class, json string: " + jsonString, e);
		}

	}

}

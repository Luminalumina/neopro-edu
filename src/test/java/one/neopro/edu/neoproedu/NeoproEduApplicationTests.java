package one.neopro.edu.neoproedu;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import static org.assertj.core.api.FactoryBasedNavigableListAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class NeoproEduApplicationTests {
	@Autowired
	private Controller controller;

	@Test
	void contextLoads() {
	}

	TestRestTemplate testRestTemplate = new TestRestTemplate();
	RestTemplate restTemplate = new RestTemplate();

//		@Test
//	void postTest() throws Exception {
//		String response = String.valueOf(controller.registerNewClient(1L));
//		assertEquals("1", response);
//
//	}
//	@Test
//	void postTest() throws Exception {
//		Client client = new Client(1L);
//	ResponseEntity<Client> response = testRestTemplate.postForEntity("http://localhost:3000/client", client, Client.class);
//	assertThat(response.getBody(), is(String.valueOf("1L")));
//	assertEquals(response.getBody(), String.valueOf("1L"));
//}
	@Test
	public void whenRegisterNewClient() {
		Client client = new Client(2L);

		ResponseEntity<Client> response = testRestTemplate.postForEntity("http://localhost:3000/client", client, Client.class);
		assertThat(response.getStatusCode(), is(HttpStatus.CREATED));
		assertThat(response.getBody().getId(), is(2));
	}
}

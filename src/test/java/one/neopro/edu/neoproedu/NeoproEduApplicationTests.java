package one.neopro.edu.neoproedu;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest(classes = NeoproEduApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NeoproEduApplicationTests {

    //	@Autowired
//	private Controller controller;
    @LocalServerPort
    private int port;

    @Test
    void contextLoads() {
    }

    TestRestTemplate testRestTemplate = new TestRestTemplate();
    Client client = new Client("Tom");


    @Test
    public void whenRegisterNewClient() {

        ResponseEntity<Client> response = testRestTemplate.postForEntity("http://localhost:" + port + "/client/add", client, Client.class);

        System.out.println(response);
        assertEquals(client.getName(), response.getBody().getName());
    }

    @Test
    public void whenGetClientByName(){
        ResponseEntity<Client> response1 = testRestTemplate.getForEntity("http://localhost:" + port + "/get-by-name", Client.class);
        assertEquals(client.getName(), response1.getBody().getName());

    }
}

package one.neopro.edu.neoproedu;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;


@SpringBootTest(classes = NeoproEduApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NeoproEduApplicationTests {

    @LocalServerPort
    private int port;
    @Autowired
    TestRestTemplate testRestTemplate = new TestRestTemplate();
    @Autowired
    private ClientRepo repo;
    @Autowired
    private ConverterService converterService;
    @Autowired
    private ModelMapper modelMapper;

    @Test
    void contextLoads() {
    }





    private ClientDTO createTestClient(String testName){
        ClientEntity testClient = new ClientEntity(testName);
        repo.save(testClient);
        return converterService.convertToDTO(testClient);
    }


    @Test
    public void whenRegisterNewClient() {

        ClientDTO max = createTestClient("Max");
        ResponseEntity<ClientDTO> response = testRestTemplate.postForEntity("http://localhost:" + port + "/client/add", max, ClientDTO.class);

        System.out.println(response);
        assertEquals(max.getName(), response.getBody().getName());


    }

    @Test
    public void whenGetClientById() {
        ClientDTO bob = createTestClient("Bob");

        // 1й вариант
//        TestClient client = testRestTemplate.getForObject("http://localhost:" + port + "/get-by-id/{id}", TestClient.class, bob.getTestId()); // пытаюсь получить объект по id
//        Assertions.assertEquals(client.getTestName(), bob.getTestName()); //сравниваю имя объекта и Боба

        // 2й вариант
        ResponseEntity<ClientDTO> response = testRestTemplate.getForEntity("http://localhost:" + port + "/get-by-id/{id}", ClientDTO.class, bob);
        assertEquals(bob.getName(), response.getBody().getName());

        // 3й вариант
//        TestClient client = testRepo.findByTestId(bob.getTestId()); //ищу в репозитории клиента по id
//        assertEquals(bob.getTestName(), client.getTestName());

    }

    public void whenDeleteClientById() {

    }
}

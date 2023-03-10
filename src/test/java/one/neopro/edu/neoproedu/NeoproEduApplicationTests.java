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

    private ClientDTO createTestClient(String testName) {
        ClientEntity testClient = new ClientEntity(testName);
        repo.save(testClient);
        return converterService.convertToDTO(testClient);
    }

    private ClientEntity createTestClientEntity(String testName) {
        ClientEntity testClient = new ClientEntity(testName);
        repo.save(testClient);
        return (testClient);
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
        ClientEntity bob = createTestClientEntity("Bob");

        // 1й вариант
//        TestClient client = testRestTemplate.getForObject("http://localhost:" + port + "/get-by-id/{id}", TestClient.class, bob.getTestId()); // пытаюсь получить объект по id
//        Assertions.assertEquals(client.getTestName(), bob.getTestName()); //сравниваю имя объекта и Боба

        // 2й вариант
        ResponseEntity<ClientEntity> response = testRestTemplate.getForEntity("http://localhost:" + port + "/get-by-id/{id}", ClientEntity.class, bob.getId());
        assertEquals(bob.getName(), response.getBody().getName());

        // 3й вариант
//        TestClient client = testRepo.findByTestId(bob.getTestId()); //ищу в репозитории клиента по id
//        assertEquals(bob.getTestName(), client.getTestName());

    }

    public void whenDeleteClientById() {

    }
}

class ClientModelMapperTest {
    private ModelMapper modelMapper = new ModelMapper();

    @Test
    public void testModelMapperToEntity() {
        ClientDTO dto = new ClientDTO("Alex");

        ClientEntity entity = modelMapper.map(dto, ClientEntity.class);

        assertEquals(entity.getId(), dto.getId());
        assertEquals(entity.getName(), dto.getName());

    }

    @Test
    public void testModelMapperToDto() {
        ClientEntity entity = new ClientEntity("Jack");

        ClientDTO dto = modelMapper.map(entity, ClientDTO.class);

        assertEquals(dto.getId(), entity.getId());
        assertEquals(dto.getName(), entity.getName());

    }
}

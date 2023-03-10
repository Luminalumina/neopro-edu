package one.neopro.edu.neoproedu;

import org.junit.After;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.assertEquals;
import static org.hamcrest.CoreMatchers.is;


@SpringBootTest(classes = NeoproEduApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class NeoproEduApplicationTests {

    @LocalServerPort
    private int port;


    @Test
    void contextLoads() {
    }

    @Autowired
    TestRestTemplate testRestTemplate = new TestRestTemplate();

    @Autowired
    private TestRepo testRepo;

    @After
    public void resetDb() {
        testRepo.deleteAll();
    }

    private TestClient createTestClient(String testName){
        TestClient testClient = new TestClient(testName);
        testRepo.save(testClient);
        return testClient;
    }


    @Test
    public void whenRegisterNewClient() {

        TestClient max = createTestClient("Max");
        ResponseEntity<TestClient> response = testRestTemplate.postForEntity("http://localhost:" + port + "/client/add", max, TestClient.class);

        System.out.println(response);
        assertEquals(max.getTestName(), response.getBody().getTestName());


    }

    @Test
    public void whenGetClientById() {
        TestClient bob = createTestClient("Bob");

        // 1й вариант
//        Client client = testRestTemplate.getForObject("http://localhost:" + port + "/get-by-id/{id}", Client.class, bob.getId()); // пытаюсь получить объект по id
//        Assertions.assertEquals(client.getName(), bob.getName()); //сравниваю имя объекта и Боба

        // 2й вариант
//        ResponseEntity<Client> response = testRestTemplate.getForEntity("http://localhost:" + port + "/get-by-id/{id}", Client.class, bob);
//        Assertions.assertEquals("Bob", response.getBody().getName());

        // 3й вариант
        TestClient client = testRepo.findByTestId(bob.getTestId()); //ищу в репозитории клиента по id
        assertEquals(bob.getTestName(), client.getTestName());

    }
}
